package usecase;

import java.util.HashMap;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetTopGradeUseCase class.
 */
public final class GetBottomGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetBottomGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the highest grade for a course across your team.
     * @param course The course.
     * @return The top grade.
     */
    public HashMap<String, Float> getBottomGrade(String course) {
        HashMap<String, Float> resMap = new HashMap<>();
        // Call the API to get the usernames of all your team members
        float min = 100;
        String minName = "";
        final Team team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members
        for (String username : team.getMembers()) {
            // Call the API to get the grade for the course for the username
            final Grade[] grades = gradeDataBase.getGrades(username);
            for (Grade grade : grades) {

                if (grade.getCourse().equals(course)) {
                    // Sum all the grades
                    if (grade.getGrade() < min) {
                        min = grade.getGrade();
                        minName = username;
                    }
                }
            }
        }
        resMap.put(minName, min);
        return resMap;
    }

}
