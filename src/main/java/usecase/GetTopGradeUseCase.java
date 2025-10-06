package usecase;

import java.util.HashMap;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetTopGradeUseCase class.
 */
public final class GetTopGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetTopGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the highest grade for a course across your team.
     * @param course The course.
     * @return The top grade.
     */
    public HashMap<String, Float> getTopGrade(String course) {
        HashMap<String, Float> resMap = new HashMap<>();
        // Call the API to get the usernames of all your team members
        float max = 0;
        String maxName = "";
        final Team team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members
        for (String username : team.getMembers()) {
            // Call the API to get the grade for the course for the username
            final Grade[] grades = gradeDataBase.getGrades(username);
            for (Grade grade : grades) {

                if (grade.getCourse().equals(course)) {
                    // Sum all the grades
                    if (grade.getGrade() > max) {
                        max = grade.getGrade();
                        maxName = username;
                    }
                }
            }
        }
        resMap.put(maxName, max);
        return resMap;
    }
}
