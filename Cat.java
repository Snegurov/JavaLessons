package Animals;

import DoubleCompare.DoubleCmp;

public class Cat extends Animal {
    private static double RUN_METERS_AVG = 200.0;
    private static double JUMP_METERS_AVG = 2.0;
    private static double MAX_DEVIATION = 0.15;     // максимальное отклонение

    public Cat(String sName) {
        super(sName);
        setMaxRun(Animal.genRandomSkillValue(RUN_METERS_AVG, MAX_DEVIATION));
        setMaxJump(Animal.genRandomSkillValue(JUMP_METERS_AVG, MAX_DEVIATION));
        m_dMaxSwim = 0.0;
    }

    @Override
    public String getInfo(){
        return String.format("Вид: '%s', Имя: '%s', Бег: [0 .. %.2f], Прыжок: [0 .. %.2f]",
                getClassName(), m_sName, m_dMaxRun, m_dMaxJump);
    }

    @Override
    public void setMaxRun(double dMaxRun) {
        if(Animal.checkSkillValue(dMaxRun, RUN_METERS_AVG, MAX_DEVIATION))
            m_dMaxRun = dMaxRun;
    }

    @Override
    public void setMaxJump(double dMaxJump) {
        if(Animal.checkSkillValue(dMaxJump, JUMP_METERS_AVG, MAX_DEVIATION))
            m_dMaxJump = dMaxJump;
    }

    @Override
    public void setMaxSwim(double dMaxSwim) {
    }

    @Override
    public String getClassName() {
        return "Кот";
    }

    @Override
    public boolean run(double dMeters) {
        return DoubleCmp.isGreater(m_dMaxRun, dMeters);
    }

    @Override
    public boolean jump(double dMeters) {
        return DoubleCmp.isGreater(m_dMaxJump, dMeters);
    }

    @Override
    public boolean swim(double dMeters) {
        return false;
    }
}
