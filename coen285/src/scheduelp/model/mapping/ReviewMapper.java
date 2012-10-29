package scheduelp.model.mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import scheduelp.model.Review;

public class ReviewMapper implements RowMapper<Review> {
	@Override
	public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
		Review review = new Review();
		review.setReviewID(rs.getInt("review_id"));
		review.setCourse((rs.getString("course_code")));
		review.setRating(rs.getInt("rating"));
		review.setComments(rs.getString("comments"));
		review.setStudent((rs.getString("first_name")));
		review.setReviewDateTime(rs.getString("review_datetime"));
		
		return review;
	}
}
