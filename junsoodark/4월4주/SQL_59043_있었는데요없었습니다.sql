-- 코드를 입력하세요
SELECT ins.ANIMAL_ID, ins.NAME
from ANIMAL_INS as ins, ANIMAL_OUTS as outs
where ins.ANIMAL_ID = outs.ANIMAL_ID and ins.datetime > outs.datetime
order by ins.datetime asc
