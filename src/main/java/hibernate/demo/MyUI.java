package hibernate.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.UI;
import hibernate.demo.entity.Course;
import hibernate.demo.entity.Instructor;
import hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;

import javax.servlet.annotation.WebServlet;
import java.util.*;

/** http://localhost:8080/OneToMany-bi-1.0-SNAPSHOT/InstructorUIServlet
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        GridLayout layout = new GridLayout(4,4);

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {


            List<Instructor> instructors = session.createQuery("from Instructor", Instructor.class).list();

            session.beginTransaction();
            Instructor tempInstructor = instructors.get(1);
            System.out.println(tempInstructor);

            Course tempCourse = new Course("Owing");
            Course secondTempCourse = new Course("Making stuff up");

            tempInstructor.add(tempCourse);
            tempInstructor.add(secondTempCourse);
            session.save(tempCourse);
            session.save(secondTempCourse);
            System.out.println(tempInstructor);

            session.getTransaction().commit();




            List<Course> cources = session.createQuery("from Course", Course.class).list();



            Grid<Course> gridInstructors = new Grid<>(Course.class);
            gridInstructors.setItems(cources);
            layout.addComponent(gridInstructors,0,0,3,2);

            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        setContent(layout);

    }

    @WebServlet(urlPatterns = "/*", name = "InstructorUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }



}
