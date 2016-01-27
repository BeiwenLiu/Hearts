package sample;

import javafx.scene.image.ImageView;

/**
 * Created by Beiwen Liu on 1/25/2016.
 */
public class ImageCardContainer {
    private ImageView imageView;
    private Card card;

    public ImageCardContainer(ImageView imageView, Card card) {
        this.imageView = imageView;
        this.card = card;
    }

    public ImageCardContainer(ImageView imageView) {
        this(imageView, null);
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
