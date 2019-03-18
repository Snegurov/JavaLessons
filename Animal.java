package Animals;

import DoubleCompare.DoubleCmp;

public abstract class Animal {
    protected String m_sName;
    protected double m_dMaxRun;
    protected double m_dMaxJump;
    protected double m_dMaxSwim;

    /* Проверяет, входит ли val в интервал [max(0, avg * (1 - max_deviation)), max(0, avg * (1 +  max_deviation))]
       max_deviation - максимальное отклонение как доля от avg, больше либо равное 0 (1 - означает 100% от avg) */
    protected static boolean checkSkillValue(double val, double avg, double max_deviation){
        if(DoubleCmp.isLess(max_deviation, 0.0))
            return false;

        return DoubleCmp.isGreaterOrEqual(val, Math.max(avg * (1.0 - max_deviation), 0.0)) &&
                DoubleCmp.isLessOrEqual(val, Math.max(avg * (1.0 + max_deviation), 0.0));
    }

    /* Возвращает случайное значение из интервала [max(0, avg * (1 - max_deviation)), max(0, avg * (1 +  max_deviation))]
       max_deviation - максимальное отклонение как доля от avg, больше либо равное 0 (1 - означает 100% от avg) */
    protected static double genRandomSkillValue(double avg, double max_deviation){
        if(DoubleCmp.isLess(max_deviation, 0.0))
            return Math.max(avg, 0.0);

        double dNum = avg * (1.0 + max_deviation * (2.0 * Math.random() - 1.0));
        return Math.max(0.0, Math.round(dNum * 100.0) / 100.0);
    }

    public Animal(String sName) {
        setName(sName);
    }

    public String getName() {
        return m_sName;
    }

    public void setName(String sName) {
        m_sName = sName;
    }

    public double getMaxRun() {
        return m_dMaxRun;
    }

    public double getMaxJump() {
        return m_dMaxJump;
    }

    public double getMaxSwim() {
        return m_dMaxSwim;
    }

    public abstract String getInfo();
    public abstract void setMaxRun(double dMaxRun);
    public abstract void setMaxJump(double dMaxJump);
    public abstract void setMaxSwim(double dMaxSwim);
    public abstract String getClassName();
    public abstract boolean run(double dMeters);
    public abstract boolean jump(double dMeters);
    public abstract boolean swim(double dMeters);
}


