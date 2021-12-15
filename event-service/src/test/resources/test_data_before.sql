insert into events (id, name, description, link, date_start, date_end)
values  (1, 'test 1', 'test 1 description', 'https://test2.com', '2021-12-08 23:36:13.000000', '2021-12-08 23:36:18.000000'),
        (2, 'test 2', 'test 2 description', 'https://test2.com', '2021-12-11 15:09:48.000000', '2021-12-11 15:09:53.000000');

insert into tags (id, name)
values  (1, 'test tag 1'),
        (2, 'test tag 2');

insert into events_tag (id_tag, id_event)
values  (1, 1),
        (2, 1);