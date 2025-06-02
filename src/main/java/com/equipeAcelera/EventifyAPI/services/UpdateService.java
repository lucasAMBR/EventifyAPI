package com.equipeAcelera.EventifyAPI.services;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equipeAcelera.EventifyAPI.DTOs.event.UpdateOnlineEventDTO;
import com.equipeAcelera.EventifyAPI.DTOs.event.UpdatePresentialEventDTO;
import com.equipeAcelera.EventifyAPI.DTOs.post.UpdatePostDTO;
import com.equipeAcelera.EventifyAPI.DTOs.user.UpdateUserDataDTO;
import com.equipeAcelera.EventifyAPI.exceptions.PersonalExceptions.UnauthorizedFunctionAccessException;
import com.equipeAcelera.EventifyAPI.models.Comments.Comment;
import com.equipeAcelera.EventifyAPI.models.Event.Event;
import com.equipeAcelera.EventifyAPI.models.Event.OnlineEvent;
import com.equipeAcelera.EventifyAPI.models.Event.PresentialEvent;
import com.equipeAcelera.EventifyAPI.models.Like.Like;
import com.equipeAcelera.EventifyAPI.models.Post.Post;
import com.equipeAcelera.EventifyAPI.models.Subscription.Subscription;
import com.equipeAcelera.EventifyAPI.models.User.NormalUser;
import com.equipeAcelera.EventifyAPI.models.User.OrganizerUser;
import com.equipeAcelera.EventifyAPI.models.User.User;
import com.equipeAcelera.EventifyAPI.utils.GeocodingUtils;
import com.equipeAcelera.EventifyAPI.utils.ImageUtils;

@Service
public class UpdateService {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired 
    EventService eventService;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    CommentService commentService;

    @Autowired 
    LikeService likeService;

    public void updateUserData(UpdateUserDataDTO userData){
        
        User findedUser = userService.findUserById(userData.getId());

        if(userData.getUserName() != null){
            findedUser.setName(userData.getUserName());

            for(Post post : postService.listAllPosts()){
                if(post.getUserId() == findedUser.getId()){
                    post.setUserName(userData.getUserName());
                }
            };

            for(Event event : eventService.getEventList()){
                if(event.getOrganizerId() == findedUser.getId()){
                    event.setOrganizerName(userData.getUserName());;
                }
            }

            for(Like like : likeService.listAllLikes()){
                if(like.getUserId() == findedUser.getId()){
                    like.setUserName(userData.getUserName());
                }
            }

            for(Subscription subs : subscriptionService.listAllSubs()){
                if(subs.getUserId() == findedUser.getId()){
                    subs.setUserName(userData.getUserName());
                }
            }

            for(Comment comment : CommentService.commentList){
                if(comment.getUserId() == findedUser.getId()){
                    comment.setUserName(findedUser.getName());
                }
            }
        }

        if(userData.getNewProfilePic() != null){
            final String uploadDir = "src/main/resources/static/";

            String newProfilePicPath = ImageUtils.saveProfilePic(userData.getNewProfilePic());

            try{
                Path filePath = Paths.get(uploadDir + findedUser.getProfilePicPath());
                File file = filePath.toFile();

                if(file.exists() && findedUser.getProfilePicPath() != "/uploads/profile_pic/default.png"){
                    file.delete();
                }
            }catch(Exception e){
                throw new RuntimeException(e);
            }

            findedUser.setProfilePicPath(newProfilePicPath);
        }
    }

