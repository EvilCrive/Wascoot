package GroupCreation.resource;

import org.json.JSONObject;

public class Group {
    private final Long courseID;
    private int members;
    private final String name;
    private final String description;
    /**
     * The photo of the group
     */
    private  byte[] photo;

    /**
     * The MIME media type of photo of the group
     */
    private  String photoMediaType;

    // this is used to return a student after login
    public Group(Long courseID, String name, String description, byte[] photo, String photoMediaType,int members){
        this.courseID = courseID;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.photoMediaType = photoMediaType;
        this.members = members;
    }

    // this is used to return a student after login
    public Group(Long courseID, String name, String description, byte[] photo, String photoMediaType){
        this.courseID = courseID;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.photoMediaType = photoMediaType;
//        this.members = members;
    }
    public Group(Long courseID, String name, String description){
        this.courseID = courseID;
        this.name = name;
        this.description = description;
    }







//    public String getId() {
//        return id;
//    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getCourseID() {
        return courseID;
    }
    public final byte[] getPhoto() {
        return photo;
    }
    public final int getMembers() {
        return members;
    }

    /**
     * Returns the MIME media type of photo of the employee.
     *
     * @return the MIME media type of photo of the employee.
     */
    public final String getPhotoMediaType() {
        return photoMediaType;
    }




}
