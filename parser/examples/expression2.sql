
FUT_RENEW.LAST_VISIT IS NOT NULL
AND
(
    FUT_RENEW.LAST_VISIT <  FT_RENEW.ABS_LAST_POST_DATE
    OR
    FUT_RENEW.LAST_VISIT <  FT_RENEW.LAST_POST_DATE
)
