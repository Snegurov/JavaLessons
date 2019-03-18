package Animals;

import DoubleCompare.DoubleCmp;

public class Dog extends Animal{
    private static double RUN_METERS_AVG = 500.0;
    private static double JUMP_METERS_AVG = 0.5;
    private static double SWIM_METERS_AVG = 10.0;
    private static double MAX_DEVIATION = 0.2;     // максимальное отклонение

    public Dog(String sName) {
        super(sName);
        setMaxRun(Animal.genRandomSkillValue(RUN_METERS_AVG, MAX_DEVIATION));
        setMaxJump(Animal.genRandomSkillValue(JUMP_METERS_AVG, MAX_DEVIATION));
        setMaxSwim(Animal.genRandomSkillValue(SWIM_METERS_AVG, MAX_DEVIATION));
    }

    @Override
    public String getInfo(){
        return String.format("Вид: '%s', Имя: '%s', Бег: [0 .. %.2f], Прыжок: [0 .. %.2f], Плавание: [0 .. %.2f]",
                getClassName(), m_sName, m_dMaxRun, m_dMaxJump, m_dMaxSwim);
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
        if(Animal.checkSkillValue(dMaxSwim, SWIM_METERS_AVG, MAX_DEVIATION))
            m_dMaxSwim = dMaxSwim;
    }

    @Override
    public String getClassName() {
        return "Собака";
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
        return DoubleCmp.isGreater(m_dMaxSwim, dMeters);
    }
}
