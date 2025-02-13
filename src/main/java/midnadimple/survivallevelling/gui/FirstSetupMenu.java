package midnadimple.survivallevelling.gui;

import midnadimple.survivallevelling.SurvivalLevelling;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.hud.HudComponents;
import net.minecraft.client.render.Tessellator;
import net.minecraft.core.lang.I18n;
import org.lwjgl.opengl.GL11;

public class FirstSetupMenu extends GuiScreen {
	private static final I18n loc = I18n.getInstance();

	@Override
	public void init() {
		GuiButton changeHudButton = new GuiButton(0, this.width / 2 - 105, this.height / 2 + 15, 100, 20, loc.translateKey("survivallevelling.fts.changehud"));
		GuiButton dontChangeHudButton = new GuiButton(1, this.width / 2 + 5, this.height / 2 + 15, 100, 20, loc.translateKey("survivallevelling.fts.dontchangehud"));
		controlList.add(changeHudButton);
		controlList.add(dontChangeHudButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick)
	{
		GL11.glDisable(2896);
		GL11.glDisable(2912);
		Tessellator tessellator = Tessellator.instance;
		GL11.glBindTexture(3553, this.mc.renderEngine.getTexture("/gui/background-loading-nether.png"));
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		float f = 32.0f;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(0x404040);
		tessellator.addVertexWithUV(0.0, this.height, 0.0, 0.0, (float)this.height / f);
		tessellator.addVertexWithUV(this.width, this.height, 0.0, (float)this.width / f, (float)this.height / f);
		tessellator.addVertexWithUV(this.width, 0.0, 0.0, (float)this.width / f, 0.0);
		tessellator.addVertexWithUV(0.0, 0.0, 0.0, 0.0, 0.0);
		tessellator.draw();

		int lines = 3;

		int titleHeight = (height / 2) - 25;

		// Title
		drawStringCentered(fontRenderer, loc.translateKey("survivallevelling.fts.title"), width / 2, titleHeight - 12, 0xFFFFFF);

		// Lines
		for (int i = 1; i <= lines; i++)
		{
			drawStringCentered(fontRenderer, loc.translateKey("survivallevelling.fts.line" + i), width / 2, (titleHeight + ((12 * (i - 1)))), 0x9E9E9E);
		}

		super.drawScreen(mouseX, mouseY, partialTick);
	}

	@Override
	protected void buttonReleased(GuiButton button)
	{
		if (button.id == 0 || button.id == 1)
		{
			mc.displayGuiScreen(null);
		}

		if (button.id == 0) {
			String rpgHotbar = "hotbar{[];abs[0.5,0.90909094,TOP_CENTER]}|crosshair{[];abs[0.5,0.5,CENTER]}|health_bar{[];snap[exp,TOP_LEFT,BOTTOM_LEFT]}|armor_bar{[];snap[health_bar,TOP_CENTER,BOTTOM_CENTER]}|oxygen_bar{[];snap[armor_bar,TOP_CENTER,BOTTOM_CENTER]}|fire_bar{[];snap[oxygen_bar,TOP_CENTER,BOTTOM_CENTER]}|rotation_lock{[];snap[crosshair,CENTER_RIGHT,CENTER_LEFT]}|exp{[];snap[hotbar,TOP_LEFT,BOTTOM_LEFT]}|";
			HudComponents.INSTANCE.fromSettingsString(rpgHotbar);
		}

		SurvivalLevelling.options.survival_levelling$firstSetupFinished().set(true);
		mc.gameSettings.saveOptions();
	}
}
