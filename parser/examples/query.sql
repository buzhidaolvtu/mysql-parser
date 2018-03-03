select
  BF.ID AS FORUM_ID , COUNT(FT_RENEW.ID) TCRENEW, 1-1 as constant,ABS( ABS(FT_RENEW.ID,'parameter') ,'parameter2') as abs
from
  b_forum BF
  LEFT JOIN b_forum_user_forum FUF_ALL ON (FUF_ALL.USER_ID =  1 AND FUF_ALL.FORUM_ID =  0)
  LEFT JOIN b_forum_topic FT_RENEW ON
                                     (
                                       BF.ID = FT_RENEW.FORUM_ID AND FT_RENEW.STATE != 'L' AND
                                       (FUF_ALL.LAST_VISIT IS NULL OR FT_RENEW.ABS_LAST_POST_DATE >  FUF_ALL.LAST_VISIT)
                                       )
  LEFT JOIN b_forum_user_topic FUT_RENEW ON (
  FUT_RENEW.FORUM_ID =  BF.ID AND FUT_RENEW.TOPIC_ID =  FT_RENEW.ID AND FUT_RENEW.USER_ID =  1)
WHERE
  (
    (BF.ID IN (-1 ,+2,'a','cccdd',0xff) and BF.AMOUND >= 1000)
    AND
    (
      FUT_RENEW.LAST_VISIT IS NULL
      AND
      (
        (FUF_ALL.LAST_VISIT IS NULL AND FUF.LAST_VISIT IS NULL)
        OR
        (
          FUF.LAST_VISIT IS NOT NULL
          AND
          ! (FUF.LAST_VISIT <  FT_RENEW.ABS_LAST_POST_DATE OR FUF.LAST_VISIT <  FT_RENEW.LAST_POST_DATE)
        )
        OR
        (
          FUF.LAST_VISIT IS NULL
          AND
          FUF_ALL.LAST_VISIT IS NOT NULL
          AND
          (
            (
              FUF_ALL.LAST_VISIT <  FT_RENEW.ABS_LAST_POST_DATE
              OR
              FUF_ALL.LAST_VISIT <  FT_RENEW.LAST_POST_DATE
            )
          )
        )
      )
    )
    OR (
      FUT_RENEW.LAST_VISIT IS NOT NULL
      AND
      (
        FUT_RENEW.LAST_VISIT <  FT_RENEW.ABS_LAST_POST_DATE
        OR
        FUT_RENEW.LAST_VISIT <  FT_RENEW.LAST_POST_DATE
      )
    )
  )