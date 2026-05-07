SELECT member_name, review_text, review_date
FROM member_profile
JOIN rest_review USING (member_id)
JOIN (
    SELECT member_id, COUNT(*)
    FROM rest_review
    GROUP BY member_id
    ORDER BY 2 DESC
    LIMIT 1
) t USING (member_id)
ORDER BY review_date ASC, review_text ASC

# SELECT member_id, COUNT(*)
# FROM rest_review
# GROUP BY member_id
# ORDER BY 2 DESC
# LIMIT 1