    public boolean updatePresentialEvent(UpdatePresentialEventDTO newEventData){
        User organizerUser = userService.findUserById(newEventData.getOrganizerId());

        Event event = eventService.getEventById(newEventData.getEventId());

        if(organizerUser.getId() != event.getOrganizerId()){
            throw new UnauthorizedFunctionAccessException("You cannot update someone event");
        }

        if(newEventData.getNewTitle() != null){
            event.setTitle(newEventData.getNewTitle());
        }

        if(newEventData.getNewDescription() != null){
            event.setDescription(newEventData.getNewDescription());
        }

        if(newEventData.getNewDate() != null){
            event.setDate(newEventData.getNewDate());
        }

        if(newEventData.getNewHour() != null){
            event.setHour(newEventData.getNewHour());
        }

        if(newEventData.getNewGuestLimit() > 0){
            event.setGuestLimit(newEventData.getNewGuestLimit());
        }

        if(newEventData.getNewBannerImage() != null){
            String imagePath =ImageUtils.saveEventBannerPic(newEventData.getNewBannerImage());

            event.setImagePath(imagePath);
        }

        if(newEventData.getNewLocation() != null){
            if(event instanceof PresentialEvent){
                ((PresentialEvent) event).setLocation(newEventData.getNewLocation());

                Map<String, Double> latitudeAndLongitude = GeocodingUtils.getLatitudeLongitude(newEventData.getNewLocation());

                ((PresentialEvent) event).setLatitude(latitudeAndLongitude.get("latitude"));
                ((PresentialEvent) event).setLongitude(latitudeAndLongitude.get("longitude"));
            }
        }

        return true;
    }

        public boolean updateOnlineEvent(UpdateOnlineEventDTO newEventData){
        User organizerUser = userService.findUserById(newEventData.getOrganizerId());

        Event event = eventService.getEventById(newEventData.getEventId());

        if(organizerUser.getId() != event.getOrganizerId()){
            throw new UnauthorizedFunctionAccessException("You cannot update someone event");
        }

        if(newEventData.getNewTitle() != null){
            event.setTitle(newEventData.getNewTitle());
        }

        if(newEventData.getNewDescription() != null){
            event.setDescription(newEventData.getNewDescription());
        }

        if(newEventData.getNewDate() != null){
            event.setDate(newEventData.getNewDate());
        }

        if(newEventData.getNewHour() != null){
            event.setHour(newEventData.getNewHour());
        }

        if(newEventData.getNewGuestLimit() > 0){
            event.setGuestLimit(newEventData.getNewGuestLimit());
        }

        if(newEventData.getNewBannerImage() != null){
            String imagePath =ImageUtils.saveEventBannerPic(newEventData.getNewBannerImage());

            event.setImagePath(imagePath);
        }

        if(newEventData.getNewLink() != null){
            ((OnlineEvent) event).setLink(newEventData.getNewLink());
        }

        return true;
    }

    public Post UpdatePostContent(UpdatePostDTO updateData){
        Post findedPost = postService.findPostById(updateData.getPostId());

        if(findedPost.getUserId() != updateData.getUserId()){
            throw new UnauthorizedFunctionAccessException("You dont have permission to this!!!");
        }

        findedPost.setContent(updateData.getContent());

        return findedPost;
    }

    public void CancelSubscription(int userId, int eventId){

        User findedUser = userService.findUserById(userId);

        Event findedEvent = eventService.getEventById(eventId);

        for(Subscription subs : SubscriptionService.subscriptionList){
            if(subs.getEventId() == findedEvent.getId() && subs.getUserId() == findedUser.getId()){
                SubscriptionService.subscriptionList.remove(subs);
                ((NormalUser) findedUser).getSubscriptions().remove(subs);
                findedEvent.getSubscriptionList().remove(subs);
                return;
            }
        }

        throw new RuntimeException("Subscription not found!");
    }

    public void removeCanceledEvent(int eventId){
        Event findedEvent = eventService.getEventById(eventId);

        User findedUser = userService.findUserById(findedEvent.getOrganizerId());

        if(findedEvent.getSubscriptionList().size() > 0 && findedEvent.isActive()){
            throw new UnauthorizedFunctionAccessException("You cannot exclude an active event");
        }

        ((OrganizerUser) findedUser).getEventList().remove(findedEvent);

        EventService.eventList.remove(findedEvent);
    }

    public void deletePost(int postId){
        Post findedPost = postService.findPostById(postId);
        User findedUser = userService.findUserById(findedPost.getUserId());
        PostService.postList.remove(findedPost);
        findedUser.getPostList().remove(findedPost);

        findedPost = null;
    }

}
