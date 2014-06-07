package display;


import java.awt.Image;

import javax.swing.ImageIcon;

import model.Card;
import model.Card.Suit;

/**
 * Utility methods for the user interface
 * @author dgibbs
 *
 */
public class Util {
	/**
	 * Finds and scales the image for a specified card
	 * @param card to be turned into an image
	 * @return scaled image of the given card
	 */
	public static ImageIcon getScaledImage(Card card){
		String loc = "/cards/";
		Enum<Suit> suit = card.getSuit();
		if (suit == Suit.CLUB){
			loc += "Clubs/";
		}
		if (suit == Suit.DIAMOND){
			loc += "Diamonds/";
		}
		if (suit == Suit.HEART){
			loc += "Hearts/";
		}
		if (suit == Suit.SPADE){
			loc += "Spades/";
		}
		return getScaledImage(loc + card.getValue() + ".png", 70, -1);
	}
	
	/**
	 * Scales an image to the specified width and height
	 * @param path of the image to be scaled
	 * @param width target width of the returned image
	 * @param height target height of the returned image
	 * @return image at the given path scaled to the dimensions specified
	 */
	public static ImageIcon getScaledImage(String path, int width, int height){
		ImageIcon icon = new ImageIcon(PokerTable.class.getResource(path));
		Image image = icon.getImage();
		Image scaled = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(scaled);
	}
}
