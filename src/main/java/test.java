import java.io.IOException;
import java.util.Arrays;

import com.siftscience.ApplyDecisionRequest;
import com.siftscience.ApplyDecisionResponse;
import com.siftscience.EventRequest;
import com.siftscience.EventResponse;
import com.siftscience.SiftClient;
import com.siftscience.exception.SiftException;
import com.siftscience.model.Address;
import com.siftscience.model.ApplyDecisionFieldSet;
import com.siftscience.model.Comment;
import com.siftscience.model.CreateCommentFieldSet;
import com.siftscience.model.CreateListingFieldSet;
import com.siftscience.model.CreateMessageFieldSet;
import com.siftscience.model.CreatePostFieldSet;
import com.siftscience.model.CreateProfileFieldSet;
import com.siftscience.model.CreateReviewFieldSet;
import com.siftscience.model.Image;
import com.siftscience.model.Item;
import com.siftscience.model.Listing;
import com.siftscience.model.Message;
import com.siftscience.model.Post;
import com.siftscience.model.Profile;
import com.siftscience.model.Review;

public class test {

    public static void main(String[] args) {
        try {
            SiftClient client = new SiftClient("z123");

            Address contactAddress = new Address()
                .setName("Bill Jones")
                .setPhone("1-415-555-6041")
                .setRegion("New Hampshire")
                .setCity("New London")
                .setCountry("US")
                .setZipCode("03257");

            Address locationAddress = new Address()
                .setCity("Seattle")
                .setRegion("Washington")
                .setCountry("US")
                .setZipCode("98112");

            Item listedItem = new Item()
                .setPrice(2950000000L)
                .setCurrencyCode("USD")
                .setTags(Arrays.asList("heat", "washer/dryer"));

            Image image = new Image()
                .setMd5Hash("aflshdfbalsubdf3234sfdkjb")
                .setLink("https://www.domain.com/file.png")
                .setDescription("Billy's picture");

            Listing listing = new Listing()
                .setBody("Capitol Hill Seattle brand new condo. 2 bedrooms and 1 full bath.")
                .setContactAddress(contactAddress)
                .setLocations(Arrays.asList(locationAddress))
                .setListedItems(Arrays.asList(listedItem))
                .setImages(Arrays.asList(image))
                .setExpirationTime(1471003200000L);

            EventRequest listingRequest = client.buildRequest(new CreateListingFieldSet()
                // Required Fields
                .setUserId("fyw3989sjpqr71")
                .setContentId("listing_23412")
                .setSessionId("a234ksjfgn435sfg")
                .setCustomField("account_type", "premium")
                .setStatus("$active")
                .setIp("255.255.255.0")
                .setListing(listing));

            Post post = new Post()
                .setSubject("My new apartment!")
                .setBody("Moved into my new apartment yesterday.")
                .setContactEmail("alex_301@domain.com")
                .setContactAddress(contactAddress)
                .setLocations(Arrays.asList(locationAddress))
                .setCategories(Arrays.asList("Personal"))
                .setImages(Arrays.asList(image))
                .setExpirationTime(1549063157000L);

            EventRequest postRequest = client.buildRequest(new CreatePostFieldSet()
                // Required Fields
                .setUserId("fyw3989sjpqr71")
                .setContentId("post_23412")
                .setSessionId("a234ksjfgn435sfg")
                .setStatus("$active")
                .setIp("255.255.255.0")
                .setPost(post));

            Comment comment = new Comment()
                .setBody("Congrats on the new role!")
                .setContactEmail("alex_301@domain.com")
                .setParentCommentId("comment_23407")
                .setRootContentId("listing-12923213")
                .setImages(Arrays.asList(image));

            EventRequest commentRequest = client.buildRequest(new CreateCommentFieldSet()
                // Required Fields
                .setUserId("fyw3989sjpqr71")
                .setContentId("comment_23412")
                .setSessionId("a234ksjfgn435sfg")
                .setStatus("$active")
                .setIp("255.255.255.0")
                .setComment(comment));

            Message message = new Message()
                .setBody("Let's meet at 5pm")
                .setContactEmail("alex_301@domain.com")
                .setRecipientUserIds(Arrays.asList("fy9h989sjphh71"))
                .setImages(Arrays.asList(image));

            EventRequest messageRequest = client.buildRequest(new CreateMessageFieldSet()
                // Required Fields
                .setUserId("fyw3989sjpqr71")
                .setContentId("message_23412")
                .setSessionId("a234ksjfgn435sfg")
                .setStatus("$active")
                .setIp("255.255.255.0")
                .setMessage(message));

            Profile profile = new Profile()
                .setBody("Hi! My name is Alex and I just moved to New London!")
                .setContactEmail("alex_301@domain.com")
                .setContactAddress(contactAddress)
                .setCategories(Arrays.asList("Friends", "Long-term dating"))
                .setImages(Arrays.asList(image));

            EventRequest profileRequest = client.buildRequest(new CreateProfileFieldSet()
                // Required Fields
                .setUserId("fyw3989sjpqr71")
                .setContentId("profile_23412")
                .setSessionId("a234ksjfgn435sfg")
                .setStatus("$active")
                .setIp("255.255.255.0")
                .setProfile(profile));

            Review review = new Review()
                .setSubject("Amazing Tacos!")
                .setBody("I ate the tacos.")
                .setContactEmail("alex_301@domain.com")
                .setLocations(Arrays.asList(locationAddress))
                .setReviewedContentId("listing_234234")
                .setImages(Arrays.asList(image))
                .setRating(4.5);

            EventRequest reviewRequest = client.buildRequest(new CreateReviewFieldSet()
                // Required Fields
                .setUserId("fyw3989sjpqr71")
                .setContentId("review_23412")
                .setSessionId("a234ksjfgn435sfg")
                .setStatus("$active")
                .setIp("255.255.255.0")
                .setReview(review));

            ApplyDecisionRequest decisionRequest = client.buildRequest(new ApplyDecisionFieldSet()
                .setAccountId("4e1a50e172beb95cf1e4ae54")
                .setUserId("fyw3989sjpqr71")
                .setContentId("review_23412")
                .setDecisionId("content_looks_ok_content_abuse")
                .setAnalyst("analyst@example.com")
                .setDescription("Text seems innocuous.")
                .setSource(ApplyDecisionFieldSet.DecisionSource.MANUAL_REVIEW));

            EventResponse response;
            try {
                response = listingRequest.send();
                response = postRequest.send();
                response = commentRequest.send();
                response = messageRequest.send();
                response = profileRequest.send();
                response = reviewRequest.send();
                ApplyDecisionResponse a = decisionRequest.send();
            } catch (SiftException e) {
                System.out.println(e.getApiErrorMessage());
                return;
            }
            response.isOk(); // true

        } catch (IOException IOe) {
            System.out.println("IOEXCEPTION");
        }
    }

}