INSERT INTO member (reg_dtt, email, phone, user_name, nick_name, auth_yn, authority, password) values
                                         ('2022-10-28 21:43:44.727000', 'admin@naver.com', '01099999999', 'admin1', 'admin1Nickname', true, 'ROLE_ADMIN', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member1@naver.com', '01099999999', 'member1', 'member1Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member2@naver.com', '01099999999', 'member2', 'member2Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member3@naver.com', '01099999999', 'member3', 'member3Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member4@naver.com', '01099999999', 'member4', 'member4Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member5@naver.com', '01099999999', 'member5', 'member5Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member6@naver.com', '01099999999', 'member6', 'member6Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member7@naver.com', '01099999999', 'member7', 'member7Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member8@naver.com', '01099999999', 'member8', 'member8Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member9@naver.com', '01099999999', 'member9', 'member9Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member10@naver.com', '01099999999', 'member10', 'member10Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member11@naver.com', '01099999999', 'member11', 'member11Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member12@naver.com', '01099999999', 'member12', 'member12Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member13@naver.com', '01099999999', 'member13', 'member13Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member14@naver.com', '01099999999', 'member14', 'member14Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6'),
                                         ('2022-10-28 21:43:44.727000', 'member15@naver.com', '01099999999', 'member15', 'member15Nickname', true, 'ROLE_MEMBER', '$2a$10$5/d8MenInOGIZ8uKjptHj.LkIzsVO3KIDj59NlrIbvpcjjHnoe0E6');


INSERT INTO post (writer_email, writer_nick_name, title, content, music_link, youtube_unique_code, youtube_view_tag, music_title, artist, genre, mood, weather, post_dtt, hits, mean_marks, report_dtt, report_reason, post_status) values
                     ('member1@naver.com','member1Nickname', '게시글1', '게시글1 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'HIPHOP', 'EXCITING', 'CLEAR',
                      '2022-10-29 21:43:44.727000', '0', '1.5', null, null, 'POST'),
                     ('member2@naver.com','member2Nickname', '게시글2', '게시글2 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'BALLAD', 'SAD', 'CLOUDS',
                      '2022-10-29 21:43:44.727000', '0', '4', null, null, 'POST'),
                     ('member3@naver.com','member3Nickname', '게시글3', '게시글3 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'ROCK', 'CALM', 'RAIN',
                      '2022-10-29 21:43:44.727000', '0', '3.5', null, null, 'POST'),
                     ('member4@naver.com','member4Nickname', '게시글4', '게시글4 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'JAZZ', 'WARM', 'THUNDERSTORM',
                      '2022-10-29 21:43:44.727000', '0', '2', null, null, 'POST'),
                     ('member5@naver.com','member5Nickname', '게시글5', '게시글5 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'HIPHOP', 'AERIAL', 'SNOW',
                      '2022-10-29 21:43:44.727000', '0', '4.5', null, null, 'POST'),
                     ('member6@naver.com','member6Nickname', '게시글6', '게시글6 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'BALLAD', 'EXCITING', 'MIST',
                      '2022-10-29 21:43:44.727000', '0', '1', '2022-10-30 21:43:44.727000', '신고 사유', 'REPORT'),
                     ('member7@naver.com','member7Nickname', '게시글7', '게시글7 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'ROCK', 'SAD', 'TORNADO',
                      '2022-10-29 21:43:44.727000', '0', '3', '2022-10-30 21:43:44.727000', '신고 사유', 'REPORT'),
                     ('member8@naver.com','member8Nickname', '게시글8', '게시글8 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'JAZZ', 'CALM', 'CLEAR',
                      '2022-10-29 21:43:44.727000', '0', '3.5', '2022-10-30 21:43:44.727000', '신고 사유', 'REPORT'),
                     ('member9@naver.com','member9Nickname', '게시글9', '게시글9 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'HIPHOP', 'WARM', 'CLOUDS',
                      '2022-10-29 21:43:44.727000', '0', '0', '2022-10-30 21:43:44.727000', '신고 사유', 'REPORT'),
                     ('member10@naver.com','member10Nickname', '게시글10', '게시글10 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'BALLAD', 'AERIAL', 'RAIN',
                      '2022-10-29 21:43:44.727000', '0', '0', '2022-10-30 21:43:44.727000', '신고 사유', 'REPORT'),
                     ('member11@naver.com','member11Nickname', '게시글11', '게시글11 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'ROCK', 'EXCITING', 'THUNDERSTORM',
                      '2022-10-29 21:43:44.727000', '0', '0', '2022-10-30 21:43:44.727000', '신고 사유', 'REPORT'),
                     ('member12@naver.com','member12Nickname', '게시글12', '게시글12 내용',
                      'https://youtu.be/eN5mG_yMDiM', 'eN5mG_yMDiM', '<iframe width="560" height="315" src="https://www.youtube.com/embed/eN5mG_yMDiM" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>',
                      '봄여름가을겨울', '빅뱅', 'JAZZ', 'SAD', 'SNOW',
                      '2022-10-29 21:43:44.727000', '0', '0', '2022-10-30 21:43:44.727000', '신고 사유', 'DELETE');

INSERT INTO comment (post_id, writer_email, writer_nick_name, content, mark, reg_dtt, report_dtt, report_reason, comment_status) values
                             ('1', 'member1@naver.com', 'member1Nickname', '댓글 1', '1', '2022-11-20 21:43:44.727000',
                              null, null, 'POST'),
                             ('1', 'member2@naver.com', 'member2Nickname', '댓글 2', '2', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'REPORT'),
                             ('1', 'member3@naver.com', 'member3Nickname', '댓글 3', '3', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'DELETE'),
                             ('2', 'member1@naver.com', 'member1Nickname', '댓글 1', '3', '2022-11-20 21:43:44.727000',
                              null, null, 'POST'),
                             ('2', 'member2@naver.com', 'member2Nickname', '댓글 2', '5', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'REPORT'),
                             ('2', 'member3@naver.com', 'member3Nickname', '댓글 3', '4', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'DELETE'),
                             ('3', 'member1@naver.com', 'member1Nickname', '댓글 1', '2', '2022-11-20 21:43:44.727000',
                              null, null, 'POST'),
                             ('3', 'member2@naver.com', 'member2Nickname', '댓글 2', '5', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'REPORT'),
                             ('3', 'member3@naver.com', 'member3Nickname', '댓글 3', '4', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'DELETE'),
                             ('4', 'member1@naver.com', 'member1Nickname', '댓글 1', '3', '2022-11-20 21:43:44.727000',
                              null, null, 'POST'),
                             ('4', 'member2@naver.com', 'member2Nickname', '댓글 2', '1', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'REPORT'),
                             ('4', 'member3@naver.com', 'member3Nickname', '댓글 3', '5', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'DELETE'),
                             ('5', 'member1@naver.com', 'member1Nickname', '댓글 1', '5', '2022-11-20 21:43:44.727000',
                              null, null, 'POST'),
                             ('5', 'member2@naver.com', 'member2Nickname', '댓글 2', '4', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'REPORT'),
                             ('5', 'member3@naver.com', 'member3Nickname', '댓글 3', '5', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'DELETE'),
                             ('6', 'member1@naver.com', 'member1Nickname', '댓글 1', '1', '2022-11-20 21:43:44.727000',
                              null, null, 'POST'),
                             ('6', 'member2@naver.com', 'member2Nickname', '댓글 2', '1', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'REPORT'),
                             ('6', 'member3@naver.com', 'member3Nickname', '댓글 3', '2', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'DELETE'),
                             ('7', 'member1@naver.com', 'member1Nickname', '댓글 1', '3', '2022-11-20 21:43:44.727000',
                              null, null, 'POST'),
                             ('7', 'member2@naver.com', 'member2Nickname', '댓글 2', '3', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'REPORT'),
                             ('7', 'member3@naver.com', 'member3Nickname', '댓글 3', '4', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'DELETE'),
                             ('8', 'member1@naver.com', 'member1Nickname', '댓글 1', '2', '2022-11-20 21:43:44.727000',
                              null, null, 'POST'),
                             ('8', 'member2@naver.com', 'member2Nickname', '댓글 2', '5', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'REPORT'),
                             ('8', 'member3@naver.com', 'member3Nickname', '댓글 3', '3', '2022-11-20 21:43:44.727000',
                              '2022-11-21 21:43:44.727000', '신고 사유', 'DELETE');

