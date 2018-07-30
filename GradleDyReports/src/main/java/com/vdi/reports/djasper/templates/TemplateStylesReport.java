package com.vdi.reports.djasper.templates;

import java.awt.Color;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;

@Configuration
public class TemplateStylesReport {

	private Style dejavuSansTitleStyle;

	private Style arialHeaderStyle;
	private Style arialHeaderVariableStyle;

	private Style detailStyle;

	private Style groupVariableStyle;

	private Style importSTyle;

	private Style oddRowStyle;

	// for summary style
	private Style arialHeaderSummaryStyle;
	private Style arialDetailSummaryStyle;

	// for agent
	private Style arialTitleAgentStyle;
	private Style arialLeftAgentStyle;
	private Style arialDetailAgentStyle;

	public TemplateStylesReport() {
	}

	@Autowired
	public TemplateStylesReport(TemplateFontsReport font) {

		this.dejavuSansTitleStyle = createDejavuSansTitleStyle(font);

		this.arialHeaderStyle = createArialHeaderStyle(font);
		this.arialHeaderVariableStyle = createArialHeaderVariableStyle(font);

		this.detailStyle = createDetailStyle(font);

		this.groupVariableStyle = createGroupVariableStyle(font);

		this.importSTyle = createImportStyle(font);

		this.oddRowStyle = createOddRowStyle(font);

		// for summary
		this.arialHeaderSummaryStyle = createArialHeaderSummaryStyle(font);
		this.arialDetailSummaryStyle = createArialDetailSummaryStyle(font);

		// for agent
		this.arialTitleAgentStyle = createArialTitleAgentStyle(font);
		this.arialDetailAgentStyle = createArialDetailAgentStyle(font);
		this.arialLeftAgentStyle = createArialLeftAgentStyle(font);
	}

	public Style createDejavuSansTitleStyle(TemplateFontsReport font) {
		Font myfont = font.getDejavuSansBoldFont();
		myfont.setFontSize(18);

		Style dejavuSansTitleStyle = new Style("dejavuSansTitleStyle");
		dejavuSansTitleStyle.setFont(myfont);

		return dejavuSansTitleStyle;
	}

	public Style createArialHeaderStyle(TemplateFontsReport font) {

		Style headerStyle = new Style("headerStyle");
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderBottom(Border.PEN_1_POINT());
		headerStyle.setBackgroundColor(Color.gray);
		headerStyle.setTextColor(Color.white);
		headerStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);

