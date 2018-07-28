package com.vdi.reports.djasper.templates;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;

@Configuration
public class TemplateStyles {
	
	private Style standardTitleStyle;
	private Style standardSubTitleStyle;
	private Style dejavuSansTitleStyle;

	private Style arialHeaderStyle;
	private Style arialHeaderVariableStyle;
	private Style standardHeaderStyle;
	
	private Style standardDetailTextStyle;
	private Style standardDetailRedTextStyle;
	private Style standardDetailGreenTextStyle;
	private Style standardDetailLeftTextStyle;
	
	private Style stdDetailPerfTextStyle;
	private Style stdLeftPerfTextStyle;
	private Style stdRedPerfTextStyle;
	private Style stdGreenPerfTextStyle;
	
	private Style standardOddBgStyle;
	private Style oddRowStyle;

	public TemplateStyles() {
	}
	
	@Autowired
	public TemplateStyles(TemplateFonts fonts) {
		this.standardTitleStyle = createStandardTitleStyle(fonts);
		this.standardSubTitleStyle = createStandardSubtitleStyle(fonts);
		this.dejavuSansTitleStyle = createDejavuSansTitleStyle(fonts);

		this.standardHeaderStyle = createStandardHeaderStyle(fonts);
		this.standardDetailTextStyle = createStandardDetailTextStyle(fonts);
		this.standardDetailRedTextStyle = createStandardDetailRedTextStyle(fonts);
		this.standardDetailGreenTextStyle = createStandardDetailGreenTextStyle(fonts);
		this.standardDetailLeftTextStyle = createStandardDetailLeftTextStyle(fonts);
		
		this.stdLeftPerfTextStyle = createStdDetailLeftTextStyle(fonts);
		this.stdDetailPerfTextStyle = createStdPerfTextStyle(fonts);
		this.stdRedPerfTextStyle = createStdRedPerfTextStyle(fonts);
		this.stdGreenPerfTextStyle = createStdGreenPerfTextStyle(fonts);
		
		this.standardOddBgStyle = createStandardOddBgStyle(fonts);
	}
	

	public Style createStandardTitleStyle(TemplateFonts fonts) {

		Font font = fonts.getStandard12BoldFont().getFont();

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		return sb.build();
	}

	public Style createStandardSubtitleStyle(TemplateFonts fonts) {
		Font font = fonts.createStandard12BoldFont().getFont();

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		return sb.build();
	}
	
	public Style createDejavuSansTitleStyle(TemplateFonts fonts) {
		Font font = fonts.getDejavuSansBoldFont();
		font.setFontSize(18);
		
		Style dejavuSansTitleStyle = new Style("dejavuSansTitleStyle");
		dejavuSansTitleStyle.setFont(font);
		
		return dejavuSansTitleStyle;
	}

	public Style createStandardHeaderStyle(TemplateFonts fonts) {
		Font font = fonts.createStandard8BoldFont().getFont();

		Color color = new Color(247, 199, 104);

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setBackgroundColor(color);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		return sb.build();
	}
	
	public Style createStandardDetailLeftTextStyle(TemplateFonts fonts) {
		Font font = fonts.getStandard7Font().getFont();

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.LEFT);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		return sb.build();
	}
	
	public Style createStandardDetailRedTextStyle(TemplateFonts fonts) {
		Font font = fonts.getStandard7BoldFont().getFont();

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(Color.RED);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		return sb.build();
	}
	
	public Style createStandardDetailGreenTextStyle(TemplateFonts fonts) {
		Font font = fonts.getStandard7BoldFont().getFont();

		Color color = new Color(10, 102, 10);
		
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(color);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		return sb.build();
	}

	public Style createStandardDetailTextStyle(TemplateFonts fonts) {
		Font font = fonts.createStandard7Font().getFont();

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		return sb.build();
	}
	
	public Style createStdDetailLeftTextStyle(TemplateFonts fonts) {
		Font font = fonts.createStandard8BoldFont().getFont();

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.LEFT);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		return sb.build();
	}
	
	public Style createStdPerfTextStyle(TemplateFonts fonts) {
		Font font = fonts.createStandard12BoldFont().getFont();

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		return sb.build();
	}
	
	public Style createStdRedPerfTextStyle(TemplateFonts fonts) {
		Font font = fonts.createStandard12BoldFont().getFont();

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(Color.RED);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		return sb.build();
	}
	
	public Style createStdGreenPerfTextStyle(TemplateFonts fonts) {
		Font font = fonts.createStandard12BoldFont().getFont();

		Color color = new Color(10, 102, 10);
		
		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setTextColor(color);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		return sb.build();
	}

	public Style createStandardOddBgStyle(TemplateFonts fonts) {
		Font font = fonts.createStandard8BoldFont().getFont();

		Color color = new Color(253, 247, 233);

		StyleBuilder sb = new StyleBuilder(true);
		sb.setFont(font);
		sb.setBackgroundColor(color);
		sb.setTextColor(Color.BLACK);
		sb.setHorizontalAlign(HorizontalAlign.CENTER);
		sb.setVerticalAlign(VerticalAlign.MIDDLE);
		sb.setTransparency(Transparency.OPAQUE);
		return sb.build();
	}
	
	public Style createOddRowStyle(TemplateFonts fonts) {
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER());
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);
		
		return oddRowStyle;
	}


	public Style getStandardTitleStyle() {
		return standardTitleStyle;
	}


	public Style getStandardSubTitleStyle() {
		return standardSubTitleStyle;
	}


	public Style getStandardHeaderStyle() {
		return standardHeaderStyle;
	}


	public Style getStandardDetailTextStyle() {
		return standardDetailTextStyle;
	}


	public Style getStandardOddBgStyle() {
		return standardOddBgStyle;
	}

	public Style getStandardDetailLeftTextStyle() {
		return standardDetailLeftTextStyle;
	}

	public Style getStandardDetailRedTextStyle() {
		return standardDetailRedTextStyle;
	}

	public Style getStandardDetailGreenTextStyle() {
		return standardDetailGreenTextStyle;
	}

	public Style getStdDetailPerfTextStyle() {
		return stdDetailPerfTextStyle;
	}

	public Style getStdRedPerfTextStyle() {
		return stdRedPerfTextStyle;
	}

	public Style getStdGreenPerfTextStyle() {
		return stdGreenPerfTextStyle;
	}

	public Style getStdLeftPerfTextStyle() {
		return stdLeftPerfTextStyle;
	}

	public Style getDejavuSansTitleStyle() {
		return dejavuSansTitleStyle;
	}

}
