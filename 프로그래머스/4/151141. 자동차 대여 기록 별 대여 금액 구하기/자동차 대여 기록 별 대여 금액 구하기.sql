WITH truck_discount_plan AS (
    SELECT CAST(REPLACE(duration_type, '일 이상', '') AS UNSIGNED) AS duration_type, discount_rate
    FROM car_rental_company_discount_plan
    WHERE car_type = '트럭'
)

SELECT history_id,
    (t.daily_fee * (100 - IFNULL(p.discount_rate, 0)) / 100 * t.days) AS FEE
FROM (
    SELECT history_id, daily_fee, DATEDIFF(END_DATE, START_DATE) + 1 AS days
    FROM car_rental_company_rental_history h
    JOIN car_rental_company_car c
    ON h.car_id = c.car_id AND c.car_type = '트럭'
) t
-- 대여 일수에 맞는 최대 할인율 조인
LEFT JOIN truck_discount_plan p
ON p.duration_type = (
    SELECT MAX(duration_type)
    FROM truck_discount_plan
    WHERE duration_type <= t.days
)
ORDER BY fee DESC, history_id DESC