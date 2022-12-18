insert into discount_card
    (holder_name, holder_email, discount_rate, is_active, creation_date)
values ('1001@supermail.com', 'John Smith', 3.0, true, '2022-12-17 23:49:38'),
       ('1002@supermail.com', 'Jane Smith', 3.0, true, '2022-12-17 23:49:39'),
       ('1003@supermail.com', 'John Doe', 3.0, true, '2022-12-17 23:49:40'),
       ('1004@supermail.com', 'Jane Doe', 3.0, true, '2022-12-17 23:49:41'),
       ('1005@supermail.com', 'Mescalero El Camino', 3.0, true, '2022-12-17 23:49:42');

insert into product
    (product_name, price, is_promoted, creation_date, is_deleted)
values ('Vic Firth drumsticks 2B', 14.0, false, '2022-12-17 23:49:38', false),
       ('Vic Firth drumsticks 2BN', 14.1, true, '2022-12-17 23:49:38', false),
       ('Vic Firth drumsticks 5B', 14.1, false, '2022-12-17 23:49:38', false),
       ('Vic Firth Signature Series Kenny Aronoff', 17.0, true, '2022-12-17 23:49:38', false),
       ('Vic Firth Signature Series Terry Bozzio', 17.0, true, '2022-12-17 23:49:38', false),
       ('Vic Firth Signature Series JoJo Mayer', 17.0, true, '2022-12-17 23:49:38', false),
       ('Vic Firth Signature Series Nicko McBrain', 17.0, false, '2022-12-17 23:49:38', false),
       ('Vic Firth Signature Series Mike Terrana', 17.0, false, '2022-12-17 23:49:38', false),
       ('Vic Firth Signature Series Dave Weckl', 17.0, true, '2022-12-17 23:49:38', false),
       ('Drummaster drumsticks 2B USA Hickory', 9.0, false, '2022-12-17 23:49:38', false);

