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