WITH total_users AS (
    SELECT
        user_id,
        COUNT(*) OVER() AS total
    FROM user_info
    WHERE YEAR(joined) = 2021
)

SELECT
    t.year AS YEAR,
    t.month AS MONTH,
    COUNT(*) AS PURCHASED_USERS,
    ROUND(COUNT(*) / total, 1) AS PURCHASED_RATIO
FROM (
    SELECT
        DISTINCT(s.user_id) AS user_id,
        YEAR(SALES_DATE) AS year,
        MONTH(SALES_DATE) AS month,
        total
    FROM online_sale s
    JOIN total_users u
    ON s.user_id = u.user_id
) t
GROUP BY t.year, t.month
ORDER BY t.year, t.month
