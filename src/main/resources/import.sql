-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
insert into BLOCK_REQUEST (LS_ID, DATE_CREATE, USERNAME_CREATE)
    values (1, TIMESTAMP '2023-07-02 14:57:29', 'Sarah Connor');
insert into BLOCK_REQUEST (LS_ID, DATE_CREATE, USERNAME_CREATE)
    values (2, TIMESTAMP '2023-07-02 14:58:29', 'James Bond');

insert into BLOCK_REQUEST (LS_ID, DATE_CREATE, USERNAME_CREATE)
values (3, TIMESTAMP '2023-07-02 14:59:29', 'Lara Croft');

insert into BLOCK_REQUEST_NUMBERS (LS2_ID, NUMBER_ADD,LS_ID)
values (1, '3002457775',1);