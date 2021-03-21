package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@CssImport("./views/main/main-view.css")
@PWA(name = "SleepCycleApp", shortName = "SleepCycleApp", enableInstallPrompt = false)
@JsModule("./styles/shared-styles.js")
@Route("")
@Theme(value = Lumo.class,variant = Lumo.DARK )
public class MainView extends VerticalLayout {
    private final Label welcome = new Label("Sleep Time Calculator");
    private final HorizontalLayout hLayout = new HorizontalLayout();
    private final Button wakeUpTimeButton = new Button("When should I wake up ?");
    private final Paragraph firstParagraph = new Paragraph("According to recent studies, " +
            "in order to get enough sleep, you need to complete, at least 5 or 6 " +
            "sleep cycles. This calculator can help you establish your bedtimes, in order to wake up well rested");

    private final Details details = new Details();

    public MainView() {
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        HorizontalLayout logoAndGreet = new HorizontalLayout( );
    
        logoAndGreet.setAlignItems(Alignment.STRETCH);
        logoAndGreet.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        Image image = new Image("/images/logoSleepingGuy.png", "sleep");
        image.setHeight("120px");
        image.getElement().getStyle().set("margin","auto");

        logoAndGreet.add(image,welcome);

        firstParagraph.setMaxHeight("200px");
        firstParagraph.setMaxWidth("500px");

        wakeUpTimeButton.setDisableOnClick(true);
        Label text = new Label("It's recommended to wake up at either of the following hours");

        wakeUpTimeButton.addClickListener(click->{
            addComponentAtIndex(3,text);
           computeAndShowTimes();
        });

        Anchor anchor1 = new Anchor();
        anchor1.setText("Harvard Health Article");
        anchor1.setHref("https://www.helpguide.org/harvard/biology-of-sleep-circadian-rhythms-sleep-stages.htm");
        Anchor anchor2 = new Anchor();
        anchor2.setText("Healthline Article");
        anchor2.setHref("https://www.healthline.com/health/best-time-to-sleep#when-to-go-to-sleep");
        details.setSummaryText("About & Studies");
        VerticalLayout detailsVertical = new VerticalLayout();
        detailsVertical.add(anchor1,anchor2);
        details.addContent(detailsVertical);


        add(logoAndGreet,firstParagraph,wakeUpTimeButton,hLayout,details);

    }

    private void computeAndShowTimes() {

        LocalDateTime now = LocalDateTime.now();
        final int averageFallingAsleepTime = 14;
        String[] wakeTimes = new String[7];

        for (int i = 1; i <= 6 ; i++) {
            LocalDateTime temp = now.plus(averageFallingAsleepTime + (90 * i)
                    , ChronoUnit.MINUTES);
            wakeTimes[i] = temp.format(DateTimeFormatter.ofPattern("HH:mm"));
            Label label = new Label(wakeTimes[i] + " AM ");
            label.getElement().getStyle().set("font-size","14px");
            hLayout.add(label);
            hLayout.setSpacing(true);
        }

    }


}
