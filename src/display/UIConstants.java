package display;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Defines user interface constants
 * @author dgibbs
 *
 */
public class UIConstants {
	public static final Border CARD_HIGHLIGHT = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.YELLOW, Color.DARK_GRAY);
	public static final Color TABLE_COLOR = new Color(51, 153, 51);
	public static final int PLAYER_WIDTH = 160;
	public static final int TABLE_WIDTH = 850;
}
