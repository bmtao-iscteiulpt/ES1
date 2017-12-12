package antiSpamFilter;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

 public class GUI {
 
	File file = new File ("rules.cf");
		
	private String s;
	//private Object[][] rules = {};
	//private ArrayList<String> listOfRules = new ArrayList<String>();
	private JFrame frame;
	
	private JPanel panelConfigure;
	private JLabel labelConfigureManual;
	private JCheckBox checkBoxConfigureManual;
	private JLabel labelConfigureAuto;
	private JCheckBox checkBoxConfigureAuto;
	
	private JPanel panelPath;
	private JLabel labelPathRules;
	private JCheckBox checkBoxPathRules;
	private JLabel labelPathHam;
	private JCheckBox checkBoxPathHam;
	private JLabel labelPathSpam;
	private JCheckBox checkBoxPathSpam;
	
	private JPanel panelUserConf;
	private JPanel panelUserConfLeft;
	private JPanel panelUserConfCenter;
	private JPanel panelUserConfRight;
	private JScrollPane barraRolagem;
	private JTable table;
	
	private JButton b1;
	private JButton b2;
	
	private JPanel panelAutoConf;
	private JPanel panelAutoConfLeft;
	private JPanel panelAutoConfRight;
	private JScrollPane barraRolagem2;
	private JTable table2;
	
	private JLabel FP;
	private JLabel FN;
	private JTextField FPositivos;
	private JTextField FNegativos;
	
	private JLabel FP2;
	private JLabel FN2;
	private JTextField FPositivos2;
	private JTextField FNegativos2;
	
	private JButton b3;
	private JButton b4;

	
	//String dados1[] = new String[(int)file.length()]
	//Object [][] dados;
	
	Object [][] dados = {{"BAYES_00", " "}, {"FREEMAIL_FROM", " "}, {"RDNS_NONE", " "}, {"FREEMAIL_REPLYTO_END_DIGIT", " "}, {"MSOE_MID_WRONG_CASE", " "},
			{"DATE_IN_PAST_24_48", " "}, {"T_LOTS_OF_MONEY", " "}, {"SPF_HELO_FAIL", " "}, {"ALL_TRUSTED", " "}, {"DNS_FROM_RFC_DSN", " "}, {"DATE_IN_PAST_06_12", " "},
			{"HTML_IMAGE_RATIO_04", " "}, {"HTML_MESSAGE", " "}, {"NO_DNS_FOR_FROM", " "}, {"URIBL_GREY", " "}, {"PLING_QUERY", " "}, {"HTTP_ESCAPED_HOST", " "},
			{"MIME_HEADER_CTYPE_ONLY", " "}, {"MIME_HTML_ONLY", " "}, {"NORMAL_HTTP_TO_IP", " "}, {"DATE_IN_PAST_03_06", " "}, {"HK_NAME_DRUGS", " "},
			{"HS_INDEX_PARAM", " "}, {"LOW_PRICE", " "}, {"SPF_SOFTFAIL", " "}, {"MSGID_FROM_MTA_HEADER", " "}, {"RCVD_IN_DNSWL_LOW", " "}, {"MISSING_HEADERS", " "},
			{"NO_RECEIVED", " "}, {"NO_RELAYS", " "}, {"RCVD_IN_NJABL_PROXY", " "}, {"BAYES_05", " "}, {"SPF_FAIL", " "}, {"SUBJECT_NEEDS_ENCODING", " "},
			{"HTML_FONT_FACE_BAD", " "}, {"RCVD_IN_DNSWL_MED", " "}, {"TVD_SPACE_RATIO", " "}, {"SUBJ_DOLLARS", " "}, {"FROM_LOCAL_NOVOWEL", " "},
			{"MIME_QP_LONG_LINE", " "}, {"APOSTROPHE_FROM", " "}, {"RCVD_IN_DNSWL_HI", " "}, {"RCVD_NUMERIC_HELO", " "}, {"HTML_TAG_BALANCE_BODY", " "},
			{"RDNS_DYNAMIC", " "}, {"URIBL_SBL", " "}, {"ACT_NOW_CAPS", " "}, {"BAYES_50", " "}, {"DEAR_EMAIL", " "}, {"DRUGS_ANXIETY", " "},
			{"ENV_AND_HDR_SPF_MATCH", " "}, {"SPF_PASS", " "}, {"URI_HEX", " "}, {"USER_IN_DEF_SPF_WL", " "}, {"SPF_HELO_SOFTFAIL", " "}, {"DATE_IN_PAST_12_24", " "},
			{"RCVD_IN_DNSWL_NONE", " "}, {"FH_HOST_EQ_PACBELL_D", " "}, {"MSGID_SPAM_CAPS", " "}, {"SUBJECT_DRUG_GAP_C", " "}, {"HTML_IMAGE_RATIO_02", " "},
			{"URG_BIZ", " "}, {"FS_START_DOYOU2", " "}, {"XMAILER_MIMEOLE_OL_3AC1D", " "}, {"DATE_IN_FUTURE_12_24", " "}, {"MSGID_RANDY", " "}, {"FREEMAIL_REPLY", " "},
			{"FROM_LOCAL_DIGITS", " "}, {"FROM_LOCAL_HEX", " "}, {"HTTP_EXCESSIVE_ESCAPES", " "}, {"FAKE_REPLY_C", " "}, {"SPF_NEUTRAL", " "},
			{"T_FILL_THIS_FORM_SHORT", " "}, {"BODY_ENHANCEMENT", " "}, {"HK_RANDOM_ENVFROM", " "}, {"MISSING_SUBJECT", " "}, {"SUBJ_BUY", " "},
			{"HTML_TAG_BALANCE_HEAD", " "}, {"MISSING_MIMEOLE", " "}, {"MPART_ALT_DIFF", " "}, {"BASE64_LENGTH_78_79", " "}, {"DIET_1", " "}, {"SUBJ_ALL_CAPS", " "},
			{"TO_NO_BRKTS_DYNIP", " "}, {"BAYES_20", " "}, {"HTML_IMAGE_RATIO_08", " "}, {"HTML_COMMENT_SAVED_URL", " "}, {"WEIRD_PORT", " "}, {"BAYES_40", " "},
			{"MARKETING_PARTNERS", " "}, {"HTML_EMBEDS", " "}, {"HTML_IMAGE_ONLY_28", " "}, {"FRT_ADOBE2", " "}, {"RCVD_IN_NJABL_SPAM", " "},
	        {"ANY_BOUNCE_MESSAGE", " "}, {"VBOUNCE_MESSAGE", " "}, {"RCVD_IN_RP_RNBL", " "}, {"DRUGS_ERECTILE", " "}, {"UNPARSEABLE_RELAY", " "},
	        {"RCVD_IN_SORBS_DUL", " "}, {"FSL_HELO_NON_FQDN_1", " "}, {"HELO_NO_DOMAIN", " "}, {"INVALID_MSGID", " "}, {"HTML_IMAGE_RATIO_06", " "},
	        {"DATE_IN_FUTURE_03_06", " "}, {"FU_COMMON_SUBS2", " "}, {"SPOOF_COM2COM", " "}, {"MIME_HTML_MOSTLY", " "}, {"SPF_HELO_PASS", " "},
	        {"DATE_IN_FUTURE_06_12", " "}, {"TVD_APPROVED", " "}, {"DEAR_SOMETHING", " "}, {"T_FROM_MISSPACED", " "}, {"EXTRA_MPART_TYPE", " "},
	        {"TVD_FW_GRAPHIC_NAME_LONG", " "}, {"TVD_FW_GRAPHIC_NAME_MID", " "}, {"MONEY_FROM_MISSP", " "}, {"FRT_SOMA2", " "}, {"TVD_SUBJ_ACC_NUM", " "},
	        {"RCVD_IN_PBL", " "}, {"BODY_ENHANCEMENT2", " "}, {"FRT_SOMA", " "}, {"MONEY_BACK", " "}, {"FRT_BIGGERMEM1", " "}, {"BAYES_60", " "}, {"CTYPE_001C_B", " "},
	        {"USER_IN_DEF_WHITELIST", " "}, {"RATWARE_MS_HASH", " "}, {"FUZZY_VPILL", " "}, {"FREEMAIL_REPLYTO", " "}, {"UPPERCASE_50_75", " "},
	        {"HTML_FONT_SIZE_LARGE", " "}, {"DATE_IN_FUTURE_96_XX", " "}, {"BAYES_99", " "}, {"FORGED_HOTMAIL_RCVD2", " "}, {"RCVD_IN_BRBL_LASTEXT"," "},
	        {"HTML_IMAGE_ONLY_16", " "}, {"MIME_BASE64_TEXT", " "}, {"HTML_IMAGE_ONLY_24", " "}, {"BAD_CREDIT", " "}, {"BUG6152_INVALID_DATE_TZ_ABSURD", " "},
	        {"INVALID_DATE_TZ_ABSURD", " "}, {"MSGID_OUTLOOK_INVALID", " "}, {"NO_RDNS_DOTCOM_HELO", " "}, {"HTML_IMAGE_ONLY_20", " "}, {"HTML_SHORT_LINK_IMG_3", " "},
	        {"IP_LINK_PLUS", " "}, {"RATWARE_OUTLOOK_NONAME", " "}, {"FORGED_MUA_MOZILLA", " "}, {"INVALID_DATE", " "}, {"NA_DOLLARS", " "}, {"RCVD_FAKE_HELO_DOTCOM", " "},
	        {"FORGED_MUA_OUTLOOK", " "}, {"FROM_ILLEGAL_CHARS", " "}, {"HTML_FONT_LOW_CONTRAST", " "}, {"HTML_OBFUSCATE_05_10", " "}, {"MIME_HTML_ONLY_MULTI", " "},
	        {"MSGID_SHORT", " "}, {"FORGED_YAHOO_RCVD", " "}, {"HTML_IMAGE_ONLY_04", " "}, {"HTML_SHORT_LINK_IMG_1", " "}, {"BANG_GUAR", " "},
	        {"GUARANTEED_100_PERCENT", " "}, {"FIN_FREE", " "}, {"DOS_OE_TO_MX", " "}, {"FORGED_OUTLOOK_HTML", " "}, {"FSL_HELO_BARE_IP_1", " "},
	        {"RCVD_HELO_IP_MISMATCH", " "}, {"TWO_IPS_RCVD", " "}, {"DNS_FROM_RFC_BOGUSMX", " "}, {"FB_NO_SCRIP_NEEDED", " "}, {"FORGED_OUTLOOK_TAGS", " "},
	        {"FREEMAIL_ENVFROM_END_DIGIT", " "}, {"HTML_MIME_NO_HTML_TAG", " "}, {"HTML_SHORT_LINK_IMG_2", " "}, {"NO_PRESCRIPTION", " "}, {"RCVD_IN_SBL", " "},
	        {"ENGLISH_UCE_SUBJECT", " "}, {"FREE_QUOTE_INSTANT", " "}, {"HTML_IMAGE_ONLY_12", " "}, {"HIDE_WIN_STATUS", " "}, {"OBFUSCATING_COMMENT", " "},
	        {"SUSPICIOUS_RECIPS", " "}, {"ADVANCE_FEE_2", " "}, {"FORGED_MUA_IMS", " "}, {"HTML_EXTRA_CLOSE", " "}, {"SUBJECT_DIET", " "}, {"EXCUSE_REMOVE", " "},
	        {"FREEMAIL_FORGED_REPLYTO", " "}, {"EXCUSE_24", " "}, {"TRACKER_ID", " "}, {"SUBJ_ILLEGAL_CHARS", " "}, {"FILL_THIS_FORM_LOAN", " "}, {"T_FILL_THIS_FORM", " "},
	        {"HTML_FONT_SIZE_HUGE", " "}, {"DATE_IN_FUTURE_24_48", " "}, {"FORGED_MUA_EUDORA", " "}, {"RATWARE_EGROUPS", " "}, {"EXCUSE_4", " "}, {"FUZZY_CREDIT", " "},
	        {"HTML_IMAGE_ONLY_08", " "}, {"URIBL_DBL_SPAM", " "}, {"FM_SUBJ_APPROVE", " "}, {"MIME_BASE64_BLANKS", " "}, {"WEIRD_QUOTING", " "},
	        {"DATE_IN_PAST_96_XX", " "}, {"FROM_OFFERS", " "}, {"ADVANCE_FEE_3", " "}, {"ADVANCE_FEE_4", " "}, {"DEAR_FRIEND", " "}, {"MILLION_USD", " "},
	        {"UPPERCASE_75_100", " "}, {"US_DOLLARS_3", " "}, {"TVD_SUBJ_WIPE_DEBT", " "}, {"BAYES_95", " "}, {"DOS_OUTLOOK_TO_MX", " "}, {"SPF_HELO_NEUTRAL", " "},
	        {"FH_DATE_IS_19XX", " "}, {"BAYES_80", " "}, {"FROM_MISSP_FREEMAIL", " "}, {"FH_FROM_CASH", " "}, {"LOCALPART_IN_SUBJECT", " "}, {"UNCLAIMED_MONEY", " "},
	        {"HTML_TITLE_SUBJ_DIFF", " "}, {"HK_NAME_FREE", " "}, {"REPTO_QUOTE_YAHOO", " "}, {"TO_MALFORMED", " "}, {"MPART_ALT_DIFF_COUNT", " "}, {"FR_TITLE_NUMS", " "},
	        {"FILL_THIS_FORM_LONG", " "}, {"FS_LARGE_PERCENT2", " "}, {"REPLYTO_WITHOUT_TO_CC", " "}, {"FORGED_TELESP_RCVD", " "}, {"DYN_RDNS_SHORT_HELO_HTML", " "},
	        {"DOS_OE_TO_MX_IMAGE", " "}, {"PART_CID_STOCK_LESS", " "}, {"FS_WEIGHT_LOSS", " "}, {"GAPPY_SUBJECT", " "}, {"FS_START_LOSE", " "}, {"FB_INCREASE_YOUR", " "},
	        {"MORE_SEX", " "}, {"DRUG_ED_CAPS", " "}, {"DRUG_ED_ONLINE", " "}, {"MISSING_MIME_HB_SEP", " "}, {"T_MIME_NO_TEXT", " "}, {"URIBL_JP_SURBL", " "},
	        {"SHORT_TERM_PRICE", " "}, {"FH_FROMEML_NOTLD", " "}, {"FROM_NO_USER", " "}, {"TVD_INCREASE_SIZE", " "}, {"HK_SCAM_N2", " "}, {"TVD_PH_SUBJ_URGENT", " "},
	        {"FORGED_MUA_OIMO", " "}, {"FUZZY_MILLION", " "}, {"HELO_OEM", " "}, {"URIBL_WS_SURBL", " "}, {"DRUGS_DIET", " "}, {"FB_GVR", " "}, {"HEADER_SPAM", " "},
	        {"FAKE_HELO_MAIL_COM_DOM", " "}, {"IMPOTENCE", " "}, {"HTML_IMAGE_ONLY_32", " "}, {"ONE_TIME", " "}, {"RCVD_ILLEGAL_IP", " "}, {"FS_REPLICA", " "},
	        {"FROM_EXCESS_BASE64", " "}, {"DOS_HIGHBIT_HDRS_BODY", " "}, {"NUMERIC_HTTP_ADDR", " "}, {"URIBL_BLACK", " "}, {"FILL_THIS_FORM_FRAUD_PHISH", " "},
	        {"FORGED_MSGID_YAHOO", " "}, {"FROM_STARTS_WITH_NUMS", " "}, {"SUBJ_YOUR_DEBT", " "}, {"RCVD_IN_IADB_DOPTIN_LT50", " "}, {"RCVD_IN_IADB_LISTED", " "},
	        {"RCVD_IN_NJABL_RELAY", " "}, {"ONLINE_PHARMACY", " "}, {"TVD_VISIT_PHARMA", " "}, {"FB_SAVE_PERSC", " "}, {"FORGED_MUA_THEBAT_BOUN", " "},
	        {"FB_LOSE_WEIGHT_CAP", " "}, {"RCVD_DOUBLE_IP_LOOSE", " "}, {"FB_SPACED_FREE", " "}, {"BASE64_LENGTH_79_INF", " "},  {"SHORT_HELO_AND_INLINE_IMAGE", " "},
	        {"FM_LOTTO_YOU_WON", " "}, {"SUBJ_AS_SEEN", " "}, {"DATE_IN_FUTURE_48_96", " "}, {"HTML_OBFUSCATE_10_20", " "}, {"FUZZY_XPILL", " "}, {"PERCENT_RANDOM", " "},
	        {"TVD_RCVD_SINGLE", " "}, {"FM_SCHOOLING", " "}, {"FM_SCHOOL_DIPLOMA", " "}, {"SORTED_RECIPS", " "}, {"FH_HOST_IN_ADDRARPA", " "}, {"FROM_BLANK_NAME", " "},
	        {"HTML_SHORT_CENTER", " "}, {"MISSING_DATE", " "}, {"MISSING_MID", " "}, {"NO_HEADERS_MESSAGE", " "}, {"MANY_SPAN_IN_TEXT", " "}, {"DYN_RDNS_AND_INLINE_IMAGE", " "},
	        {"TVD_RCVD_IP", " "}, {"TVD_RCVD_IP4", " "}, {"FROM_MISSP_MSFT", " "}, {"SUBJ_YOUR_FAMILY", " "}, {"FRT_DIPLOMA", " "}, {"BANKING_LAWS", " "}, {"RCVD_IN_SORBS_WEB", " "},
	        {"SANE_91eb43f705d25c804374a746d7519660", " "}, {"FH_HELO_EQ_D_D_D_D", " "}, {"HELO_DYNAMIC_DHCP", " "}, {"S25R_6", " "}, {"DRUG_ED_SILD", " "}, {"RATWARE_EFROM", " "},
	        {"FROM_MISSP_DYNIP", " "}, {"FH_FROM_GET_NAME", " "}, {"HTML_NONELEMENT_30_40", " "}, {"T_KHOP_FOREIGN_CLICK", " "}, {"FS_ABIGGER", " "}, {"UNRESOLVED_TEMPLATE", " "},
	        {"FUZZY_PHARMACY", " "}, {"FB_GET_MEDS", " "}, {"HK_LOTTO", " "}, {"KAM_LOTTO1", " "}, {"LOTTO_AGENT", " "}, {"URI_NOVOWEL", " "}, {"FS_NUDE", " "},
	        {"HELO_LOCALHOST", " "}, {"DRUGS_MUSCLE", " "}, {"MSGID_MULTIPLE_AT", " "}, {"BILLION_DOLLARS", " "}, {"TVD_PH_BODY_ACCOUNTS_PRE", " "}, {"FUZZY_PHENT", " "}
	    };
	
	String [] colunas = {"Rules", "Peso"};
    
	
	public GUI() {
		readRules();
		addContentInterface();
		startInterface();
	}
	
	private void addContentInterface() {
		frame = new JFrame("Anti-Spam Controler");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 400);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new GridLayout(4, 0));
		
		panelConfigure = new JPanel();
		panelConfigure.setLayout(new FlowLayout());
		frame.add(panelConfigure);
		
		labelConfigureManual = new JLabel("Configuração Manual");
		panelConfigure.add(labelConfigureManual);
		checkBoxConfigureManual = new JCheckBox(); 
		panelConfigure.add(checkBoxConfigureManual);		
		
		labelConfigureAuto = new JLabel("Configuração Automática");
		panelConfigure.add(labelConfigureAuto);
		checkBoxConfigureAuto = new JCheckBox(); 
		panelConfigure.add(checkBoxConfigureAuto);
		
		panelPath = new JPanel();
		panelPath.setLayout(new FlowLayout());
		frame.add(panelPath);
		
		labelPathRules = new JLabel("Path Rules.cf");
		panelPath.add(labelPathRules);		
		checkBoxPathRules = new JCheckBox(); 
		panelPath.add(checkBoxPathRules);
		
		labelPathHam = new JLabel("Path Ham.log");
		panelPath.add(labelPathHam);
		
		checkBoxPathHam = new JCheckBox(); 
		panelPath.add(checkBoxPathHam);
		
		labelPathSpam = new JLabel("Path Spam.log");
		panelPath.add(labelPathSpam);
		
		checkBoxPathSpam = new JCheckBox(); 
		panelPath.add(checkBoxPathSpam);
		
		//USER CONF
		panelUserConf = new JPanel();
        panelUserConf.setLayout(new GridLayout(1, 2));
        frame.add(panelUserConf);
        
        panelUserConfLeft = new JPanel();
        panelUserConfLeft.setLayout(new GridLayout(1,0));
        panelUserConf.add(panelUserConfLeft);
        
        table = new JTable(dados, colunas);
        barraRolagem = new JScrollPane(table);
        //table.setBounds(100,40,800,400);
        panelUserConfLeft.add(barraRolagem); 	
        
        
        panelUserConfRight = new JPanel();
        panelUserConfRight.setLayout(new GridLayout(2,2));
        panelUserConf.add(panelUserConfRight);
        
        FP = new JLabel("Falsos Positivos");
        panelUserConfRight.add(FP);
        
        FPositivos = new JTextField(0);
        panelUserConfRight.add(FPositivos);
        
        FN = new JLabel("Falsos Negativos");
        panelUserConfRight.add(FN);
        
        FNegativos = new JTextField(0);
        panelUserConfRight.add(FNegativos);
        
        b1 = new JButton("AVALIAR");
        panelUserConf.add(b1);
        b2 = new JButton("GRAVAR");
        panelUserConf.add(b2);
            
        
        //AUTO CONF
        panelAutoConf = new JPanel();
        panelAutoConf.setLayout(new GridLayout(1, 2));
        frame.add(panelAutoConf);
        
        panelAutoConfLeft = new JPanel();
        panelAutoConfLeft.setLayout(new GridLayout(1, 0));
        panelAutoConf.add(panelAutoConfLeft);
        
        
        table2 = new JTable(dados, colunas);
        barraRolagem2 = new JScrollPane(table2);
        panelAutoConfLeft.add(barraRolagem2); 	
		
        
        panelAutoConfRight = new JPanel();
        panelAutoConfRight.setLayout(new GridLayout(2,2));
        panelAutoConf.add(panelAutoConfRight);
        
        FP2 = new JLabel("Falsos Positivos");
        panelAutoConfRight.add(FP2);
        
        FPositivos2 = new JTextField(0);
        panelAutoConfRight.add(FPositivos2);
        
        FN2 = new JLabel("Falsos Negativos");
        panelAutoConfRight.add(FN2);
        
        FNegativos2 = new JTextField(0);
        panelAutoConfRight.add(FNegativos2);
        
        b3 = new JButton("GERAR CONF");
        panelAutoConf.add(b3);
        b4 = new JButton("GRAVAR");
        panelAutoConf.add(b4);
           
	}
	
	public void readRules () {
		Scanner sc;
		try {
			sc = new Scanner(file);
			
			while(sc.hasNextLine()){
				s = sc.nextLine();
				System.out.println(s);
				//dados = {{s," "}};
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

	private void startInterface() {
		//frame.pack();
		frame.setVisible(true);
	}
	
 }
