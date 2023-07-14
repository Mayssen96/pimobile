import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

public class RatingForm extends Dialog {
    private int selectedRating = 0;

    public RatingForm() {
        super();

        setTitle("Rate Blog");

        Container starContainer = new Container();
        starContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        starContainer.setUIID("StarContainer");

        for (int i = 1; i <= 5; i++) {
            Button starButton = new Button();
            starButton.setUIID("StarButton");
            starButton.setPressedIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR, "Button", 0xFFD32F));
            starButton.setDisabledIcon(FontImage.createMaterial(FontImage.MATERIAL_STAR, "Button", 0xFFD32F));
            starButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Button starBtn = (Button) evt.getSource();
                    selectedRating = starContainer.getComponentIndex(starBtn) + 1;
                    dispose();
                }
            });
            starContainer.add(starButton);
        }

        SpanLabel infoLabel = new SpanLabel("Tap a star to rate");

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        contentPane.add(starContainer);
        contentPane.add(infoLabel);
    }

    public int getSelectedRating() {
        return selectedRating;
    }
}
