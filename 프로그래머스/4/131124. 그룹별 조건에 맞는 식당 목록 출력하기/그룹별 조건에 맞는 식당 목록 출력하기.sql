
# SELECT MEMBER_NAME, REVIEW_TEXT, DATE_FORMAT(REVIEW_DATE, '%Y-%m-%d') AS REVIEW_DATE
# FROM (
#         SELECT MEMBER_ID, MEMBER_NAME, COUNT(1) AS CNT
#         FROM MEMBER_PROFILE
#         JOIN REST_REVIEW USING (MEMBER_ID)
#         GROUP BY MEMBER_ID
#         ORDER BY CNT DESC
#         LIMIT 1
#     ) T
# JOIN REST_REVIEW USING (MEMBER_ID)
# ORDER BY 3, 2

WITH ranked_members AS (
    SELECT 
        member_id,
        RANK() OVER (ORDER BY COUNT(review_id) DESC) as review_rank
    FROM rest_review
    GROUP BY member_id
)
SELECT 
    mp.member_name,
    rr.review_text,
    DATE_FORMAT(rr.review_date, "%Y-%m-%d") AS review_date
FROM member_profile mp
JOIN rest_review rr USING (member_id)
JOIN ranked_members rm ON mp.member_id = rm.member_id
WHERE rm.review_rank = 1
ORDER BY rr.review_date ASC, rr.review_text ASC;