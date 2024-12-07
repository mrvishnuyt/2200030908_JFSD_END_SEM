package endsem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {

    public static void main(String[] args) {
        // Create SessionFactory and Session
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        // Insert records
        insertDepartment(session);

        // Delete records
        deleteDepartment(session, 1);

        transaction.commit();
        session.close();
        factory.close();
    }

    public static void insertDepartment(Session session) {
        Department dept = new Department();
        dept.setName("Computer Science");
        dept.setLocation("Building A");
        dept.setHodName("Dr. Smith");

        session.save(dept);
        System.out.println("Department inserted successfully");
    }

    public static void deleteDepartment(Session session, int deptId) {
        String hql = "delete from Department where id = :deptId";
        session.createQuery(hql).setParameter("deptId", deptId).executeUpdate();
        System.out.println("Department deleted successfully");
    }
}