		return headerStyle;
	}

	public Style createArialHeaderVariableStyle(TemplateFontsReport font) {

		Style headerVariables = new Style("headerVariables");
		headerVariables.setFont(Font.ARIAL_BIG_BOLD);
		headerVariables.setBorderBottom(Border.THIN());
		headerVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
		headerVariables.setVerticalAlign(VerticalAlign.TOP);
		headerVariables.setStretchWithOverflow(true);

		return headerVariables;
	}

	public Style createDetailStyle(TemplateFontsReport font) {
		Style detailStyle = new Style("detailStyle");

		return detailStyle;
	}

	public Style createGroupVariableStyle(TemplateFontsReport font) {

		Style groupVariables = new Style("groupVariables");
		groupVariables.setFont(Font.ARIAL_MEDIUM_BOLD);
		groupVariables.setTextColor(Color.BLUE);
		groupVariables.setBorderBottom(Border.THIN());
		groupVariables.setHorizontalAlign(HorizontalAlign.RIGHT);
		groupVariables.setVerticalAlign(VerticalAlign.BOTTOM);

		return groupVariables;
	}

	public Style createImportStyle(TemplateFontsReport font) {

		Style importeStyle = new Style();
		importeStyle.setHorizontalAlign(HorizontalAlign.RIGHT);

		return importeStyle;
	}

	public Style createOddRowStyle(TemplateFontsReport font) {
		Style oddRowStyle = new Style();
		oddRowStyle.setBorder(Border.NO_BORDER());
		oddRowStyle.setBackgroundColor(Color.LIGHT_GRAY);
		oddRowStyle.setTransparency(Transparency.OPAQUE);

		return oddRowStyle;
	}

	// style for Summary Reports
	public Style createArialHeaderSummaryStyle(TemplateFontsReport font) {

		Style headerStyle = new Style("headerSummaryStyle");
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderTop(Border.PEN_1_POINT());
		headerStyle.setBorderBottom(Border.PEN_1_POINT());
		headerStyle.setBackgroundColor(Color.gray);
		headerStyle.setTextColor(Color.white);
		headerStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		headerStyle.setTransparency(Transparency.OPAQUE);

		return headerStyle;
	}

	public Style createArialDetailSummaryStyle(TemplateFontsReport font) {

		Style headerStyle = new Style("detailSummaryStyle");
		headerStyle.setFont(Font.ARIAL_MEDIUM_BOLD);
		headerStyle.setBorderBottom(Border.PEN_1_POINT());
		// headerStyle.setBackgroundColor(Color.gray);
		// headerStyle.setTextColor(Color.white);
		headerStyle.setHorizontalAlign(HorizontalAlign.LEFT);
		headerStyle.setVerticalAlign(VerticalAlign.MIDDLE);
		// headerStyle.setTransparency(Transparency.OPAQUE);

		return headerStyle;

	}

	//for agent sub report
	public Style createArialTitleAgentStyle(TemplateFontsReport font) {
		Font myfont = font.getArialBoldFont();
		myfont.setFontSize(14);

		Style style = new Style("arialTitleAgentStyle");
		style.setFont(myfont);
		style.setHorizontalAlign(HorizontalAlign.CENTER);

		return style;
	}
	
	public Style createArialLeftAgentStyle(TemplateFontsReport font) {

		Font myFont = font.getArialFont();
		myFont.setFontSize(9);
		myFont.setBold(true);

		Style style = new Style("leftAgentStyle");
		style.setFont(myFont);
		// headerStyle.setBorderBottom(Border.PEN_1_POINT());
		// headerStyle.setBackgroundColor(Color.gray);
		// headerStyle.setTextColor(Color.white);
		style.setHorizontalAlign(HorizontalAlign.LEFT);
		style.setVerticalAlign(VerticalAlign.MIDDLE);
		// headerStyle.setTransparency(Transparency.OPAQUE);

		return style;

	}

	public Style createArialDetailAgentStyle(TemplateFontsReport font) {

		Font myFont = font.getArialFont();
		myFont.setFontSize(9);
		myFont.setBold(true);

		Style style = new Style("detailAgentStyle");
		style.setFont(myFont);
		style.setBorderBottom(Border.PEN_1_POINT());
		// headerStyle.setBackgroundColor(Color.gray);
		// headerStyle.setTextColor(Color.white);
		style.setHorizontalAlign(HorizontalAlign.LEFT);
		style.setVerticalAlign(VerticalAlign.MIDDLE);
		// headerStyle.setTransparency(Transparency.OPAQUE);

		return style;

	}

	public Style getDejavuSansTitleStyle() {
		return dejavuSansTitleStyle;
	}

	public Style getOddRowStyle() {
		return oddRowStyle;
	}

	public Style getArialHeaderStyle() {
		return arialHeaderStyle;
	}

	public Style getArialHeaderVariableStyle() {
		return arialHeaderVariableStyle;
	}

	public Style getGroupVariableStyle() {
		return groupVariableStyle;
	}

	public Style getImportSTyle() {
		return importSTyle;
	}

	public Style getDetailStyle() {
		return detailStyle;
	}

	public Style getArialHeaderSummaryStyle() {
		return arialHeaderSummaryStyle;
	}

	public Style getArialTitleAgentStyle() {
		return arialTitleAgentStyle;
	}

	public Style getArialDetailSummaryStyle() {
		return arialDetailSummaryStyle;
	}

	public Style getArialDetailAgentStyle() {
		return arialDetailAgentStyle;
	}

	public Style getArialLeftAgentStyle() {
		return arialLeftAgentStyle;
	}

}