package model;

public class Person
{
  private final String fname;
  private final String mname;
  private final String lname;

  public Person(String fname, String mname, String lname) {
    this.fname = fname;
    this.mname = mname;
    this.lname = lname;
  }

  public String getMname()
  {
    return mname;
  }

  public String getLname()
  {
    return lname;
  }

  public String getFname()
  {

    return fname;
  }
}
