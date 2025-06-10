USE newsFeed;

CREATE TABLE 'user'
(
    'id'       BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '사용자 식별자',
    'email'    VARCHAR(100)       NOT NULL UNIQUE COMMENT '이메일',
    'password' VARCHAR(255)       NOT NULL COMMENT '비밀번호'
);

CREATE TABLE 'profile'
(
    'id'      BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '프로필 식별자',
    'name'    VARCHAR(100)       NOT NULL COMMENT '이름',
    'info'    VARCHAR(255)       NOT NULL COMMENT '설명',
    'user_id' BIGINT             NOT NULL COMMENT '유저 ID',

    constraint 'fk_profile_user' foreign key ('user_id') references 'user' ('id')
);

create table 'board'
(
    'id'          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '게시글 식별자',
    'title'       VARCHAR(100)       NOT NULL COMMENT '제목',
    'contents'    VARCHAR(255)       NOT NULL COMMENT '내용',
    'user_id'     BIGINT             NOT NULL COMMENT '유저 ID',
    'created_at'  DATETIME           NOT NULL COMMENT '생성일시',
    'modified_at' DATETIME           NOT NULL COMMENT '수정일시',

    constraint 'fk_board_user' foreign key ('user_id') references 'user' ('id')
);

create table 'comments'
(
    'id'          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '댓글 식별자',
    'contents'    VARCHAR(255)       NOT NULL COMMENT '내용',
    'user_id'     BIGINT             NOT NULL COMMENT '유저 ID',
    'board_id'    BIGINT             NOT NULL COMMENT '게시글 ID',
    'created_at'  DATETIME           NOT NULL COMMENT '생성일시',
    'modified_at' DATETIME           NOT NULL COMMENT '수정일시',

    constraint 'fk_comments_user' foreign key ('user_id') references 'user' ('id'),
    constraint 'fk_comments_board' foreign key ('board_id') references 'board' ('id')
);

create table 'like_comments'
(
    'id'          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '댓글 좋아요 식별자',
    'user_id'     BIGINT             NOT NULL COMMENT '유저 ID',
    'comments_id' BIGINT             NOT NULL COMMENT '댓글 ID',
    'created_at'  DATETIME           NOT NULL COMMENT '생성일시',
    'modified_at' DATETIME           NOT NULL COMMENT '수정일시',
    'cancel'      BIGINT             NOT NULL default 0 comment '좋아요 취소 여부',

    constraint 'fk_like_comments_user' foreign key ('user_id') references 'user' ('id'),
    constraint 'fk_like_comments_comments' foreign key ('comments_id') references 'comments' ('id')
);

create table 'like_board'
(
    'id'          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '게시글 좋아요 식별자',
    'user_id'     BIGINT             NOT NULL COMMENT '유저 ID',
    'board_id'    BIGINT             NOT NULL COMMENT '게시글 ID',
    'created_at'  DATETIME           NOT NULL COMMENT '생성일시',
    'modified_at' DATETIME           NOT NULL COMMENT '수정일시',
    'cancel'      BIGINT             NOT NULL default 0 comment '좋아요 취소 여부',

    constraint 'fk_like_board_user' foreign key ('user_id') references 'user' ('id'),
    constraint 'fk_like_board_board' foreign key ('board_id') references 'board' ('id')
);

create table 'follow'
(
    'id'         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '팔로우 식별자',
    'user_id'    BIGINT             NOT NULL COMMENT '유저 ID',
    'follow_id'  BIGINT             NOT NULL COMMENT '팔로우 ID',
    'created_at' DATETIME           NOT NULL COMMENT '생성일시',
    'unfollow'   BIGINT             NOT NULL default 0 comment '팔로우 취소 여부',

    constraint 'fk_follow_user' foreign key ('user_id') references 'user' ('id'),
    constraint 'fk_follow_user' foreign key ('follow_id') references 'user' ('id')
);


