public class Person{
    private String m_sFIO;
    private String m_sPosition;
    private String m_sEmail;
    private String m_sPhone;
    private double m_dSalary;
    private int m_nAge;

    public Person(String sFIO, String sPosition, String sEmail, String sPhone, double dSalary, int nAge){
        setFIO(sFIO);
        setPosition(sPosition);
        setEmail(sEmail);
        setPhone(sPhone);
        setSalary(dSalary);
        setAge(nAge);
    }

    public void printInfo(){
        System.out.println("ФИО: " + m_sFIO + ", должность: " + m_sPosition + ", e-mail: " + m_sEmail +
                ", тел.: " + m_sPhone + ", зарплата: " + String.format("%.2f", m_dSalary) + " руб., возраст: " + m_nAge);
    }

    public void setFIO(String sFIO) {
        this.m_sFIO = sFIO;
    }

    public void setPosition(String sPosition) {
        this.m_sPosition = sPosition;
    }

    public void setEmail(String sEmail) {
        this.m_sEmail = sEmail;
    }

    public void setPhone(String sPhone) {
        this.m_sPhone = sPhone;
    }

    public void setSalary(double dSalary) {
        if(dSalary > -1e-2)
            this.m_dSalary = dSalary;
    }

    public void setAge(int nAge) {
        if(nAge > 0 && nAge < 150)
            this.m_nAge = nAge;
    }

    public String getFIO() {
        return m_sFIO;
    }

    public String getPosition() {
        return m_sPosition;
    }

    public String getEmail() {
        return m_sEmail;
    }

    public String getPhone() {
        return m_sPhone;
    }

    public double getSalary() {
        return m_dSalary;
    }

    public int getAge() {
        return m_nAge;
    }
}

