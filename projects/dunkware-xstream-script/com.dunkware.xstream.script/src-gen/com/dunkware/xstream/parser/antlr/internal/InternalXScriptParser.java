package com.dunkware.xstream.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import com.dunkware.xstream.services.XScriptGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalXScriptParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_DOUBLE", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'svar'", "'('", "','", "')'", "'='", "';'", "'||'", "'&&'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", "'!'", "'true'", "'false'", "'tick'", "'['", "']'", "'exp'", "'{'", "'}'", "'snapshot'", "'roc'", "'avg'", "'sub'", "'ssc'", "'in last'", "'signal'", "'class'", "'var'", "'function'", "'return'", "'signalListener'", "'streamVarListener'", "'functionRunner'", "'++'", "'--'", "'setStreamVar'", "'debug'", "'if'", "'elseif'", "'else'", "'whilst'", "'break'", "'sleep'", "'percentChange'", "'columnStrk'", "'bwd'", "'fwd'", "'sum'", "'diff'", "'value'", "'columnPairStrk'", "'variance'", "'slrAvg'", "'lst'", "'stc'", "'varAvg'", "'varMax'", "'rox'", "'relative'", "'sessionVarValue'", "'sessionVarAgg'", "'sessionSignalCount'", "'historicalSignalCount'", "'query'", "'valueFilter'", "'valueCompareFilter'", "'SEC'", "'MIN'", "'HOUR'", "'STR'", "'INT'", "'BOOl'", "'T'", "'DT'", "'DATE'", "'DUB'", "'LONG'", "'HIGH'", "'LOW'", "'GT'", "'LT'", "'EQ'", "'NE'", "'ROC'", "'DIFF'"
    };
    public static final int T__50=50;
    public static final int T__59=59;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int RULE_INT=5;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__91=91;
    public static final int T__100=100;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__102=102;
    public static final int T__94=94;
    public static final int T__101=101;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__99=99;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__95=95;
    public static final int T__96=96;
    public static final int T__97=97;
    public static final int T__98=98;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=9;
    public static final int RULE_DOUBLE=6;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__84=84;
    public static final int T__104=104;
    public static final int T__85=85;
    public static final int T__103=103;
    public static final int T__86=86;
    public static final int T__87=87;

    // delegates
    // delegators


        public InternalXScriptParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalXScriptParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalXScriptParser.tokenNames; }
    public String getGrammarFileName() { return "InternalXScript.g"; }



     	private XScriptGrammarAccess grammarAccess;

        public InternalXScriptParser(TokenStream input, XScriptGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "XScript";
       	}

       	@Override
       	protected XScriptGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleXScript"
    // InternalXScript.g:65:1: entryRuleXScript returns [EObject current=null] : iv_ruleXScript= ruleXScript EOF ;
    public final EObject entryRuleXScript() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXScript = null;


        try {
            // InternalXScript.g:65:48: (iv_ruleXScript= ruleXScript EOF )
            // InternalXScript.g:66:2: iv_ruleXScript= ruleXScript EOF
            {
             newCompositeNode(grammarAccess.getXScriptRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXScript=ruleXScript();

            state._fsp--;

             current =iv_ruleXScript; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXScript"


    // $ANTLR start "ruleXScript"
    // InternalXScript.g:72:1: ruleXScript returns [EObject current=null] : ( (lv_elements_0_0= ruleScriptElement ) )* ;
    public final EObject ruleXScript() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:78:2: ( ( (lv_elements_0_0= ruleScriptElement ) )* )
            // InternalXScript.g:79:2: ( (lv_elements_0_0= ruleScriptElement ) )*
            {
            // InternalXScript.g:79:2: ( (lv_elements_0_0= ruleScriptElement ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==12||(LA1_0>=45 && LA1_0<=46)||LA1_0==83) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalXScript.g:80:3: (lv_elements_0_0= ruleScriptElement )
            	    {
            	    // InternalXScript.g:80:3: (lv_elements_0_0= ruleScriptElement )
            	    // InternalXScript.g:81:4: lv_elements_0_0= ruleScriptElement
            	    {

            	    				newCompositeNode(grammarAccess.getXScriptAccess().getElementsScriptElementParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_elements_0_0=ruleScriptElement();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getXScriptRule());
            	    				}
            	    				add(
            	    					current,
            	    					"elements",
            	    					lv_elements_0_0,
            	    					"com.dunkware.xstream.XScript.ScriptElement");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXScript"


    // $ANTLR start "entryRuleScriptElement"
    // InternalXScript.g:101:1: entryRuleScriptElement returns [EObject current=null] : iv_ruleScriptElement= ruleScriptElement EOF ;
    public final EObject entryRuleScriptElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScriptElement = null;


        try {
            // InternalXScript.g:101:54: (iv_ruleScriptElement= ruleScriptElement EOF )
            // InternalXScript.g:102:2: iv_ruleScriptElement= ruleScriptElement EOF
            {
             newCompositeNode(grammarAccess.getScriptElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScriptElement=ruleScriptElement();

            state._fsp--;

             current =iv_ruleScriptElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleScriptElement"


    // $ANTLR start "ruleScriptElement"
    // InternalXScript.g:108:1: ruleScriptElement returns [EObject current=null] : this_CoreAbstractElement_0= ruleCoreAbstractElement ;
    public final EObject ruleScriptElement() throws RecognitionException {
        EObject current = null;

        EObject this_CoreAbstractElement_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:114:2: (this_CoreAbstractElement_0= ruleCoreAbstractElement )
            // InternalXScript.g:115:2: this_CoreAbstractElement_0= ruleCoreAbstractElement
            {

            		newCompositeNode(grammarAccess.getScriptElementAccess().getCoreAbstractElementParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_CoreAbstractElement_0=ruleCoreAbstractElement();

            state._fsp--;


            		current = this_CoreAbstractElement_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleScriptElement"


    // $ANTLR start "entryRuleCoreAbstractElement"
    // InternalXScript.g:126:1: entryRuleCoreAbstractElement returns [EObject current=null] : iv_ruleCoreAbstractElement= ruleCoreAbstractElement EOF ;
    public final EObject entryRuleCoreAbstractElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCoreAbstractElement = null;


        try {
            // InternalXScript.g:126:60: (iv_ruleCoreAbstractElement= ruleCoreAbstractElement EOF )
            // InternalXScript.g:127:2: iv_ruleCoreAbstractElement= ruleCoreAbstractElement EOF
            {
             newCompositeNode(grammarAccess.getCoreAbstractElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCoreAbstractElement=ruleCoreAbstractElement();

            state._fsp--;

             current =iv_ruleCoreAbstractElement; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCoreAbstractElement"


    // $ANTLR start "ruleCoreAbstractElement"
    // InternalXScript.g:133:1: ruleCoreAbstractElement returns [EObject current=null] : (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType | this_XQueryType_3= ruleXQueryType ) ;
    public final EObject ruleCoreAbstractElement() throws RecognitionException {
        EObject current = null;

        EObject this_VarType_0 = null;

        EObject this_XClassType_1 = null;

        EObject this_SignalType_2 = null;

        EObject this_XQueryType_3 = null;



        	enterRule();

        try {
            // InternalXScript.g:139:2: ( (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType | this_XQueryType_3= ruleXQueryType ) )
            // InternalXScript.g:140:2: (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType | this_XQueryType_3= ruleXQueryType )
            {
            // InternalXScript.g:140:2: (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType | this_XQueryType_3= ruleXQueryType )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt2=1;
                }
                break;
            case 46:
                {
                alt2=2;
                }
                break;
            case 45:
                {
                alt2=3;
                }
                break;
            case 83:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalXScript.g:141:3: this_VarType_0= ruleVarType
                    {

                    			newCompositeNode(grammarAccess.getCoreAbstractElementAccess().getVarTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarType_0=ruleVarType();

                    state._fsp--;


                    			current = this_VarType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalXScript.g:150:3: this_XClassType_1= ruleXClassType
                    {

                    			newCompositeNode(grammarAccess.getCoreAbstractElementAccess().getXClassTypeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_XClassType_1=ruleXClassType();

                    state._fsp--;


                    			current = this_XClassType_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalXScript.g:159:3: this_SignalType_2= ruleSignalType
                    {

                    			newCompositeNode(grammarAccess.getCoreAbstractElementAccess().getSignalTypeParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_SignalType_2=ruleSignalType();

                    state._fsp--;


                    			current = this_SignalType_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalXScript.g:168:3: this_XQueryType_3= ruleXQueryType
                    {

                    			newCompositeNode(grammarAccess.getCoreAbstractElementAccess().getXQueryTypeParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_XQueryType_3=ruleXQueryType();

                    state._fsp--;


                    			current = this_XQueryType_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCoreAbstractElement"


    // $ANTLR start "entryRuleVarType"
    // InternalXScript.g:180:1: entryRuleVarType returns [EObject current=null] : iv_ruleVarType= ruleVarType EOF ;
    public final EObject entryRuleVarType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarType = null;


        try {
            // InternalXScript.g:180:48: (iv_ruleVarType= ruleVarType EOF )
            // InternalXScript.g:181:2: iv_ruleVarType= ruleVarType EOF
            {
             newCompositeNode(grammarAccess.getVarTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarType=ruleVarType();

            state._fsp--;

             current =iv_ruleVarType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVarType"


    // $ANTLR start "ruleVarType"
    // InternalXScript.g:187:1: ruleVarType returns [EObject current=null] : (otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' ) ;
    public final EObject ruleVarType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_code_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Enumerator lv_type_5_0 = null;

        EObject lv_expression_8_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:193:2: ( (otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' ) )
            // InternalXScript.g:194:2: (otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' )
            {
            // InternalXScript.g:194:2: (otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' )
            // InternalXScript.g:195:3: otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getVarTypeAccess().getSvarKeyword_0());
            		
            // InternalXScript.g:199:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalXScript.g:200:4: (lv_name_1_0= RULE_ID )
            {
            // InternalXScript.g:200:4: (lv_name_1_0= RULE_ID )
            // InternalXScript.g:201:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_1_0, grammarAccess.getVarTypeAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVarTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getVarTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:221:3: ( (lv_code_3_0= RULE_INT ) )
            // InternalXScript.g:222:4: (lv_code_3_0= RULE_INT )
            {
            // InternalXScript.g:222:4: (lv_code_3_0= RULE_INT )
            // InternalXScript.g:223:5: lv_code_3_0= RULE_INT
            {
            lv_code_3_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            					newLeafNode(lv_code_3_0, grammarAccess.getVarTypeAccess().getCodeINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVarTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"code",
            						lv_code_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_8); 

            			newLeafNode(otherlv_4, grammarAccess.getVarTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:243:3: ( (lv_type_5_0= ruleDataType ) )
            // InternalXScript.g:244:4: (lv_type_5_0= ruleDataType )
            {
            // InternalXScript.g:244:4: (lv_type_5_0= ruleDataType )
            // InternalXScript.g:245:5: lv_type_5_0= ruleDataType
            {

            					newCompositeNode(grammarAccess.getVarTypeAccess().getTypeDataTypeEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_type_5_0=ruleDataType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarTypeRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_5_0,
            						"com.dunkware.xstream.XScript.DataType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_10); 

            			newLeafNode(otherlv_6, grammarAccess.getVarTypeAccess().getRightParenthesisKeyword_6());
            		
            otherlv_7=(Token)match(input,16,FOLLOW_11); 

            			newLeafNode(otherlv_7, grammarAccess.getVarTypeAccess().getEqualsSignKeyword_7());
            		
            // InternalXScript.g:270:3: ( (lv_expression_8_0= ruleExpressionType ) )
            // InternalXScript.g:271:4: (lv_expression_8_0= ruleExpressionType )
            {
            // InternalXScript.g:271:4: (lv_expression_8_0= ruleExpressionType )
            // InternalXScript.g:272:5: lv_expression_8_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getVarTypeAccess().getExpressionExpressionTypeParserRuleCall_8_0());
            				
            pushFollow(FOLLOW_12);
            lv_expression_8_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarTypeRule());
            					}
            					set(
            						current,
            						"expression",
            						lv_expression_8_0,
            						"com.dunkware.xstream.XScript.ExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getVarTypeAccess().getSemicolonKeyword_9());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVarType"


    // $ANTLR start "entryRuleExpressionType"
    // InternalXScript.g:297:1: entryRuleExpressionType returns [EObject current=null] : iv_ruleExpressionType= ruleExpressionType EOF ;
    public final EObject entryRuleExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionType = null;


        try {
            // InternalXScript.g:297:55: (iv_ruleExpressionType= ruleExpressionType EOF )
            // InternalXScript.g:298:2: iv_ruleExpressionType= ruleExpressionType EOF
            {
             newCompositeNode(grammarAccess.getExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpressionType=ruleExpressionType();

            state._fsp--;

             current =iv_ruleExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpressionType"


    // $ANTLR start "ruleExpressionType"
    // InternalXScript.g:304:1: ruleExpressionType returns [EObject current=null] : this_OrType_0= ruleOrType ;
    public final EObject ruleExpressionType() throws RecognitionException {
        EObject current = null;

        EObject this_OrType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:310:2: (this_OrType_0= ruleOrType )
            // InternalXScript.g:311:2: this_OrType_0= ruleOrType
            {

            		newCompositeNode(grammarAccess.getExpressionTypeAccess().getOrTypeParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_OrType_0=ruleOrType();

            state._fsp--;


            		current = this_OrType_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpressionType"


    // $ANTLR start "entryRuleOrType"
    // InternalXScript.g:322:1: entryRuleOrType returns [EObject current=null] : iv_ruleOrType= ruleOrType EOF ;
    public final EObject entryRuleOrType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrType = null;


        try {
            // InternalXScript.g:322:47: (iv_ruleOrType= ruleOrType EOF )
            // InternalXScript.g:323:2: iv_ruleOrType= ruleOrType EOF
            {
             newCompositeNode(grammarAccess.getOrTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOrType=ruleOrType();

            state._fsp--;

             current =iv_ruleOrType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOrType"


    // $ANTLR start "ruleOrType"
    // InternalXScript.g:329:1: ruleOrType returns [EObject current=null] : (this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )* ) ;
    public final EObject ruleOrType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AndType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:335:2: ( (this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )* ) )
            // InternalXScript.g:336:2: (this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )* )
            {
            // InternalXScript.g:336:2: (this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )* )
            // InternalXScript.g:337:3: this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )*
            {

            			newCompositeNode(grammarAccess.getOrTypeAccess().getAndTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_13);
            this_AndType_0=ruleAndType();

            state._fsp--;


            			current = this_AndType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:345:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==18) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalXScript.g:346:4: () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) )
            	    {
            	    // InternalXScript.g:346:4: ()
            	    // InternalXScript.g:347:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getOrTypeAccess().getOrTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,18,FOLLOW_11); 

            	    				newLeafNode(otherlv_2, grammarAccess.getOrTypeAccess().getVerticalLineVerticalLineKeyword_1_1());
            	    			
            	    // InternalXScript.g:357:4: ( (lv_right_3_0= ruleAndType ) )
            	    // InternalXScript.g:358:5: (lv_right_3_0= ruleAndType )
            	    {
            	    // InternalXScript.g:358:5: (lv_right_3_0= ruleAndType )
            	    // InternalXScript.g:359:6: lv_right_3_0= ruleAndType
            	    {

            	    						newCompositeNode(grammarAccess.getOrTypeAccess().getRightAndTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_13);
            	    lv_right_3_0=ruleAndType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getOrTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.AndType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOrType"


    // $ANTLR start "entryRuleAndType"
    // InternalXScript.g:381:1: entryRuleAndType returns [EObject current=null] : iv_ruleAndType= ruleAndType EOF ;
    public final EObject entryRuleAndType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndType = null;


        try {
            // InternalXScript.g:381:48: (iv_ruleAndType= ruleAndType EOF )
            // InternalXScript.g:382:2: iv_ruleAndType= ruleAndType EOF
            {
             newCompositeNode(grammarAccess.getAndTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAndType=ruleAndType();

            state._fsp--;

             current =iv_ruleAndType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAndType"


    // $ANTLR start "ruleAndType"
    // InternalXScript.g:388:1: ruleAndType returns [EObject current=null] : (this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )* ) ;
    public final EObject ruleAndType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_EqualityType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:394:2: ( (this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )* ) )
            // InternalXScript.g:395:2: (this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )* )
            {
            // InternalXScript.g:395:2: (this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )* )
            // InternalXScript.g:396:3: this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )*
            {

            			newCompositeNode(grammarAccess.getAndTypeAccess().getEqualityTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_14);
            this_EqualityType_0=ruleEqualityType();

            state._fsp--;


            			current = this_EqualityType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:404:3: ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==19) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalXScript.g:405:4: () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) )
            	    {
            	    // InternalXScript.g:405:4: ()
            	    // InternalXScript.g:406:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAndTypeAccess().getAndTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,19,FOLLOW_11); 

            	    				newLeafNode(otherlv_2, grammarAccess.getAndTypeAccess().getAmpersandAmpersandKeyword_1_1());
            	    			
            	    // InternalXScript.g:416:4: ( (lv_right_3_0= ruleEqualityType ) )
            	    // InternalXScript.g:417:5: (lv_right_3_0= ruleEqualityType )
            	    {
            	    // InternalXScript.g:417:5: (lv_right_3_0= ruleEqualityType )
            	    // InternalXScript.g:418:6: lv_right_3_0= ruleEqualityType
            	    {

            	    						newCompositeNode(grammarAccess.getAndTypeAccess().getRightEqualityTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_right_3_0=ruleEqualityType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getAndTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.EqualityType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAndType"


    // $ANTLR start "entryRuleEqualityType"
    // InternalXScript.g:440:1: entryRuleEqualityType returns [EObject current=null] : iv_ruleEqualityType= ruleEqualityType EOF ;
    public final EObject entryRuleEqualityType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEqualityType = null;


        try {
            // InternalXScript.g:440:53: (iv_ruleEqualityType= ruleEqualityType EOF )
            // InternalXScript.g:441:2: iv_ruleEqualityType= ruleEqualityType EOF
            {
             newCompositeNode(grammarAccess.getEqualityTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEqualityType=ruleEqualityType();

            state._fsp--;

             current =iv_ruleEqualityType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEqualityType"


    // $ANTLR start "ruleEqualityType"
    // InternalXScript.g:447:1: ruleEqualityType returns [EObject current=null] : (this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )* ) ;
    public final EObject ruleEqualityType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_ComparisonType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:453:2: ( (this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )* ) )
            // InternalXScript.g:454:2: (this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )* )
            {
            // InternalXScript.g:454:2: (this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )* )
            // InternalXScript.g:455:3: this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )*
            {

            			newCompositeNode(grammarAccess.getEqualityTypeAccess().getComparisonTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_15);
            this_ComparisonType_0=ruleComparisonType();

            state._fsp--;


            			current = this_ComparisonType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:463:3: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=20 && LA6_0<=21)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalXScript.g:464:4: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) )
            	    {
            	    // InternalXScript.g:464:4: ()
            	    // InternalXScript.g:465:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getEqualityTypeAccess().getEqualityTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:471:4: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // InternalXScript.g:472:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // InternalXScript.g:472:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // InternalXScript.g:473:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // InternalXScript.g:473:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    int alt5=2;
            	    int LA5_0 = input.LA(1);

            	    if ( (LA5_0==20) ) {
            	        alt5=1;
            	    }
            	    else if ( (LA5_0==21) ) {
            	        alt5=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 5, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt5) {
            	        case 1 :
            	            // InternalXScript.g:474:7: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,20,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getEqualityTypeAccess().getOpEqualsSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getEqualityTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:485:7: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,21,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getEqualityTypeAccess().getOpExclamationMarkEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getEqualityTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalXScript.g:498:4: ( (lv_right_3_0= ruleComparisonType ) )
            	    // InternalXScript.g:499:5: (lv_right_3_0= ruleComparisonType )
            	    {
            	    // InternalXScript.g:499:5: (lv_right_3_0= ruleComparisonType )
            	    // InternalXScript.g:500:6: lv_right_3_0= ruleComparisonType
            	    {

            	    						newCompositeNode(grammarAccess.getEqualityTypeAccess().getRightComparisonTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_15);
            	    lv_right_3_0=ruleComparisonType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getEqualityTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.ComparisonType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEqualityType"


    // $ANTLR start "entryRuleComparisonType"
    // InternalXScript.g:522:1: entryRuleComparisonType returns [EObject current=null] : iv_ruleComparisonType= ruleComparisonType EOF ;
    public final EObject entryRuleComparisonType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparisonType = null;


        try {
            // InternalXScript.g:522:55: (iv_ruleComparisonType= ruleComparisonType EOF )
            // InternalXScript.g:523:2: iv_ruleComparisonType= ruleComparisonType EOF
            {
             newCompositeNode(grammarAccess.getComparisonTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComparisonType=ruleComparisonType();

            state._fsp--;

             current =iv_ruleComparisonType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComparisonType"


    // $ANTLR start "ruleComparisonType"
    // InternalXScript.g:529:1: ruleComparisonType returns [EObject current=null] : (this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )* ) ;
    public final EObject ruleComparisonType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        Token lv_op_2_4=null;
        EObject this_PlusOrMinusType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:535:2: ( (this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )* ) )
            // InternalXScript.g:536:2: (this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )* )
            {
            // InternalXScript.g:536:2: (this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )* )
            // InternalXScript.g:537:3: this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )*
            {

            			newCompositeNode(grammarAccess.getComparisonTypeAccess().getPlusOrMinusTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_16);
            this_PlusOrMinusType_0=rulePlusOrMinusType();

            state._fsp--;


            			current = this_PlusOrMinusType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:545:3: ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=22 && LA8_0<=25)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalXScript.g:546:4: () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) )
            	    {
            	    // InternalXScript.g:546:4: ()
            	    // InternalXScript.g:547:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getComparisonTypeAccess().getComparisonTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:553:4: ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) )
            	    // InternalXScript.g:554:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    {
            	    // InternalXScript.g:554:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    // InternalXScript.g:555:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    {
            	    // InternalXScript.g:555:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    int alt7=4;
            	    switch ( input.LA(1) ) {
            	    case 22:
            	        {
            	        alt7=1;
            	        }
            	        break;
            	    case 23:
            	        {
            	        alt7=2;
            	        }
            	        break;
            	    case 24:
            	        {
            	        alt7=3;
            	        }
            	        break;
            	    case 25:
            	        {
            	        alt7=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 7, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt7) {
            	        case 1 :
            	            // InternalXScript.g:556:7: lv_op_2_1= '>='
            	            {
            	            lv_op_2_1=(Token)match(input,22,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getComparisonTypeAccess().getOpGreaterThanSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:567:7: lv_op_2_2= '<='
            	            {
            	            lv_op_2_2=(Token)match(input,23,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getComparisonTypeAccess().getOpLessThanSignEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalXScript.g:578:7: lv_op_2_3= '>'
            	            {
            	            lv_op_2_3=(Token)match(input,24,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_3, grammarAccess.getComparisonTypeAccess().getOpGreaterThanSignKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalXScript.g:589:7: lv_op_2_4= '<'
            	            {
            	            lv_op_2_4=(Token)match(input,25,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_4, grammarAccess.getComparisonTypeAccess().getOpLessThanSignKeyword_1_1_0_3());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_4, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalXScript.g:602:4: ( (lv_right_3_0= rulePlusOrMinusType ) )
            	    // InternalXScript.g:603:5: (lv_right_3_0= rulePlusOrMinusType )
            	    {
            	    // InternalXScript.g:603:5: (lv_right_3_0= rulePlusOrMinusType )
            	    // InternalXScript.g:604:6: lv_right_3_0= rulePlusOrMinusType
            	    {

            	    						newCompositeNode(grammarAccess.getComparisonTypeAccess().getRightPlusOrMinusTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_16);
            	    lv_right_3_0=rulePlusOrMinusType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getComparisonTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.PlusOrMinusType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComparisonType"


    // $ANTLR start "entryRulePlusOrMinusType"
    // InternalXScript.g:626:1: entryRulePlusOrMinusType returns [EObject current=null] : iv_rulePlusOrMinusType= rulePlusOrMinusType EOF ;
    public final EObject entryRulePlusOrMinusType() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlusOrMinusType = null;


        try {
            // InternalXScript.g:626:56: (iv_rulePlusOrMinusType= rulePlusOrMinusType EOF )
            // InternalXScript.g:627:2: iv_rulePlusOrMinusType= rulePlusOrMinusType EOF
            {
             newCompositeNode(grammarAccess.getPlusOrMinusTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePlusOrMinusType=rulePlusOrMinusType();

            state._fsp--;

             current =iv_rulePlusOrMinusType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePlusOrMinusType"


    // $ANTLR start "rulePlusOrMinusType"
    // InternalXScript.g:633:1: rulePlusOrMinusType returns [EObject current=null] : (this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )* ) ;
    public final EObject rulePlusOrMinusType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_MulOrDivType_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:639:2: ( (this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )* ) )
            // InternalXScript.g:640:2: (this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )* )
            {
            // InternalXScript.g:640:2: (this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )* )
            // InternalXScript.g:641:3: this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )*
            {

            			newCompositeNode(grammarAccess.getPlusOrMinusTypeAccess().getMulOrDivTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_17);
            this_MulOrDivType_0=ruleMulOrDivType();

            state._fsp--;


            			current = this_MulOrDivType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:649:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=26 && LA10_0<=27)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalXScript.g:650:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) )
            	    {
            	    // InternalXScript.g:650:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt9=2;
            	    int LA9_0 = input.LA(1);

            	    if ( (LA9_0==26) ) {
            	        alt9=1;
            	    }
            	    else if ( (LA9_0==27) ) {
            	        alt9=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 9, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt9) {
            	        case 1 :
            	            // InternalXScript.g:651:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalXScript.g:651:5: ( () otherlv_2= '+' )
            	            // InternalXScript.g:652:6: () otherlv_2= '+'
            	            {
            	            // InternalXScript.g:652:6: ()
            	            // InternalXScript.g:653:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getPlusOrMinusTypeAccess().getPlusTypeLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,26,FOLLOW_11); 

            	            						newLeafNode(otherlv_2, grammarAccess.getPlusOrMinusTypeAccess().getPlusSignKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:665:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalXScript.g:665:5: ( () otherlv_4= '-' )
            	            // InternalXScript.g:666:6: () otherlv_4= '-'
            	            {
            	            // InternalXScript.g:666:6: ()
            	            // InternalXScript.g:667:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getPlusOrMinusTypeAccess().getMinusTypeLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,27,FOLLOW_11); 

            	            						newLeafNode(otherlv_4, grammarAccess.getPlusOrMinusTypeAccess().getHyphenMinusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalXScript.g:679:4: ( (lv_right_5_0= ruleMulOrDivType ) )
            	    // InternalXScript.g:680:5: (lv_right_5_0= ruleMulOrDivType )
            	    {
            	    // InternalXScript.g:680:5: (lv_right_5_0= ruleMulOrDivType )
            	    // InternalXScript.g:681:6: lv_right_5_0= ruleMulOrDivType
            	    {

            	    						newCompositeNode(grammarAccess.getPlusOrMinusTypeAccess().getRightMulOrDivTypeParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_17);
            	    lv_right_5_0=ruleMulOrDivType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getPlusOrMinusTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_5_0,
            	    							"com.dunkware.xstream.XScript.MulOrDivType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePlusOrMinusType"


    // $ANTLR start "entryRuleMulOrDivType"
    // InternalXScript.g:703:1: entryRuleMulOrDivType returns [EObject current=null] : iv_ruleMulOrDivType= ruleMulOrDivType EOF ;
    public final EObject entryRuleMulOrDivType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMulOrDivType = null;


        try {
            // InternalXScript.g:703:53: (iv_ruleMulOrDivType= ruleMulOrDivType EOF )
            // InternalXScript.g:704:2: iv_ruleMulOrDivType= ruleMulOrDivType EOF
            {
             newCompositeNode(grammarAccess.getMulOrDivTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMulOrDivType=ruleMulOrDivType();

            state._fsp--;

             current =iv_ruleMulOrDivType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMulOrDivType"


    // $ANTLR start "ruleMulOrDivType"
    // InternalXScript.g:710:1: ruleMulOrDivType returns [EObject current=null] : (this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )* ) ;
    public final EObject ruleMulOrDivType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_PrimaryType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:716:2: ( (this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )* ) )
            // InternalXScript.g:717:2: (this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )* )
            {
            // InternalXScript.g:717:2: (this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )* )
            // InternalXScript.g:718:3: this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )*
            {

            			newCompositeNode(grammarAccess.getMulOrDivTypeAccess().getPrimaryTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_18);
            this_PrimaryType_0=rulePrimaryType();

            state._fsp--;


            			current = this_PrimaryType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:726:3: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=28 && LA12_0<=29)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalXScript.g:727:4: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) )
            	    {
            	    // InternalXScript.g:727:4: ()
            	    // InternalXScript.g:728:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getMulOrDivTypeAccess().getMulOrDivTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:734:4: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // InternalXScript.g:735:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // InternalXScript.g:735:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // InternalXScript.g:736:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // InternalXScript.g:736:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt11=2;
            	    int LA11_0 = input.LA(1);

            	    if ( (LA11_0==28) ) {
            	        alt11=1;
            	    }
            	    else if ( (LA11_0==29) ) {
            	        alt11=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 11, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt11) {
            	        case 1 :
            	            // InternalXScript.g:737:7: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,28,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getMulOrDivTypeAccess().getOpAsteriskKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMulOrDivTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:748:7: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,29,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getMulOrDivTypeAccess().getOpSolidusKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMulOrDivTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalXScript.g:761:4: ( (lv_right_3_0= rulePrimaryType ) )
            	    // InternalXScript.g:762:5: (lv_right_3_0= rulePrimaryType )
            	    {
            	    // InternalXScript.g:762:5: (lv_right_3_0= rulePrimaryType )
            	    // InternalXScript.g:763:6: lv_right_3_0= rulePrimaryType
            	    {

            	    						newCompositeNode(grammarAccess.getMulOrDivTypeAccess().getRightPrimaryTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_18);
            	    lv_right_3_0=rulePrimaryType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getMulOrDivTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.PrimaryType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMulOrDivType"


    // $ANTLR start "entryRulePrimaryType"
    // InternalXScript.g:785:1: entryRulePrimaryType returns [EObject current=null] : iv_rulePrimaryType= rulePrimaryType EOF ;
    public final EObject entryRulePrimaryType() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryType = null;


        try {
            // InternalXScript.g:785:52: (iv_rulePrimaryType= rulePrimaryType EOF )
            // InternalXScript.g:786:2: iv_rulePrimaryType= rulePrimaryType EOF
            {
             newCompositeNode(grammarAccess.getPrimaryTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimaryType=rulePrimaryType();

            state._fsp--;

             current =iv_rulePrimaryType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePrimaryType"


    // $ANTLR start "rulePrimaryType"
    // InternalXScript.g:792:1: rulePrimaryType returns [EObject current=null] : ( (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) ) | this_AtomicType_6= ruleAtomicType ) ;
    public final EObject rulePrimaryType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_ExpressionType_1 = null;

        EObject lv_expression_5_0 = null;

        EObject this_AtomicType_6 = null;



        	enterRule();

        try {
            // InternalXScript.g:798:2: ( ( (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) ) | this_AtomicType_6= ruleAtomicType ) )
            // InternalXScript.g:799:2: ( (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) ) | this_AtomicType_6= ruleAtomicType )
            {
            // InternalXScript.g:799:2: ( (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) ) | this_AtomicType_6= ruleAtomicType )
            int alt13=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt13=1;
                }
                break;
            case 30:
                {
                alt13=2;
                }
                break;
            case RULE_ID:
            case RULE_INT:
            case RULE_DOUBLE:
            case RULE_STRING:
            case 31:
            case 32:
            case 33:
            case 36:
            case 37:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
                {
                alt13=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // InternalXScript.g:800:3: (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' )
                    {
                    // InternalXScript.g:800:3: (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' )
                    // InternalXScript.g:801:4: otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,13,FOLLOW_11); 

                    				newLeafNode(otherlv_0, grammarAccess.getPrimaryTypeAccess().getLeftParenthesisKeyword_0_0());
                    			

                    				newCompositeNode(grammarAccess.getPrimaryTypeAccess().getExpressionTypeParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_9);
                    this_ExpressionType_1=ruleExpressionType();

                    state._fsp--;


                    				current = this_ExpressionType_1;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_2=(Token)match(input,15,FOLLOW_2); 

                    				newLeafNode(otherlv_2, grammarAccess.getPrimaryTypeAccess().getRightParenthesisKeyword_0_2());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:819:3: ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) )
                    {
                    // InternalXScript.g:819:3: ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) )
                    // InternalXScript.g:820:4: () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) )
                    {
                    // InternalXScript.g:820:4: ()
                    // InternalXScript.g:821:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getPrimaryTypeAccess().getNotTypeAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_4=(Token)match(input,30,FOLLOW_11); 

                    				newLeafNode(otherlv_4, grammarAccess.getPrimaryTypeAccess().getExclamationMarkKeyword_1_1());
                    			
                    // InternalXScript.g:831:4: ( (lv_expression_5_0= rulePrimaryType ) )
                    // InternalXScript.g:832:5: (lv_expression_5_0= rulePrimaryType )
                    {
                    // InternalXScript.g:832:5: (lv_expression_5_0= rulePrimaryType )
                    // InternalXScript.g:833:6: lv_expression_5_0= rulePrimaryType
                    {

                    						newCompositeNode(grammarAccess.getPrimaryTypeAccess().getExpressionPrimaryTypeParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_expression_5_0=rulePrimaryType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPrimaryTypeRule());
                    						}
                    						set(
                    							current,
                    							"expression",
                    							lv_expression_5_0,
                    							"com.dunkware.xstream.XScript.PrimaryType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:852:3: this_AtomicType_6= ruleAtomicType
                    {

                    			newCompositeNode(grammarAccess.getPrimaryTypeAccess().getAtomicTypeParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_AtomicType_6=ruleAtomicType();

                    state._fsp--;


                    			current = this_AtomicType_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePrimaryType"


    // $ANTLR start "entryRuleAtomicType"
    // InternalXScript.g:864:1: entryRuleAtomicType returns [EObject current=null] : iv_ruleAtomicType= ruleAtomicType EOF ;
    public final EObject entryRuleAtomicType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicType = null;


        try {
            // InternalXScript.g:864:51: (iv_ruleAtomicType= ruleAtomicType EOF )
            // InternalXScript.g:865:2: iv_ruleAtomicType= ruleAtomicType EOF
            {
             newCompositeNode(grammarAccess.getAtomicTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAtomicType=ruleAtomicType();

            state._fsp--;

             current =iv_ruleAtomicType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAtomicType"


    // $ANTLR start "ruleAtomicType"
    // InternalXScript.g:871:1: ruleAtomicType returns [EObject current=null] : this_AtomicBaseType_0= ruleAtomicBaseType ;
    public final EObject ruleAtomicType() throws RecognitionException {
        EObject current = null;

        EObject this_AtomicBaseType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:877:2: (this_AtomicBaseType_0= ruleAtomicBaseType )
            // InternalXScript.g:878:2: this_AtomicBaseType_0= ruleAtomicBaseType
            {

            		newCompositeNode(grammarAccess.getAtomicTypeAccess().getAtomicBaseTypeParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_AtomicBaseType_0=ruleAtomicBaseType();

            state._fsp--;


            		current = this_AtomicBaseType_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAtomicType"


    // $ANTLR start "entryRuleAtomicBaseType"
    // InternalXScript.g:889:1: entryRuleAtomicBaseType returns [EObject current=null] : iv_ruleAtomicBaseType= ruleAtomicBaseType EOF ;
    public final EObject entryRuleAtomicBaseType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicBaseType = null;


        try {
            // InternalXScript.g:889:55: (iv_ruleAtomicBaseType= ruleAtomicBaseType EOF )
            // InternalXScript.g:890:2: iv_ruleAtomicBaseType= ruleAtomicBaseType EOF
            {
             newCompositeNode(grammarAccess.getAtomicBaseTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAtomicBaseType=ruleAtomicBaseType();

            state._fsp--;

             current =iv_ruleAtomicBaseType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAtomicBaseType"


    // $ANTLR start "ruleAtomicBaseType"
    // InternalXScript.g:896:1: ruleAtomicBaseType returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_SessionSignalExpressionType_17= ruleSessionSignalExpressionType ) ;
    public final EObject ruleAtomicBaseType() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;
        Token lv_value_3_0=null;
        Token lv_value_5_0=null;
        Token lv_value_7_1=null;
        Token lv_value_7_2=null;
        EObject this_TickExpressionType_8 = null;

        EObject this_SnapshotExpressionType_9 = null;

        EObject this_SetExpressionType_10 = null;

        EObject this_RocExpressionType_11 = null;

        EObject this_AvgExpressionType_12 = null;

        EObject this_VariableValueExpType_13 = null;

        EObject this_VariableValueRangeType_14 = null;

        EObject this_VariableValueType_15 = null;

        EObject this_SubExpressionType_16 = null;

        EObject this_SessionSignalExpressionType_17 = null;



        	enterRule();

        try {
            // InternalXScript.g:902:2: ( ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_SessionSignalExpressionType_17= ruleSessionSignalExpressionType ) )
            // InternalXScript.g:903:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_SessionSignalExpressionType_17= ruleSessionSignalExpressionType )
            {
            // InternalXScript.g:903:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_SessionSignalExpressionType_17= ruleSessionSignalExpressionType )
            int alt15=14;
            alt15 = dfa15.predict(input);
            switch (alt15) {
                case 1 :
                    // InternalXScript.g:904:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    {
                    // InternalXScript.g:904:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    // InternalXScript.g:905:4: () ( (lv_value_1_0= RULE_DOUBLE ) )
                    {
                    // InternalXScript.g:905:4: ()
                    // InternalXScript.g:906:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicBaseTypeAccess().getDoubleConstantTypeAction_0_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:912:4: ( (lv_value_1_0= RULE_DOUBLE ) )
                    // InternalXScript.g:913:5: (lv_value_1_0= RULE_DOUBLE )
                    {
                    // InternalXScript.g:913:5: (lv_value_1_0= RULE_DOUBLE )
                    // InternalXScript.g:914:6: lv_value_1_0= RULE_DOUBLE
                    {
                    lv_value_1_0=(Token)match(input,RULE_DOUBLE,FOLLOW_2); 

                    						newLeafNode(lv_value_1_0, grammarAccess.getAtomicBaseTypeAccess().getValueDOUBLETerminalRuleCall_0_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicBaseTypeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_1_0,
                    							"com.dunkware.xstream.XScript.DOUBLE");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:932:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    {
                    // InternalXScript.g:932:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    // InternalXScript.g:933:4: () ( (lv_value_3_0= RULE_INT ) )
                    {
                    // InternalXScript.g:933:4: ()
                    // InternalXScript.g:934:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicBaseTypeAccess().getIntConstantTypeAction_1_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:940:4: ( (lv_value_3_0= RULE_INT ) )
                    // InternalXScript.g:941:5: (lv_value_3_0= RULE_INT )
                    {
                    // InternalXScript.g:941:5: (lv_value_3_0= RULE_INT )
                    // InternalXScript.g:942:6: lv_value_3_0= RULE_INT
                    {
                    lv_value_3_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_value_3_0, grammarAccess.getAtomicBaseTypeAccess().getValueINTTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicBaseTypeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_3_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:960:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    {
                    // InternalXScript.g:960:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    // InternalXScript.g:961:4: () ( (lv_value_5_0= RULE_STRING ) )
                    {
                    // InternalXScript.g:961:4: ()
                    // InternalXScript.g:962:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicBaseTypeAccess().getStringConstantTypeAction_2_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:968:4: ( (lv_value_5_0= RULE_STRING ) )
                    // InternalXScript.g:969:5: (lv_value_5_0= RULE_STRING )
                    {
                    // InternalXScript.g:969:5: (lv_value_5_0= RULE_STRING )
                    // InternalXScript.g:970:6: lv_value_5_0= RULE_STRING
                    {
                    lv_value_5_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_value_5_0, grammarAccess.getAtomicBaseTypeAccess().getValueSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicBaseTypeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_5_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalXScript.g:988:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    {
                    // InternalXScript.g:988:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    // InternalXScript.g:989:4: () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    {
                    // InternalXScript.g:989:4: ()
                    // InternalXScript.g:990:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicBaseTypeAccess().getBoolConstantTypeAction_3_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:996:4: ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    // InternalXScript.g:997:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    {
                    // InternalXScript.g:997:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    // InternalXScript.g:998:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    {
                    // InternalXScript.g:998:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==31) ) {
                        alt14=1;
                    }
                    else if ( (LA14_0==32) ) {
                        alt14=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 14, 0, input);

                        throw nvae;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalXScript.g:999:7: lv_value_7_1= 'true'
                            {
                            lv_value_7_1=(Token)match(input,31,FOLLOW_2); 

                            							newLeafNode(lv_value_7_1, grammarAccess.getAtomicBaseTypeAccess().getValueTrueKeyword_3_1_0_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getAtomicBaseTypeRule());
                            							}
                            							setWithLastConsumed(current, "value", lv_value_7_1, null);
                            						

                            }
                            break;
                        case 2 :
                            // InternalXScript.g:1010:7: lv_value_7_2= 'false'
                            {
                            lv_value_7_2=(Token)match(input,32,FOLLOW_2); 

                            							newLeafNode(lv_value_7_2, grammarAccess.getAtomicBaseTypeAccess().getValueFalseKeyword_3_1_0_1());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getAtomicBaseTypeRule());
                            							}
                            							setWithLastConsumed(current, "value", lv_value_7_2, null);
                            						

                            }
                            break;

                    }


                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalXScript.g:1025:3: this_TickExpressionType_8= ruleTickExpressionType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getTickExpressionTypeParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_TickExpressionType_8=ruleTickExpressionType();

                    state._fsp--;


                    			current = this_TickExpressionType_8;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalXScript.g:1034:3: this_SnapshotExpressionType_9= ruleSnapshotExpressionType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getSnapshotExpressionTypeParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_SnapshotExpressionType_9=ruleSnapshotExpressionType();

                    state._fsp--;


                    			current = this_SnapshotExpressionType_9;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalXScript.g:1043:3: this_SetExpressionType_10= ruleSetExpressionType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getSetExpressionTypeParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_SetExpressionType_10=ruleSetExpressionType();

                    state._fsp--;


                    			current = this_SetExpressionType_10;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalXScript.g:1052:3: this_RocExpressionType_11= ruleRocExpressionType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getRocExpressionTypeParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_RocExpressionType_11=ruleRocExpressionType();

                    state._fsp--;


                    			current = this_RocExpressionType_11;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 9 :
                    // InternalXScript.g:1061:3: this_AvgExpressionType_12= ruleAvgExpressionType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getAvgExpressionTypeParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_AvgExpressionType_12=ruleAvgExpressionType();

                    state._fsp--;


                    			current = this_AvgExpressionType_12;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 10 :
                    // InternalXScript.g:1070:3: this_VariableValueExpType_13= ruleVariableValueExpType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getVariableValueExpTypeParserRuleCall_9());
                    		
                    pushFollow(FOLLOW_2);
                    this_VariableValueExpType_13=ruleVariableValueExpType();

                    state._fsp--;


                    			current = this_VariableValueExpType_13;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 11 :
                    // InternalXScript.g:1079:3: this_VariableValueRangeType_14= ruleVariableValueRangeType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getVariableValueRangeTypeParserRuleCall_10());
                    		
                    pushFollow(FOLLOW_2);
                    this_VariableValueRangeType_14=ruleVariableValueRangeType();

                    state._fsp--;


                    			current = this_VariableValueRangeType_14;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 12 :
                    // InternalXScript.g:1088:3: this_VariableValueType_15= ruleVariableValueType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getVariableValueTypeParserRuleCall_11());
                    		
                    pushFollow(FOLLOW_2);
                    this_VariableValueType_15=ruleVariableValueType();

                    state._fsp--;


                    			current = this_VariableValueType_15;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 13 :
                    // InternalXScript.g:1097:3: this_SubExpressionType_16= ruleSubExpressionType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getSubExpressionTypeParserRuleCall_12());
                    		
                    pushFollow(FOLLOW_2);
                    this_SubExpressionType_16=ruleSubExpressionType();

                    state._fsp--;


                    			current = this_SubExpressionType_16;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 14 :
                    // InternalXScript.g:1106:3: this_SessionSignalExpressionType_17= ruleSessionSignalExpressionType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getSessionSignalExpressionTypeParserRuleCall_13());
                    		
                    pushFollow(FOLLOW_2);
                    this_SessionSignalExpressionType_17=ruleSessionSignalExpressionType();

                    state._fsp--;


                    			current = this_SessionSignalExpressionType_17;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAtomicBaseType"


    // $ANTLR start "entryRuleTickExpressionType"
    // InternalXScript.g:1118:1: entryRuleTickExpressionType returns [EObject current=null] : iv_ruleTickExpressionType= ruleTickExpressionType EOF ;
    public final EObject entryRuleTickExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTickExpressionType = null;


        try {
            // InternalXScript.g:1118:59: (iv_ruleTickExpressionType= ruleTickExpressionType EOF )
            // InternalXScript.g:1119:2: iv_ruleTickExpressionType= ruleTickExpressionType EOF
            {
             newCompositeNode(grammarAccess.getTickExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTickExpressionType=ruleTickExpressionType();

            state._fsp--;

             current =iv_ruleTickExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTickExpressionType"


    // $ANTLR start "ruleTickExpressionType"
    // InternalXScript.g:1125:1: ruleTickExpressionType returns [EObject current=null] : ( () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')' ) ;
    public final EObject ruleTickExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_type_3_0=null;
        Token otherlv_4=null;
        Token lv_field_5_0=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_dataType_7_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1131:2: ( ( () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')' ) )
            // InternalXScript.g:1132:2: ( () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:1132:2: ( () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')' )
            // InternalXScript.g:1133:3: () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')'
            {
            // InternalXScript.g:1133:3: ()
            // InternalXScript.g:1134:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTickExpressionTypeAccess().getTickExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,33,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getTickExpressionTypeAccess().getTickKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getTickExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1148:3: ( (lv_type_3_0= RULE_INT ) )
            // InternalXScript.g:1149:4: (lv_type_3_0= RULE_INT )
            {
            // InternalXScript.g:1149:4: (lv_type_3_0= RULE_INT )
            // InternalXScript.g:1150:5: lv_type_3_0= RULE_INT
            {
            lv_type_3_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            					newLeafNode(lv_type_3_0, grammarAccess.getTickExpressionTypeAccess().getTypeINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTickExpressionTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"type",
            						lv_type_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getTickExpressionTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:1170:3: ( (lv_field_5_0= RULE_INT ) )
            // InternalXScript.g:1171:4: (lv_field_5_0= RULE_INT )
            {
            // InternalXScript.g:1171:4: (lv_field_5_0= RULE_INT )
            // InternalXScript.g:1172:5: lv_field_5_0= RULE_INT
            {
            lv_field_5_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            					newLeafNode(lv_field_5_0, grammarAccess.getTickExpressionTypeAccess().getFieldINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTickExpressionTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"field",
            						lv_field_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_8); 

            			newLeafNode(otherlv_6, grammarAccess.getTickExpressionTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:1192:3: ( (lv_dataType_7_0= ruleDataType ) )
            // InternalXScript.g:1193:4: (lv_dataType_7_0= ruleDataType )
            {
            // InternalXScript.g:1193:4: (lv_dataType_7_0= ruleDataType )
            // InternalXScript.g:1194:5: lv_dataType_7_0= ruleDataType
            {

            					newCompositeNode(grammarAccess.getTickExpressionTypeAccess().getDataTypeDataTypeEnumRuleCall_7_0());
            				
            pushFollow(FOLLOW_9);
            lv_dataType_7_0=ruleDataType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTickExpressionTypeRule());
            					}
            					set(
            						current,
            						"dataType",
            						lv_dataType_7_0,
            						"com.dunkware.xstream.XScript.DataType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getTickExpressionTypeAccess().getRightParenthesisKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTickExpressionType"


    // $ANTLR start "entryRuleVariableValueRangeType"
    // InternalXScript.g:1219:1: entryRuleVariableValueRangeType returns [EObject current=null] : iv_ruleVariableValueRangeType= ruleVariableValueRangeType EOF ;
    public final EObject entryRuleVariableValueRangeType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableValueRangeType = null;


        try {
            // InternalXScript.g:1219:63: (iv_ruleVariableValueRangeType= ruleVariableValueRangeType EOF )
            // InternalXScript.g:1220:2: iv_ruleVariableValueRangeType= ruleVariableValueRangeType EOF
            {
             newCompositeNode(grammarAccess.getVariableValueRangeTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableValueRangeType=ruleVariableValueRangeType();

            state._fsp--;

             current =iv_ruleVariableValueRangeType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableValueRangeType"


    // $ANTLR start "ruleVariableValueRangeType"
    // InternalXScript.g:1226:1: ruleVariableValueRangeType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']' ) ;
    public final EObject ruleVariableValueRangeType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_startIndex_3_0=null;
        Token otherlv_4=null;
        Token lv_endIndex_5_0=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalXScript.g:1232:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']' ) )
            // InternalXScript.g:1233:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']' )
            {
            // InternalXScript.g:1233:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']' )
            // InternalXScript.g:1234:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']'
            {
            // InternalXScript.g:1234:3: ()
            // InternalXScript.g:1235:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableValueRangeTypeAccess().getVariableValueRangeTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:1241:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:1242:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:1242:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:1243:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableValueRangeTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(otherlv_1, grammarAccess.getVariableValueRangeTypeAccess().getTargetVarVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,34,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getVariableValueRangeTypeAccess().getLeftSquareBracketKeyword_2());
            		
            // InternalXScript.g:1258:3: ( (lv_startIndex_3_0= RULE_INT ) )
            // InternalXScript.g:1259:4: (lv_startIndex_3_0= RULE_INT )
            {
            // InternalXScript.g:1259:4: (lv_startIndex_3_0= RULE_INT )
            // InternalXScript.g:1260:5: lv_startIndex_3_0= RULE_INT
            {
            lv_startIndex_3_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            					newLeafNode(lv_startIndex_3_0, grammarAccess.getVariableValueRangeTypeAccess().getStartIndexINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableValueRangeTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"startIndex",
            						lv_startIndex_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getVariableValueRangeTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:1280:3: ( (lv_endIndex_5_0= RULE_INT ) )
            // InternalXScript.g:1281:4: (lv_endIndex_5_0= RULE_INT )
            {
            // InternalXScript.g:1281:4: (lv_endIndex_5_0= RULE_INT )
            // InternalXScript.g:1282:5: lv_endIndex_5_0= RULE_INT
            {
            lv_endIndex_5_0=(Token)match(input,RULE_INT,FOLLOW_20); 

            					newLeafNode(lv_endIndex_5_0, grammarAccess.getVariableValueRangeTypeAccess().getEndIndexINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableValueRangeTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"endIndex",
            						lv_endIndex_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_6=(Token)match(input,35,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getVariableValueRangeTypeAccess().getRightSquareBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableValueRangeType"


    // $ANTLR start "entryRuleVariableValueType"
    // InternalXScript.g:1306:1: entryRuleVariableValueType returns [EObject current=null] : iv_ruleVariableValueType= ruleVariableValueType EOF ;
    public final EObject entryRuleVariableValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableValueType = null;


        try {
            // InternalXScript.g:1306:58: (iv_ruleVariableValueType= ruleVariableValueType EOF )
            // InternalXScript.g:1307:2: iv_ruleVariableValueType= ruleVariableValueType EOF
            {
             newCompositeNode(grammarAccess.getVariableValueTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableValueType=ruleVariableValueType();

            state._fsp--;

             current =iv_ruleVariableValueType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableValueType"


    // $ANTLR start "ruleVariableValueType"
    // InternalXScript.g:1313:1: ruleVariableValueType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' ) ) ;
    public final EObject ruleVariableValueType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_indexInt_3_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_expType_6_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1319:2: ( ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' ) ) )
            // InternalXScript.g:1320:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' ) )
            {
            // InternalXScript.g:1320:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' ) )
            // InternalXScript.g:1321:3: () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' )
            {
            // InternalXScript.g:1321:3: ()
            // InternalXScript.g:1322:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableValueTypeAccess().getVariableValueTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:1328:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:1329:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:1329:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:1330:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableValueTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(otherlv_1, grammarAccess.getVariableValueTypeAccess().getVariableVarTypeCrossReference_1_0());
            				

            }


            }

            // InternalXScript.g:1341:3: (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' )
            // InternalXScript.g:1342:4: otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']'
            {
            otherlv_2=(Token)match(input,34,FOLLOW_21); 

            				newLeafNode(otherlv_2, grammarAccess.getVariableValueTypeAccess().getLeftSquareBracketKeyword_2_0());
            			
            // InternalXScript.g:1346:4: ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_INT) ) {
                alt16=1;
            }
            else if ( (LA16_0==36) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalXScript.g:1347:5: ( (lv_indexInt_3_0= RULE_INT ) )
                    {
                    // InternalXScript.g:1347:5: ( (lv_indexInt_3_0= RULE_INT ) )
                    // InternalXScript.g:1348:6: (lv_indexInt_3_0= RULE_INT )
                    {
                    // InternalXScript.g:1348:6: (lv_indexInt_3_0= RULE_INT )
                    // InternalXScript.g:1349:7: lv_indexInt_3_0= RULE_INT
                    {
                    lv_indexInt_3_0=(Token)match(input,RULE_INT,FOLLOW_20); 

                    							newLeafNode(lv_indexInt_3_0, grammarAccess.getVariableValueTypeAccess().getIndexIntINTTerminalRuleCall_2_1_0_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getVariableValueTypeRule());
                    							}
                    							setWithLastConsumed(
                    								current,
                    								"indexInt",
                    								lv_indexInt_3_0,
                    								"org.eclipse.xtext.common.Terminals.INT");
                    						

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:1366:5: (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' )
                    {
                    // InternalXScript.g:1366:5: (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' )
                    // InternalXScript.g:1367:6: otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')'
                    {
                    otherlv_4=(Token)match(input,36,FOLLOW_5); 

                    						newLeafNode(otherlv_4, grammarAccess.getVariableValueTypeAccess().getExpKeyword_2_1_1_0());
                    					
                    otherlv_5=(Token)match(input,13,FOLLOW_11); 

                    						newLeafNode(otherlv_5, grammarAccess.getVariableValueTypeAccess().getLeftParenthesisKeyword_2_1_1_1());
                    					
                    // InternalXScript.g:1375:6: ( (lv_expType_6_0= ruleExpressionType ) )
                    // InternalXScript.g:1376:7: (lv_expType_6_0= ruleExpressionType )
                    {
                    // InternalXScript.g:1376:7: (lv_expType_6_0= ruleExpressionType )
                    // InternalXScript.g:1377:8: lv_expType_6_0= ruleExpressionType
                    {

                    								newCompositeNode(grammarAccess.getVariableValueTypeAccess().getExpTypeExpressionTypeParserRuleCall_2_1_1_2_0());
                    							
                    pushFollow(FOLLOW_9);
                    lv_expType_6_0=ruleExpressionType();

                    state._fsp--;


                    								if (current==null) {
                    									current = createModelElementForParent(grammarAccess.getVariableValueTypeRule());
                    								}
                    								set(
                    									current,
                    									"expType",
                    									lv_expType_6_0,
                    									"com.dunkware.xstream.XScript.ExpressionType");
                    								afterParserOrEnumRuleCall();
                    							

                    }


                    }

                    otherlv_7=(Token)match(input,15,FOLLOW_20); 

                    						newLeafNode(otherlv_7, grammarAccess.getVariableValueTypeAccess().getRightParenthesisKeyword_2_1_1_3());
                    					

                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,35,FOLLOW_2); 

            				newLeafNode(otherlv_8, grammarAccess.getVariableValueTypeAccess().getRightSquareBracketKeyword_2_2());
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableValueType"


    // $ANTLR start "entryRuleVariableValueExpType"
    // InternalXScript.g:1409:1: entryRuleVariableValueExpType returns [EObject current=null] : iv_ruleVariableValueExpType= ruleVariableValueExpType EOF ;
    public final EObject entryRuleVariableValueExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableValueExpType = null;


        try {
            // InternalXScript.g:1409:61: (iv_ruleVariableValueExpType= ruleVariableValueExpType EOF )
            // InternalXScript.g:1410:2: iv_ruleVariableValueExpType= ruleVariableValueExpType EOF
            {
             newCompositeNode(grammarAccess.getVariableValueExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableValueExpType=ruleVariableValueExpType();

            state._fsp--;

             current =iv_ruleVariableValueExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableValueExpType"


    // $ANTLR start "ruleVariableValueExpType"
    // InternalXScript.g:1416:1: ruleVariableValueExpType returns [EObject current=null] : ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')' ) ;
    public final EObject ruleVariableValueExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expType_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1422:2: ( ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')' ) )
            // InternalXScript.g:1423:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:1423:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            // InternalXScript.g:1424:3: () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')'
            {
            // InternalXScript.g:1424:3: ()
            // InternalXScript.g:1425:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableValueExpTypeAccess().getVariableValueExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,36,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getVariableValueExpTypeAccess().getExpKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getVariableValueExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1439:3: ( (lv_expType_3_0= ruleExpressionType ) )
            // InternalXScript.g:1440:4: (lv_expType_3_0= ruleExpressionType )
            {
            // InternalXScript.g:1440:4: (lv_expType_3_0= ruleExpressionType )
            // InternalXScript.g:1441:5: lv_expType_3_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getVariableValueExpTypeAccess().getExpTypeExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_9);
            lv_expType_3_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVariableValueExpTypeRule());
            					}
            					set(
            						current,
            						"expType",
            						lv_expType_3_0,
            						"com.dunkware.xstream.XScript.ExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getVariableValueExpTypeAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableValueExpType"


    // $ANTLR start "entryRuleSetExpressionType"
    // InternalXScript.g:1466:1: entryRuleSetExpressionType returns [EObject current=null] : iv_ruleSetExpressionType= ruleSetExpressionType EOF ;
    public final EObject entryRuleSetExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetExpressionType = null;


        try {
            // InternalXScript.g:1466:58: (iv_ruleSetExpressionType= ruleSetExpressionType EOF )
            // InternalXScript.g:1467:2: iv_ruleSetExpressionType= ruleSetExpressionType EOF
            {
             newCompositeNode(grammarAccess.getSetExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSetExpressionType=ruleSetExpressionType();

            state._fsp--;

             current =iv_ruleSetExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSetExpressionType"


    // $ANTLR start "ruleSetExpressionType"
    // InternalXScript.g:1473:1: ruleSetExpressionType returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleSetExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_args_2_0 = null;

        EObject lv_args_4_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1479:2: ( ( () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}' ) )
            // InternalXScript.g:1480:2: ( () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}' )
            {
            // InternalXScript.g:1480:2: ( () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}' )
            // InternalXScript.g:1481:3: () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}'
            {
            // InternalXScript.g:1481:3: ()
            // InternalXScript.g:1482:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSetExpressionTypeAccess().getSetExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,37,FOLLOW_22); 

            			newLeafNode(otherlv_1, grammarAccess.getSetExpressionTypeAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalXScript.g:1492:3: ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( ((LA18_0>=RULE_ID && LA18_0<=RULE_STRING)||LA18_0==13||(LA18_0>=30 && LA18_0<=33)||(LA18_0>=36 && LA18_0<=37)||(LA18_0>=39 && LA18_0<=43)) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // InternalXScript.g:1493:4: ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )*
                    {
                    // InternalXScript.g:1493:4: ( (lv_args_2_0= ruleExpressionType ) )
                    // InternalXScript.g:1494:5: (lv_args_2_0= ruleExpressionType )
                    {
                    // InternalXScript.g:1494:5: (lv_args_2_0= ruleExpressionType )
                    // InternalXScript.g:1495:6: lv_args_2_0= ruleExpressionType
                    {

                    						newCompositeNode(grammarAccess.getSetExpressionTypeAccess().getArgsExpressionTypeParserRuleCall_2_0_0());
                    					
                    pushFollow(FOLLOW_23);
                    lv_args_2_0=ruleExpressionType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getSetExpressionTypeRule());
                    						}
                    						add(
                    							current,
                    							"args",
                    							lv_args_2_0,
                    							"com.dunkware.xstream.XScript.ExpressionType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalXScript.g:1512:4: (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==14) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalXScript.g:1513:5: otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) )
                    	    {
                    	    otherlv_3=(Token)match(input,14,FOLLOW_11); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getSetExpressionTypeAccess().getCommaKeyword_2_1_0());
                    	    				
                    	    // InternalXScript.g:1517:5: ( (lv_args_4_0= ruleExpressionType ) )
                    	    // InternalXScript.g:1518:6: (lv_args_4_0= ruleExpressionType )
                    	    {
                    	    // InternalXScript.g:1518:6: (lv_args_4_0= ruleExpressionType )
                    	    // InternalXScript.g:1519:7: lv_args_4_0= ruleExpressionType
                    	    {

                    	    							newCompositeNode(grammarAccess.getSetExpressionTypeAccess().getArgsExpressionTypeParserRuleCall_2_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_23);
                    	    lv_args_4_0=ruleExpressionType();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getSetExpressionTypeRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"args",
                    	    								lv_args_4_0,
                    	    								"com.dunkware.xstream.XScript.ExpressionType");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,38,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getSetExpressionTypeAccess().getRightCurlyBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSetExpressionType"


    // $ANTLR start "entryRuleSnapshotExpressionType"
    // InternalXScript.g:1546:1: entryRuleSnapshotExpressionType returns [EObject current=null] : iv_ruleSnapshotExpressionType= ruleSnapshotExpressionType EOF ;
    public final EObject entryRuleSnapshotExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSnapshotExpressionType = null;


        try {
            // InternalXScript.g:1546:63: (iv_ruleSnapshotExpressionType= ruleSnapshotExpressionType EOF )
            // InternalXScript.g:1547:2: iv_ruleSnapshotExpressionType= ruleSnapshotExpressionType EOF
            {
             newCompositeNode(grammarAccess.getSnapshotExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSnapshotExpressionType=ruleSnapshotExpressionType();

            state._fsp--;

             current =iv_ruleSnapshotExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSnapshotExpressionType"


    // $ANTLR start "ruleSnapshotExpressionType"
    // InternalXScript.g:1553:1: ruleSnapshotExpressionType returns [EObject current=null] : ( () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) ) ;
    public final EObject ruleSnapshotExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_interval_5_0=null;
        EObject lv_target_3_0 = null;

        Enumerator lv_time_6_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1559:2: ( ( () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) ) )
            // InternalXScript.g:1560:2: ( () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) )
            {
            // InternalXScript.g:1560:2: ( () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) )
            // InternalXScript.g:1561:3: () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) )
            {
            // InternalXScript.g:1561:3: ()
            // InternalXScript.g:1562:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSnapshotExpressionTypeAccess().getSnapshotExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,39,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getSnapshotExpressionTypeAccess().getSnapshotKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getSnapshotExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1576:3: ( (lv_target_3_0= ruleExpressionType ) )
            // InternalXScript.g:1577:4: (lv_target_3_0= ruleExpressionType )
            {
            // InternalXScript.g:1577:4: (lv_target_3_0= ruleExpressionType )
            // InternalXScript.g:1578:5: lv_target_3_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getSnapshotExpressionTypeAccess().getTargetExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_9);
            lv_target_3_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSnapshotExpressionTypeRule());
            					}
            					set(
            						current,
            						"target",
            						lv_target_3_0,
            						"com.dunkware.xstream.XScript.ExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getSnapshotExpressionTypeAccess().getRightParenthesisKeyword_4());
            		
            // InternalXScript.g:1599:3: ( (lv_interval_5_0= RULE_INT ) )
            // InternalXScript.g:1600:4: (lv_interval_5_0= RULE_INT )
            {
            // InternalXScript.g:1600:4: (lv_interval_5_0= RULE_INT )
            // InternalXScript.g:1601:5: lv_interval_5_0= RULE_INT
            {
            lv_interval_5_0=(Token)match(input,RULE_INT,FOLLOW_24); 

            					newLeafNode(lv_interval_5_0, grammarAccess.getSnapshotExpressionTypeAccess().getIntervalINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSnapshotExpressionTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"interval",
            						lv_interval_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalXScript.g:1617:3: ( (lv_time_6_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:1618:4: (lv_time_6_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:1618:4: (lv_time_6_0= ruleStreamTimeUnit )
            // InternalXScript.g:1619:5: lv_time_6_0= ruleStreamTimeUnit
            {

            					newCompositeNode(grammarAccess.getSnapshotExpressionTypeAccess().getTimeStreamTimeUnitEnumRuleCall_6_0());
            				
            pushFollow(FOLLOW_2);
            lv_time_6_0=ruleStreamTimeUnit();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSnapshotExpressionTypeRule());
            					}
            					set(
            						current,
            						"time",
            						lv_time_6_0,
            						"com.dunkware.xstream.XScript.StreamTimeUnit");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSnapshotExpressionType"


    // $ANTLR start "entryRuleRocExpressionType"
    // InternalXScript.g:1640:1: entryRuleRocExpressionType returns [EObject current=null] : iv_ruleRocExpressionType= ruleRocExpressionType EOF ;
    public final EObject entryRuleRocExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRocExpressionType = null;


        try {
            // InternalXScript.g:1640:58: (iv_ruleRocExpressionType= ruleRocExpressionType EOF )
            // InternalXScript.g:1641:2: iv_ruleRocExpressionType= ruleRocExpressionType EOF
            {
             newCompositeNode(grammarAccess.getRocExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRocExpressionType=ruleRocExpressionType();

            state._fsp--;

             current =iv_ruleRocExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRocExpressionType"


    // $ANTLR start "ruleRocExpressionType"
    // InternalXScript.g:1647:1: ruleRocExpressionType returns [EObject current=null] : ( () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')' ) ;
    public final EObject ruleRocExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_target_3_0 = null;

        EObject lv_compare_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1653:2: ( ( () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:1654:2: ( () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:1654:2: ( () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:1655:3: () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:1655:3: ()
            // InternalXScript.g:1656:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getRocExpressionTypeAccess().getRocExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,40,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getRocExpressionTypeAccess().getRocKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getRocExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1670:3: ( (lv_target_3_0= ruleExpressionType ) )
            // InternalXScript.g:1671:4: (lv_target_3_0= ruleExpressionType )
            {
            // InternalXScript.g:1671:4: (lv_target_3_0= ruleExpressionType )
            // InternalXScript.g:1672:5: lv_target_3_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getRocExpressionTypeAccess().getTargetExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_target_3_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRocExpressionTypeRule());
            					}
            					set(
            						current,
            						"target",
            						lv_target_3_0,
            						"com.dunkware.xstream.XScript.ExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_11); 

            			newLeafNode(otherlv_4, grammarAccess.getRocExpressionTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:1693:3: ( (lv_compare_5_0= ruleExpressionType ) )
            // InternalXScript.g:1694:4: (lv_compare_5_0= ruleExpressionType )
            {
            // InternalXScript.g:1694:4: (lv_compare_5_0= ruleExpressionType )
            // InternalXScript.g:1695:5: lv_compare_5_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getRocExpressionTypeAccess().getCompareExpressionTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_compare_5_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRocExpressionTypeRule());
            					}
            					set(
            						current,
            						"compare",
            						lv_compare_5_0,
            						"com.dunkware.xstream.XScript.ExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getRocExpressionTypeAccess().getRightParenthesisKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRocExpressionType"


    // $ANTLR start "entryRuleAvgExpressionType"
    // InternalXScript.g:1720:1: entryRuleAvgExpressionType returns [EObject current=null] : iv_ruleAvgExpressionType= ruleAvgExpressionType EOF ;
    public final EObject entryRuleAvgExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAvgExpressionType = null;


        try {
            // InternalXScript.g:1720:58: (iv_ruleAvgExpressionType= ruleAvgExpressionType EOF )
            // InternalXScript.g:1721:2: iv_ruleAvgExpressionType= ruleAvgExpressionType EOF
            {
             newCompositeNode(grammarAccess.getAvgExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAvgExpressionType=ruleAvgExpressionType();

            state._fsp--;

             current =iv_ruleAvgExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAvgExpressionType"


    // $ANTLR start "ruleAvgExpressionType"
    // InternalXScript.g:1727:1: ruleAvgExpressionType returns [EObject current=null] : ( () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')' ) ;
    public final EObject ruleAvgExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_target_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1733:2: ( ( () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')' ) )
            // InternalXScript.g:1734:2: ( () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:1734:2: ( () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')' )
            // InternalXScript.g:1735:3: () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')'
            {
            // InternalXScript.g:1735:3: ()
            // InternalXScript.g:1736:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAvgExpressionTypeAccess().getAvgExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,41,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getAvgExpressionTypeAccess().getAvgKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_25); 

            			newLeafNode(otherlv_2, grammarAccess.getAvgExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1750:3: ( (lv_target_3_0= ruleSetExpressionType ) )
            // InternalXScript.g:1751:4: (lv_target_3_0= ruleSetExpressionType )
            {
            // InternalXScript.g:1751:4: (lv_target_3_0= ruleSetExpressionType )
            // InternalXScript.g:1752:5: lv_target_3_0= ruleSetExpressionType
            {

            					newCompositeNode(grammarAccess.getAvgExpressionTypeAccess().getTargetSetExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_9);
            lv_target_3_0=ruleSetExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAvgExpressionTypeRule());
            					}
            					set(
            						current,
            						"target",
            						lv_target_3_0,
            						"com.dunkware.xstream.XScript.SetExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getAvgExpressionTypeAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAvgExpressionType"


    // $ANTLR start "entryRuleSubExpressionType"
    // InternalXScript.g:1777:1: entryRuleSubExpressionType returns [EObject current=null] : iv_ruleSubExpressionType= ruleSubExpressionType EOF ;
    public final EObject entryRuleSubExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubExpressionType = null;


        try {
            // InternalXScript.g:1777:58: (iv_ruleSubExpressionType= ruleSubExpressionType EOF )
            // InternalXScript.g:1778:2: iv_ruleSubExpressionType= ruleSubExpressionType EOF
            {
             newCompositeNode(grammarAccess.getSubExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSubExpressionType=ruleSubExpressionType();

            state._fsp--;

             current =iv_ruleSubExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSubExpressionType"


    // $ANTLR start "ruleSubExpressionType"
    // InternalXScript.g:1784:1: ruleSubExpressionType returns [EObject current=null] : ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')' ) ;
    public final EObject ruleSubExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_target_3_0 = null;

        EObject lv_compare_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1790:2: ( ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:1791:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:1791:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')' )
            // InternalXScript.g:1792:3: () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:1792:3: ()
            // InternalXScript.g:1793:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSubExpressionTypeAccess().getSubExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,42,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getSubExpressionTypeAccess().getSubKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getSubExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1807:3: ( (lv_target_3_0= ruleVariableValueType ) )
            // InternalXScript.g:1808:4: (lv_target_3_0= ruleVariableValueType )
            {
            // InternalXScript.g:1808:4: (lv_target_3_0= ruleVariableValueType )
            // InternalXScript.g:1809:5: lv_target_3_0= ruleVariableValueType
            {

            					newCompositeNode(grammarAccess.getSubExpressionTypeAccess().getTargetVariableValueTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_target_3_0=ruleVariableValueType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSubExpressionTypeRule());
            					}
            					set(
            						current,
            						"target",
            						lv_target_3_0,
            						"com.dunkware.xstream.XScript.VariableValueType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getSubExpressionTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:1830:3: ( (lv_compare_5_0= ruleVariableValueType ) )
            // InternalXScript.g:1831:4: (lv_compare_5_0= ruleVariableValueType )
            {
            // InternalXScript.g:1831:4: (lv_compare_5_0= ruleVariableValueType )
            // InternalXScript.g:1832:5: lv_compare_5_0= ruleVariableValueType
            {

            					newCompositeNode(grammarAccess.getSubExpressionTypeAccess().getCompareVariableValueTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_compare_5_0=ruleVariableValueType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSubExpressionTypeRule());
            					}
            					set(
            						current,
            						"compare",
            						lv_compare_5_0,
            						"com.dunkware.xstream.XScript.VariableValueType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getSubExpressionTypeAccess().getRightParenthesisKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSubExpressionType"


    // $ANTLR start "entryRuleSessionSignalExpressionType"
    // InternalXScript.g:1857:1: entryRuleSessionSignalExpressionType returns [EObject current=null] : iv_ruleSessionSignalExpressionType= ruleSessionSignalExpressionType EOF ;
    public final EObject entryRuleSessionSignalExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSessionSignalExpressionType = null;


        try {
            // InternalXScript.g:1857:68: (iv_ruleSessionSignalExpressionType= ruleSessionSignalExpressionType EOF )
            // InternalXScript.g:1858:2: iv_ruleSessionSignalExpressionType= ruleSessionSignalExpressionType EOF
            {
             newCompositeNode(grammarAccess.getSessionSignalExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSessionSignalExpressionType=ruleSessionSignalExpressionType();

            state._fsp--;

             current =iv_ruleSessionSignalExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSessionSignalExpressionType"


    // $ANTLR start "ruleSessionSignalExpressionType"
    // InternalXScript.g:1864:1: ruleSessionSignalExpressionType returns [EObject current=null] : ( () otherlv_1= 'ssc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) ( ( (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' ) ) ) ( (lv_count_5_0= RULE_INT ) ) otherlv_6= 'in last' ( (lv_time_7_0= RULE_INT ) ) ( (lv_unit_8_0= ruleStreamTimeUnit ) ) otherlv_9= ')' ) ;
    public final EObject ruleSessionSignalExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token lv_op_4_1=null;
        Token lv_op_4_2=null;
        Token lv_op_4_3=null;
        Token lv_count_5_0=null;
        Token otherlv_6=null;
        Token lv_time_7_0=null;
        Token otherlv_9=null;
        Enumerator lv_unit_8_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1870:2: ( ( () otherlv_1= 'ssc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) ( ( (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' ) ) ) ( (lv_count_5_0= RULE_INT ) ) otherlv_6= 'in last' ( (lv_time_7_0= RULE_INT ) ) ( (lv_unit_8_0= ruleStreamTimeUnit ) ) otherlv_9= ')' ) )
            // InternalXScript.g:1871:2: ( () otherlv_1= 'ssc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) ( ( (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' ) ) ) ( (lv_count_5_0= RULE_INT ) ) otherlv_6= 'in last' ( (lv_time_7_0= RULE_INT ) ) ( (lv_unit_8_0= ruleStreamTimeUnit ) ) otherlv_9= ')' )
            {
            // InternalXScript.g:1871:2: ( () otherlv_1= 'ssc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) ( ( (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' ) ) ) ( (lv_count_5_0= RULE_INT ) ) otherlv_6= 'in last' ( (lv_time_7_0= RULE_INT ) ) ( (lv_unit_8_0= ruleStreamTimeUnit ) ) otherlv_9= ')' )
            // InternalXScript.g:1872:3: () otherlv_1= 'ssc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) ( ( (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' ) ) ) ( (lv_count_5_0= RULE_INT ) ) otherlv_6= 'in last' ( (lv_time_7_0= RULE_INT ) ) ( (lv_unit_8_0= ruleStreamTimeUnit ) ) otherlv_9= ')'
            {
            // InternalXScript.g:1872:3: ()
            // InternalXScript.g:1873:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSessionSignalExpressionTypeAccess().getSessionSignalExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,43,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getSessionSignalExpressionTypeAccess().getSscKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getSessionSignalExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1887:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:1888:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:1888:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:1889:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSessionSignalExpressionTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_26); 

            					newLeafNode(otherlv_3, grammarAccess.getSessionSignalExpressionTypeAccess().getSignalSignalTypeCrossReference_3_0());
            				

            }


            }

            // InternalXScript.g:1900:3: ( ( (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' ) ) )
            // InternalXScript.g:1901:4: ( (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' ) )
            {
            // InternalXScript.g:1901:4: ( (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' ) )
            // InternalXScript.g:1902:5: (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' )
            {
            // InternalXScript.g:1902:5: (lv_op_4_1= '>' | lv_op_4_2= '<' | lv_op_4_3= '=' )
            int alt19=3;
            switch ( input.LA(1) ) {
            case 24:
                {
                alt19=1;
                }
                break;
            case 25:
                {
                alt19=2;
                }
                break;
            case 16:
                {
                alt19=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // InternalXScript.g:1903:6: lv_op_4_1= '>'
                    {
                    lv_op_4_1=(Token)match(input,24,FOLLOW_6); 

                    						newLeafNode(lv_op_4_1, grammarAccess.getSessionSignalExpressionTypeAccess().getOpGreaterThanSignKeyword_4_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSessionSignalExpressionTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_4_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:1914:6: lv_op_4_2= '<'
                    {
                    lv_op_4_2=(Token)match(input,25,FOLLOW_6); 

                    						newLeafNode(lv_op_4_2, grammarAccess.getSessionSignalExpressionTypeAccess().getOpLessThanSignKeyword_4_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSessionSignalExpressionTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_4_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:1925:6: lv_op_4_3= '='
                    {
                    lv_op_4_3=(Token)match(input,16,FOLLOW_6); 

                    						newLeafNode(lv_op_4_3, grammarAccess.getSessionSignalExpressionTypeAccess().getOpEqualsSignKeyword_4_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getSessionSignalExpressionTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_4_3, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalXScript.g:1938:3: ( (lv_count_5_0= RULE_INT ) )
            // InternalXScript.g:1939:4: (lv_count_5_0= RULE_INT )
            {
            // InternalXScript.g:1939:4: (lv_count_5_0= RULE_INT )
            // InternalXScript.g:1940:5: lv_count_5_0= RULE_INT
            {
            lv_count_5_0=(Token)match(input,RULE_INT,FOLLOW_27); 

            					newLeafNode(lv_count_5_0, grammarAccess.getSessionSignalExpressionTypeAccess().getCountINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSessionSignalExpressionTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"count",
            						lv_count_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_6=(Token)match(input,44,FOLLOW_6); 

            			newLeafNode(otherlv_6, grammarAccess.getSessionSignalExpressionTypeAccess().getInLastKeyword_6());
            		
            // InternalXScript.g:1960:3: ( (lv_time_7_0= RULE_INT ) )
            // InternalXScript.g:1961:4: (lv_time_7_0= RULE_INT )
            {
            // InternalXScript.g:1961:4: (lv_time_7_0= RULE_INT )
            // InternalXScript.g:1962:5: lv_time_7_0= RULE_INT
            {
            lv_time_7_0=(Token)match(input,RULE_INT,FOLLOW_24); 

            					newLeafNode(lv_time_7_0, grammarAccess.getSessionSignalExpressionTypeAccess().getTimeINTTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSessionSignalExpressionTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"time",
            						lv_time_7_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalXScript.g:1978:3: ( (lv_unit_8_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:1979:4: (lv_unit_8_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:1979:4: (lv_unit_8_0= ruleStreamTimeUnit )
            // InternalXScript.g:1980:5: lv_unit_8_0= ruleStreamTimeUnit
            {

            					newCompositeNode(grammarAccess.getSessionSignalExpressionTypeAccess().getUnitStreamTimeUnitEnumRuleCall_8_0());
            				
            pushFollow(FOLLOW_9);
            lv_unit_8_0=ruleStreamTimeUnit();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSessionSignalExpressionTypeRule());
            					}
            					set(
            						current,
            						"unit",
            						lv_unit_8_0,
            						"com.dunkware.xstream.XScript.StreamTimeUnit");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_9, grammarAccess.getSessionSignalExpressionTypeAccess().getRightParenthesisKeyword_9());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSessionSignalExpressionType"


    // $ANTLR start "entryRuleSignalType"
    // InternalXScript.g:2005:1: entryRuleSignalType returns [EObject current=null] : iv_ruleSignalType= ruleSignalType EOF ;
    public final EObject entryRuleSignalType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignalType = null;


        try {
            // InternalXScript.g:2005:51: (iv_ruleSignalType= ruleSignalType EOF )
            // InternalXScript.g:2006:2: iv_ruleSignalType= ruleSignalType EOF
            {
             newCompositeNode(grammarAccess.getSignalTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSignalType=ruleSignalType();

            state._fsp--;

             current =iv_ruleSignalType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSignalType"


    // $ANTLR start "ruleSignalType"
    // InternalXScript.g:2012:1: ruleSignalType returns [EObject current=null] : ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' ) ;
    public final EObject ruleSignalType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_id_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalXScript.g:2018:2: ( ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' ) )
            // InternalXScript.g:2019:2: ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' )
            {
            // InternalXScript.g:2019:2: ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' )
            // InternalXScript.g:2020:3: () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';'
            {
            // InternalXScript.g:2020:3: ()
            // InternalXScript.g:2021:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSignalTypeAccess().getSignalTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,45,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getSignalTypeAccess().getSignalKeyword_1());
            		
            // InternalXScript.g:2031:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2032:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2032:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2033:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_2_0, grammarAccess.getSignalTypeAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSignalTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_3, grammarAccess.getSignalTypeAccess().getLeftParenthesisKeyword_3());
            		
            // InternalXScript.g:2053:3: ( (lv_id_4_0= RULE_INT ) )
            // InternalXScript.g:2054:4: (lv_id_4_0= RULE_INT )
            {
            // InternalXScript.g:2054:4: (lv_id_4_0= RULE_INT )
            // InternalXScript.g:2055:5: lv_id_4_0= RULE_INT
            {
            lv_id_4_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            					newLeafNode(lv_id_4_0, grammarAccess.getSignalTypeAccess().getIdINTTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSignalTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"id",
            						lv_id_4_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_5=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_5, grammarAccess.getSignalTypeAccess().getRightParenthesisKeyword_5());
            		
            otherlv_6=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getSignalTypeAccess().getSemicolonKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSignalType"


    // $ANTLR start "entryRuleXClassType"
    // InternalXScript.g:2083:1: entryRuleXClassType returns [EObject current=null] : iv_ruleXClassType= ruleXClassType EOF ;
    public final EObject entryRuleXClassType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassType = null;


        try {
            // InternalXScript.g:2083:51: (iv_ruleXClassType= ruleXClassType EOF )
            // InternalXScript.g:2084:2: iv_ruleXClassType= ruleXClassType EOF
            {
             newCompositeNode(grammarAccess.getXClassTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXClassType=ruleXClassType();

            state._fsp--;

             current =iv_ruleXClassType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXClassType"


    // $ANTLR start "ruleXClassType"
    // InternalXScript.g:2090:1: ruleXClassType returns [EObject current=null] : ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' ) ;
    public final EObject ruleXClassType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token lv_symbolFilter_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_elements_7_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2096:2: ( ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' ) )
            // InternalXScript.g:2097:2: ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' )
            {
            // InternalXScript.g:2097:2: ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' )
            // InternalXScript.g:2098:3: () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}'
            {
            // InternalXScript.g:2098:3: ()
            // InternalXScript.g:2099:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXClassTypeAccess().getXClassTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,46,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXClassTypeAccess().getClassKeyword_1());
            		
            // InternalXScript.g:2109:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2110:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2110:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2111:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_28); 

            					newLeafNode(lv_name_2_0, grammarAccess.getXClassTypeAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXClassTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalXScript.g:2127:3: (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==13) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalXScript.g:2128:4: otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_29); 

                    				newLeafNode(otherlv_3, grammarAccess.getXClassTypeAccess().getLeftParenthesisKeyword_3_0());
                    			
                    // InternalXScript.g:2132:4: ( (lv_symbolFilter_4_0= RULE_STRING ) )
                    // InternalXScript.g:2133:5: (lv_symbolFilter_4_0= RULE_STRING )
                    {
                    // InternalXScript.g:2133:5: (lv_symbolFilter_4_0= RULE_STRING )
                    // InternalXScript.g:2134:6: lv_symbolFilter_4_0= RULE_STRING
                    {
                    lv_symbolFilter_4_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

                    						newLeafNode(lv_symbolFilter_4_0, grammarAccess.getXClassTypeAccess().getSymbolFilterSTRINGTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXClassTypeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"symbolFilter",
                    							lv_symbolFilter_4_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }

                    otherlv_5=(Token)match(input,15,FOLLOW_25); 

                    				newLeafNode(otherlv_5, grammarAccess.getXClassTypeAccess().getRightParenthesisKeyword_3_2());
                    			

                    }
                    break;

            }

            otherlv_6=(Token)match(input,37,FOLLOW_30); 

            			newLeafNode(otherlv_6, grammarAccess.getXClassTypeAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalXScript.g:2159:3: ( (lv_elements_7_0= ruleXClassElementType ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=47 && LA21_0<=48)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalXScript.g:2160:4: (lv_elements_7_0= ruleXClassElementType )
            	    {
            	    // InternalXScript.g:2160:4: (lv_elements_7_0= ruleXClassElementType )
            	    // InternalXScript.g:2161:5: lv_elements_7_0= ruleXClassElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXClassTypeAccess().getElementsXClassElementTypeParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_30);
            	    lv_elements_7_0=ruleXClassElementType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXClassTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_7_0,
            	    						"com.dunkware.xstream.XScript.XClassElementType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            otherlv_8=(Token)match(input,38,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getXClassTypeAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXClassType"


    // $ANTLR start "entryRuleXClassElementType"
    // InternalXScript.g:2186:1: entryRuleXClassElementType returns [EObject current=null] : iv_ruleXClassElementType= ruleXClassElementType EOF ;
    public final EObject entryRuleXClassElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassElementType = null;


        try {
            // InternalXScript.g:2186:58: (iv_ruleXClassElementType= ruleXClassElementType EOF )
            // InternalXScript.g:2187:2: iv_ruleXClassElementType= ruleXClassElementType EOF
            {
             newCompositeNode(grammarAccess.getXClassElementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXClassElementType=ruleXClassElementType();

            state._fsp--;

             current =iv_ruleXClassElementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXClassElementType"


    // $ANTLR start "ruleXClassElementType"
    // InternalXScript.g:2193:1: ruleXClassElementType returns [EObject current=null] : this_XClassCoreElementType_0= ruleXClassCoreElementType ;
    public final EObject ruleXClassElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XClassCoreElementType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2199:2: (this_XClassCoreElementType_0= ruleXClassCoreElementType )
            // InternalXScript.g:2200:2: this_XClassCoreElementType_0= ruleXClassCoreElementType
            {

            		newCompositeNode(grammarAccess.getXClassElementTypeAccess().getXClassCoreElementTypeParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_XClassCoreElementType_0=ruleXClassCoreElementType();

            state._fsp--;


            		current = this_XClassCoreElementType_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXClassElementType"


    // $ANTLR start "entryRuleXClassCoreElementType"
    // InternalXScript.g:2211:1: entryRuleXClassCoreElementType returns [EObject current=null] : iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF ;
    public final EObject entryRuleXClassCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassCoreElementType = null;


        try {
            // InternalXScript.g:2211:62: (iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF )
            // InternalXScript.g:2212:2: iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF
            {
             newCompositeNode(grammarAccess.getXClassCoreElementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXClassCoreElementType=ruleXClassCoreElementType();

            state._fsp--;

             current =iv_ruleXClassCoreElementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXClassCoreElementType"


    // $ANTLR start "ruleXClassCoreElementType"
    // InternalXScript.g:2218:1: ruleXClassCoreElementType returns [EObject current=null] : (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType ) ;
    public final EObject ruleXClassCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XFunctionType_0 = null;

        EObject this_XVarType_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:2224:2: ( (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType ) )
            // InternalXScript.g:2225:2: (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType )
            {
            // InternalXScript.g:2225:2: (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==48) ) {
                alt22=1;
            }
            else if ( (LA22_0==47) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalXScript.g:2226:3: this_XFunctionType_0= ruleXFunctionType
                    {

                    			newCompositeNode(grammarAccess.getXClassCoreElementTypeAccess().getXFunctionTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_XFunctionType_0=ruleXFunctionType();

                    state._fsp--;


                    			current = this_XFunctionType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalXScript.g:2235:3: this_XVarType_1= ruleXVarType
                    {

                    			newCompositeNode(grammarAccess.getXClassCoreElementTypeAccess().getXVarTypeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarType_1=ruleXVarType();

                    state._fsp--;


                    			current = this_XVarType_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXClassCoreElementType"


    // $ANTLR start "entryRuleXVarType"
    // InternalXScript.g:2247:1: entryRuleXVarType returns [EObject current=null] : iv_ruleXVarType= ruleXVarType EOF ;
    public final EObject entryRuleXVarType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarType = null;


        try {
            // InternalXScript.g:2247:49: (iv_ruleXVarType= ruleXVarType EOF )
            // InternalXScript.g:2248:2: iv_ruleXVarType= ruleXVarType EOF
            {
             newCompositeNode(grammarAccess.getXVarTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarType=ruleXVarType();

            state._fsp--;

             current =iv_ruleXVarType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarType"


    // $ANTLR start "ruleXVarType"
    // InternalXScript.g:2254:1: ruleXVarType returns [EObject current=null] : ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' ) ;
    public final EObject ruleXVarType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_exp_4_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2260:2: ( ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' ) )
            // InternalXScript.g:2261:2: ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' )
            {
            // InternalXScript.g:2261:2: ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' )
            // InternalXScript.g:2262:3: () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';'
            {
            // InternalXScript.g:2262:3: ()
            // InternalXScript.g:2263:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarTypeAccess().getXVarTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,47,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarTypeAccess().getVarKeyword_1());
            		
            // InternalXScript.g:2273:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2274:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2274:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2275:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_31); 

            					newLeafNode(lv_name_2_0, grammarAccess.getXVarTypeAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalXScript.g:2291:3: (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==16) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // InternalXScript.g:2292:4: otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) )
                    {
                    otherlv_3=(Token)match(input,16,FOLLOW_32); 

                    				newLeafNode(otherlv_3, grammarAccess.getXVarTypeAccess().getEqualsSignKeyword_3_0());
                    			
                    // InternalXScript.g:2296:4: ( (lv_exp_4_0= ruleXExpressionType ) )
                    // InternalXScript.g:2297:5: (lv_exp_4_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:2297:5: (lv_exp_4_0= ruleXExpressionType )
                    // InternalXScript.g:2298:6: lv_exp_4_0= ruleXExpressionType
                    {

                    						newCompositeNode(grammarAccess.getXVarTypeAccess().getExpXExpressionTypeParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_exp_4_0=ruleXExpressionType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getXVarTypeRule());
                    						}
                    						set(
                    							current,
                    							"exp",
                    							lv_exp_4_0,
                    							"com.dunkware.xstream.XScript.XExpressionType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_5=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getXVarTypeAccess().getSemicolonKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarType"


    // $ANTLR start "entryRuleXFunctionType"
    // InternalXScript.g:2324:1: entryRuleXFunctionType returns [EObject current=null] : iv_ruleXFunctionType= ruleXFunctionType EOF ;
    public final EObject entryRuleXFunctionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionType = null;


        try {
            // InternalXScript.g:2324:54: (iv_ruleXFunctionType= ruleXFunctionType EOF )
            // InternalXScript.g:2325:2: iv_ruleXFunctionType= ruleXFunctionType EOF
            {
             newCompositeNode(grammarAccess.getXFunctionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXFunctionType=ruleXFunctionType();

            state._fsp--;

             current =iv_ruleXFunctionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXFunctionType"


    // $ANTLR start "ruleXFunctionType"
    // InternalXScript.g:2331:1: ruleXFunctionType returns [EObject current=null] : ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' ) ;
    public final EObject ruleXFunctionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_elements_7_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2337:2: ( ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' ) )
            // InternalXScript.g:2338:2: ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' )
            {
            // InternalXScript.g:2338:2: ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' )
            // InternalXScript.g:2339:3: () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}'
            {
            // InternalXScript.g:2339:3: ()
            // InternalXScript.g:2340:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionTypeAccess().getXFunctionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,48,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionTypeAccess().getFunctionKeyword_1());
            		
            // InternalXScript.g:2350:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2351:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2351:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2352:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_2_0, grammarAccess.getXFunctionTypeAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_33); 

            			newLeafNode(otherlv_3, grammarAccess.getXFunctionTypeAccess().getLeftParenthesisKeyword_3());
            		
            // InternalXScript.g:2372:3: ( (otherlv_4= RULE_ID ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==RULE_ID) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalXScript.g:2373:4: (otherlv_4= RULE_ID )
            	    {
            	    // InternalXScript.g:2373:4: (otherlv_4= RULE_ID )
            	    // InternalXScript.g:2374:5: otherlv_4= RULE_ID
            	    {

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getXFunctionTypeRule());
            	    					}
            	    				
            	    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_33); 

            	    					newLeafNode(otherlv_4, grammarAccess.getXFunctionTypeAccess().getParamsXVarTypeCrossReference_4_0());
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

            otherlv_5=(Token)match(input,15,FOLLOW_25); 

            			newLeafNode(otherlv_5, grammarAccess.getXFunctionTypeAccess().getRightParenthesisKeyword_5());
            		
            otherlv_6=(Token)match(input,37,FOLLOW_34); 

            			newLeafNode(otherlv_6, grammarAccess.getXFunctionTypeAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalXScript.g:2393:3: ( (lv_elements_7_0= ruleXClassFunctionElementType ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_ID||LA25_0==45||LA25_0==47||(LA25_0>=49 && LA25_0<=52)||(LA25_0>=55 && LA25_0<=57)||LA25_0==60||LA25_0==62) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalXScript.g:2394:4: (lv_elements_7_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:2394:4: (lv_elements_7_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:2395:5: lv_elements_7_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXFunctionTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_34);
            	    lv_elements_7_0=ruleXClassFunctionElementType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXFunctionTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_7_0,
            	    						"com.dunkware.xstream.XScript.XClassFunctionElementType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            otherlv_8=(Token)match(input,38,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getXFunctionTypeAccess().getRightCurlyBracketKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFunctionType"


    // $ANTLR start "entryRuleXClassFunctionElementType"
    // InternalXScript.g:2420:1: entryRuleXClassFunctionElementType returns [EObject current=null] : iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF ;
    public final EObject entryRuleXClassFunctionElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassFunctionElementType = null;


        try {
            // InternalXScript.g:2420:66: (iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF )
            // InternalXScript.g:2421:2: iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF
            {
             newCompositeNode(grammarAccess.getXClassFunctionElementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXClassFunctionElementType=ruleXClassFunctionElementType();

            state._fsp--;

             current =iv_ruleXClassFunctionElementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXClassFunctionElementType"


    // $ANTLR start "ruleXClassFunctionElementType"
    // InternalXScript.g:2427:1: ruleXClassFunctionElementType returns [EObject current=null] : this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType ;
    public final EObject ruleXClassFunctionElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XFunctionCoreElementType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2433:2: (this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType )
            // InternalXScript.g:2434:2: this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType
            {

            		newCompositeNode(grammarAccess.getXClassFunctionElementTypeAccess().getXFunctionCoreElementTypeParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_XFunctionCoreElementType_0=ruleXFunctionCoreElementType();

            state._fsp--;


            		current = this_XFunctionCoreElementType_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXClassFunctionElementType"


    // $ANTLR start "entryRuleXFunctionCoreElementType"
    // InternalXScript.g:2445:1: entryRuleXFunctionCoreElementType returns [EObject current=null] : iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF ;
    public final EObject entryRuleXFunctionCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCoreElementType = null;


        try {
            // InternalXScript.g:2445:65: (iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF )
            // InternalXScript.g:2446:2: iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF
            {
             newCompositeNode(grammarAccess.getXFunctionCoreElementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXFunctionCoreElementType=ruleXFunctionCoreElementType();

            state._fsp--;

             current =iv_ruleXFunctionCoreElementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXFunctionCoreElementType"


    // $ANTLR start "ruleXFunctionCoreElementType"
    // InternalXScript.g:2452:1: ruleXFunctionCoreElementType returns [EObject current=null] : (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType ) ;
    public final EObject ruleXFunctionCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XFunctionReturnType_0 = null;

        EObject this_XVarType_1 = null;

        EObject this_XIfStatementType_2 = null;

        EObject this_XSignalListenerType_3 = null;

        EObject this_XStreamVarListenerType_4 = null;

        EObject this_XFunctionStartType_5 = null;

        EObject this_XFunctionCallType_6 = null;

        EObject this_XVarSetterType_7 = null;

        EObject this_XSignalTriggerType_8 = null;

        EObject this_XVarDecrementType_9 = null;

        EObject this_XSetVarType_10 = null;

        EObject this_XVarIncrementType_11 = null;

        EObject this_XDebugType_12 = null;

        EObject this_XSleepType_13 = null;

        EObject this_XWhileType_14 = null;



        	enterRule();

        try {
            // InternalXScript.g:2458:2: ( (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType ) )
            // InternalXScript.g:2459:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )
            {
            // InternalXScript.g:2459:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )
            int alt26=15;
            alt26 = dfa26.predict(input);
            switch (alt26) {
                case 1 :
                    // InternalXScript.g:2460:3: this_XFunctionReturnType_0= ruleXFunctionReturnType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXFunctionReturnTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_XFunctionReturnType_0=ruleXFunctionReturnType();

                    state._fsp--;


                    			current = this_XFunctionReturnType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalXScript.g:2469:3: this_XVarType_1= ruleXVarType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXVarTypeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarType_1=ruleXVarType();

                    state._fsp--;


                    			current = this_XVarType_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalXScript.g:2478:3: this_XIfStatementType_2= ruleXIfStatementType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXIfStatementTypeParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_XIfStatementType_2=ruleXIfStatementType();

                    state._fsp--;


                    			current = this_XIfStatementType_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalXScript.g:2487:3: this_XSignalListenerType_3= ruleXSignalListenerType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXSignalListenerTypeParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_XSignalListenerType_3=ruleXSignalListenerType();

                    state._fsp--;


                    			current = this_XSignalListenerType_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalXScript.g:2496:3: this_XStreamVarListenerType_4= ruleXStreamVarListenerType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXStreamVarListenerTypeParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_XStreamVarListenerType_4=ruleXStreamVarListenerType();

                    state._fsp--;


                    			current = this_XStreamVarListenerType_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalXScript.g:2505:3: this_XFunctionStartType_5= ruleXFunctionStartType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXFunctionStartTypeParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_XFunctionStartType_5=ruleXFunctionStartType();

                    state._fsp--;


                    			current = this_XFunctionStartType_5;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalXScript.g:2514:3: this_XFunctionCallType_6= ruleXFunctionCallType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXFunctionCallTypeParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_XFunctionCallType_6=ruleXFunctionCallType();

                    state._fsp--;


                    			current = this_XFunctionCallType_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalXScript.g:2523:3: this_XVarSetterType_7= ruleXVarSetterType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXVarSetterTypeParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarSetterType_7=ruleXVarSetterType();

                    state._fsp--;


                    			current = this_XVarSetterType_7;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 9 :
                    // InternalXScript.g:2532:3: this_XSignalTriggerType_8= ruleXSignalTriggerType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXSignalTriggerTypeParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_XSignalTriggerType_8=ruleXSignalTriggerType();

                    state._fsp--;


                    			current = this_XSignalTriggerType_8;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 10 :
                    // InternalXScript.g:2541:3: this_XVarDecrementType_9= ruleXVarDecrementType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXVarDecrementTypeParserRuleCall_9());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarDecrementType_9=ruleXVarDecrementType();

                    state._fsp--;


                    			current = this_XVarDecrementType_9;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 11 :
                    // InternalXScript.g:2550:3: this_XSetVarType_10= ruleXSetVarType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXSetVarTypeParserRuleCall_10());
                    		
                    pushFollow(FOLLOW_2);
                    this_XSetVarType_10=ruleXSetVarType();

                    state._fsp--;


                    			current = this_XSetVarType_10;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 12 :
                    // InternalXScript.g:2559:3: this_XVarIncrementType_11= ruleXVarIncrementType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXVarIncrementTypeParserRuleCall_11());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarIncrementType_11=ruleXVarIncrementType();

                    state._fsp--;


                    			current = this_XVarIncrementType_11;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 13 :
                    // InternalXScript.g:2568:3: this_XDebugType_12= ruleXDebugType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXDebugTypeParserRuleCall_12());
                    		
                    pushFollow(FOLLOW_2);
                    this_XDebugType_12=ruleXDebugType();

                    state._fsp--;


                    			current = this_XDebugType_12;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 14 :
                    // InternalXScript.g:2577:3: this_XSleepType_13= ruleXSleepType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXSleepTypeParserRuleCall_13());
                    		
                    pushFollow(FOLLOW_2);
                    this_XSleepType_13=ruleXSleepType();

                    state._fsp--;


                    			current = this_XSleepType_13;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 15 :
                    // InternalXScript.g:2586:3: this_XWhileType_14= ruleXWhileType
                    {

                    			newCompositeNode(grammarAccess.getXFunctionCoreElementTypeAccess().getXWhileTypeParserRuleCall_14());
                    		
                    pushFollow(FOLLOW_2);
                    this_XWhileType_14=ruleXWhileType();

                    state._fsp--;


                    			current = this_XWhileType_14;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFunctionCoreElementType"


    // $ANTLR start "entryRuleXFunctionReturnType"
    // InternalXScript.g:2598:1: entryRuleXFunctionReturnType returns [EObject current=null] : iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF ;
    public final EObject entryRuleXFunctionReturnType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionReturnType = null;


        try {
            // InternalXScript.g:2598:60: (iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF )
            // InternalXScript.g:2599:2: iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF
            {
             newCompositeNode(grammarAccess.getXFunctionReturnTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXFunctionReturnType=ruleXFunctionReturnType();

            state._fsp--;

             current =iv_ruleXFunctionReturnType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXFunctionReturnType"


    // $ANTLR start "ruleXFunctionReturnType"
    // InternalXScript.g:2605:1: ruleXFunctionReturnType returns [EObject current=null] : ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' ) ;
    public final EObject ruleXFunctionReturnType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_returnValue_2_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2611:2: ( ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' ) )
            // InternalXScript.g:2612:2: ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' )
            {
            // InternalXScript.g:2612:2: ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' )
            // InternalXScript.g:2613:3: () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';'
            {
            // InternalXScript.g:2613:3: ()
            // InternalXScript.g:2614:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionReturnTypeAccess().getXFunctionReturnTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,49,FOLLOW_35); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionReturnTypeAccess().getReturnKeyword_1());
            		
            // InternalXScript.g:2624:3: ( (lv_returnValue_2_0= ruleXExpressionType ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( ((LA27_0>=RULE_ID && LA27_0<=RULE_STRING)||LA27_0==13||(LA27_0>=30 && LA27_0<=32)||LA27_0==36||LA27_0==42||(LA27_0>=63 && LA27_0<=64)||LA27_0==70||(LA27_0>=72 && LA27_0<=77)) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // InternalXScript.g:2625:4: (lv_returnValue_2_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:2625:4: (lv_returnValue_2_0= ruleXExpressionType )
                    // InternalXScript.g:2626:5: lv_returnValue_2_0= ruleXExpressionType
                    {

                    					newCompositeNode(grammarAccess.getXFunctionReturnTypeAccess().getReturnValueXExpressionTypeParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_12);
                    lv_returnValue_2_0=ruleXExpressionType();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getXFunctionReturnTypeRule());
                    					}
                    					set(
                    						current,
                    						"returnValue",
                    						lv_returnValue_2_0,
                    						"com.dunkware.xstream.XScript.XExpressionType");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_3=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getXFunctionReturnTypeAccess().getSemicolonKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFunctionReturnType"


    // $ANTLR start "entryRuleXFunctionCallType"
    // InternalXScript.g:2651:1: entryRuleXFunctionCallType returns [EObject current=null] : iv_ruleXFunctionCallType= ruleXFunctionCallType EOF ;
    public final EObject entryRuleXFunctionCallType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCallType = null;


        try {
            // InternalXScript.g:2651:58: (iv_ruleXFunctionCallType= ruleXFunctionCallType EOF )
            // InternalXScript.g:2652:2: iv_ruleXFunctionCallType= ruleXFunctionCallType EOF
            {
             newCompositeNode(grammarAccess.getXFunctionCallTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXFunctionCallType=ruleXFunctionCallType();

            state._fsp--;

             current =iv_ruleXFunctionCallType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXFunctionCallType"


    // $ANTLR start "ruleXFunctionCallType"
    // InternalXScript.g:2658:1: ruleXFunctionCallType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' ) ;
    public final EObject ruleXFunctionCallType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_params_3_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalXScript.g:2664:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' ) )
            // InternalXScript.g:2665:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' )
            {
            // InternalXScript.g:2665:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' )
            // InternalXScript.g:2666:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';'
            {
            // InternalXScript.g:2666:3: ()
            // InternalXScript.g:2667:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionCallTypeAccess().getXFunctionCallTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:2673:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:2674:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:2674:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:2675:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionCallTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(otherlv_1, grammarAccess.getXFunctionCallTypeAccess().getFunctionXFunctionTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_36); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionCallTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2690:3: ( (lv_params_3_0= RULE_STRING ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==RULE_STRING) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalXScript.g:2691:4: (lv_params_3_0= RULE_STRING )
                    {
                    // InternalXScript.g:2691:4: (lv_params_3_0= RULE_STRING )
                    // InternalXScript.g:2692:5: lv_params_3_0= RULE_STRING
                    {
                    lv_params_3_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

                    					newLeafNode(lv_params_3_0, grammarAccess.getXFunctionCallTypeAccess().getParamsSTRINGTerminalRuleCall_3_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getXFunctionCallTypeRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"params",
                    						lv_params_3_0,
                    						"org.eclipse.xtext.common.Terminals.STRING");
                    				

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_4, grammarAccess.getXFunctionCallTypeAccess().getRightParenthesisKeyword_4());
            		
            otherlv_5=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getXFunctionCallTypeAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFunctionCallType"


    // $ANTLR start "entryRuleXSignalListenerType"
    // InternalXScript.g:2720:1: entryRuleXSignalListenerType returns [EObject current=null] : iv_ruleXSignalListenerType= ruleXSignalListenerType EOF ;
    public final EObject entryRuleXSignalListenerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalListenerType = null;


        try {
            // InternalXScript.g:2720:60: (iv_ruleXSignalListenerType= ruleXSignalListenerType EOF )
            // InternalXScript.g:2721:2: iv_ruleXSignalListenerType= ruleXSignalListenerType EOF
            {
             newCompositeNode(grammarAccess.getXSignalListenerTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXSignalListenerType=ruleXSignalListenerType();

            state._fsp--;

             current =iv_ruleXSignalListenerType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXSignalListenerType"


    // $ANTLR start "ruleXSignalListenerType"
    // InternalXScript.g:2727:1: ruleXSignalListenerType returns [EObject current=null] : ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) ;
    public final EObject ruleXSignalListenerType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;


        	enterRule();

        try {
            // InternalXScript.g:2733:2: ( ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:2734:2: ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:2734:2: ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:2735:3: () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:2735:3: ()
            // InternalXScript.g:2736:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalListenerTypeAccess().getXSignalListenerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,50,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalListenerTypeAccess().getSignalListenerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalListenerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2750:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2751:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2751:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2752:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSignalListenerTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXSignalListenerTypeAccess().getSignalTypeSignalTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getXSignalListenerTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:2767:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:2768:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:2768:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:2769:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSignalListenerTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_9); 

            					newLeafNode(otherlv_5, grammarAccess.getXSignalListenerTypeAccess().getFunctionXFunctionTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_6, grammarAccess.getXSignalListenerTypeAccess().getRightParenthesisKeyword_6());
            		
            otherlv_7=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getXSignalListenerTypeAccess().getSemicolonKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXSignalListenerType"


    // $ANTLR start "entryRuleXStreamVarListenerType"
    // InternalXScript.g:2792:1: entryRuleXStreamVarListenerType returns [EObject current=null] : iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF ;
    public final EObject entryRuleXStreamVarListenerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamVarListenerType = null;


        try {
            // InternalXScript.g:2792:63: (iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF )
            // InternalXScript.g:2793:2: iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF
            {
             newCompositeNode(grammarAccess.getXStreamVarListenerTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXStreamVarListenerType=ruleXStreamVarListenerType();

            state._fsp--;

             current =iv_ruleXStreamVarListenerType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXStreamVarListenerType"


    // $ANTLR start "ruleXStreamVarListenerType"
    // InternalXScript.g:2799:1: ruleXStreamVarListenerType returns [EObject current=null] : ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) ;
    public final EObject ruleXStreamVarListenerType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;


        	enterRule();

        try {
            // InternalXScript.g:2805:2: ( ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:2806:2: ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:2806:2: ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:2807:3: () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:2807:3: ()
            // InternalXScript.g:2808:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamVarListenerTypeAccess().getXStreamVarListenerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,51,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXStreamVarListenerTypeAccess().getStreamVarListenerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXStreamVarListenerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2822:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2823:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2823:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2824:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXStreamVarListenerTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXStreamVarListenerTypeAccess().getColumnVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getXStreamVarListenerTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:2839:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:2840:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:2840:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:2841:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXStreamVarListenerTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_9); 

            					newLeafNode(otherlv_5, grammarAccess.getXStreamVarListenerTypeAccess().getFunctionXFunctionTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_6, grammarAccess.getXStreamVarListenerTypeAccess().getRightParenthesisKeyword_6());
            		
            otherlv_7=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getXStreamVarListenerTypeAccess().getSemicolonKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXStreamVarListenerType"


    // $ANTLR start "entryRuleXSignalTriggerType"
    // InternalXScript.g:2864:1: entryRuleXSignalTriggerType returns [EObject current=null] : iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF ;
    public final EObject entryRuleXSignalTriggerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalTriggerType = null;


        try {
            // InternalXScript.g:2864:59: (iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF )
            // InternalXScript.g:2865:2: iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF
            {
             newCompositeNode(grammarAccess.getXSignalTriggerTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXSignalTriggerType=ruleXSignalTriggerType();

            state._fsp--;

             current =iv_ruleXSignalTriggerType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXSignalTriggerType"


    // $ANTLR start "ruleXSignalTriggerType"
    // InternalXScript.g:2871:1: ruleXSignalTriggerType returns [EObject current=null] : ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' ) ;
    public final EObject ruleXSignalTriggerType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalXScript.g:2877:2: ( ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' ) )
            // InternalXScript.g:2878:2: ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' )
            {
            // InternalXScript.g:2878:2: ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' )
            // InternalXScript.g:2879:3: () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';'
            {
            // InternalXScript.g:2879:3: ()
            // InternalXScript.g:2880:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalTriggerTypeAccess().getXSignalTriggerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,45,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalTriggerTypeAccess().getSignalKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalTriggerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2894:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2895:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2895:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2896:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSignalTriggerTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_9); 

            					newLeafNode(otherlv_3, grammarAccess.getXSignalTriggerTypeAccess().getSignalSignalTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_4, grammarAccess.getXSignalTriggerTypeAccess().getRightParenthesisKeyword_4());
            		
            otherlv_5=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getXSignalTriggerTypeAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXSignalTriggerType"


    // $ANTLR start "entryRuleXFunctionStartType"
    // InternalXScript.g:2919:1: entryRuleXFunctionStartType returns [EObject current=null] : iv_ruleXFunctionStartType= ruleXFunctionStartType EOF ;
    public final EObject entryRuleXFunctionStartType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionStartType = null;


        try {
            // InternalXScript.g:2919:59: (iv_ruleXFunctionStartType= ruleXFunctionStartType EOF )
            // InternalXScript.g:2920:2: iv_ruleXFunctionStartType= ruleXFunctionStartType EOF
            {
             newCompositeNode(grammarAccess.getXFunctionStartTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXFunctionStartType=ruleXFunctionStartType();

            state._fsp--;

             current =iv_ruleXFunctionStartType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXFunctionStartType"


    // $ANTLR start "ruleXFunctionStartType"
    // InternalXScript.g:2926:1: ruleXFunctionStartType returns [EObject current=null] : ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' ) ;
    public final EObject ruleXFunctionStartType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_interval_5_0=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Enumerator lv_time_6_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2932:2: ( ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' ) )
            // InternalXScript.g:2933:2: ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' )
            {
            // InternalXScript.g:2933:2: ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' )
            // InternalXScript.g:2934:3: () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';'
            {
            // InternalXScript.g:2934:3: ()
            // InternalXScript.g:2935:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionStartTypeAccess().getXFunctionStartTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,52,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionStartTypeAccess().getFunctionRunnerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionStartTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2949:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2950:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2950:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2951:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionStartTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXFunctionStartTypeAccess().getFunctionXFunctionTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getXFunctionStartTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:2966:3: ( (lv_interval_5_0= RULE_INT ) )
            // InternalXScript.g:2967:4: (lv_interval_5_0= RULE_INT )
            {
            // InternalXScript.g:2967:4: (lv_interval_5_0= RULE_INT )
            // InternalXScript.g:2968:5: lv_interval_5_0= RULE_INT
            {
            lv_interval_5_0=(Token)match(input,RULE_INT,FOLLOW_24); 

            					newLeafNode(lv_interval_5_0, grammarAccess.getXFunctionStartTypeAccess().getIntervalINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionStartTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"interval",
            						lv_interval_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalXScript.g:2984:3: ( (lv_time_6_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:2985:4: (lv_time_6_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:2985:4: (lv_time_6_0= ruleStreamTimeUnit )
            // InternalXScript.g:2986:5: lv_time_6_0= ruleStreamTimeUnit
            {

            					newCompositeNode(grammarAccess.getXFunctionStartTypeAccess().getTimeStreamTimeUnitEnumRuleCall_6_0());
            				
            pushFollow(FOLLOW_9);
            lv_time_6_0=ruleStreamTimeUnit();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXFunctionStartTypeRule());
            					}
            					set(
            						current,
            						"time",
            						lv_time_6_0,
            						"com.dunkware.xstream.XScript.StreamTimeUnit");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_7, grammarAccess.getXFunctionStartTypeAccess().getRightParenthesisKeyword_7());
            		
            otherlv_8=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getXFunctionStartTypeAccess().getSemicolonKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFunctionStartType"


    // $ANTLR start "entryRuleXVarSetterType"
    // InternalXScript.g:3015:1: entryRuleXVarSetterType returns [EObject current=null] : iv_ruleXVarSetterType= ruleXVarSetterType EOF ;
    public final EObject entryRuleXVarSetterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarSetterType = null;


        try {
            // InternalXScript.g:3015:55: (iv_ruleXVarSetterType= ruleXVarSetterType EOF )
            // InternalXScript.g:3016:2: iv_ruleXVarSetterType= ruleXVarSetterType EOF
            {
             newCompositeNode(grammarAccess.getXVarSetterTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarSetterType=ruleXVarSetterType();

            state._fsp--;

             current =iv_ruleXVarSetterType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarSetterType"


    // $ANTLR start "ruleXVarSetterType"
    // InternalXScript.g:3022:1: ruleXVarSetterType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' ) ;
    public final EObject ruleXVarSetterType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_exp_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3028:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' ) )
            // InternalXScript.g:3029:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' )
            {
            // InternalXScript.g:3029:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' )
            // InternalXScript.g:3030:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';'
            {
            // InternalXScript.g:3030:3: ()
            // InternalXScript.g:3031:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarSetterTypeAccess().getXVarSetterTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3037:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3038:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3038:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3039:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarSetterTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarSetterTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_32); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarSetterTypeAccess().getEqualsSignKeyword_2());
            		
            // InternalXScript.g:3054:3: ( (lv_exp_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3055:4: (lv_exp_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3055:4: (lv_exp_3_0= ruleXExpressionType )
            // InternalXScript.g:3056:5: lv_exp_3_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXVarSetterTypeAccess().getExpXExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_12);
            lv_exp_3_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXVarSetterTypeRule());
            					}
            					set(
            						current,
            						"exp",
            						lv_exp_3_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarSetterTypeAccess().getSemicolonKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarSetterType"


    // $ANTLR start "entryRuleXVarIncrementType"
    // InternalXScript.g:3081:1: entryRuleXVarIncrementType returns [EObject current=null] : iv_ruleXVarIncrementType= ruleXVarIncrementType EOF ;
    public final EObject entryRuleXVarIncrementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarIncrementType = null;


        try {
            // InternalXScript.g:3081:58: (iv_ruleXVarIncrementType= ruleXVarIncrementType EOF )
            // InternalXScript.g:3082:2: iv_ruleXVarIncrementType= ruleXVarIncrementType EOF
            {
             newCompositeNode(grammarAccess.getXVarIncrementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarIncrementType=ruleXVarIncrementType();

            state._fsp--;

             current =iv_ruleXVarIncrementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarIncrementType"


    // $ANTLR start "ruleXVarIncrementType"
    // InternalXScript.g:3088:1: ruleXVarIncrementType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' ) ;
    public final EObject ruleXVarIncrementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalXScript.g:3094:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' ) )
            // InternalXScript.g:3095:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' )
            {
            // InternalXScript.g:3095:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' )
            // InternalXScript.g:3096:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';'
            {
            // InternalXScript.g:3096:3: ()
            // InternalXScript.g:3097:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarIncrementTypeAccess().getXVarIncrementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3103:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3104:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3104:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3105:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarIncrementTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_37); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarIncrementTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,53,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarIncrementTypeAccess().getPlusSignPlusSignKeyword_2());
            		
            otherlv_3=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getXVarIncrementTypeAccess().getSemicolonKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarIncrementType"


    // $ANTLR start "entryRuleXVarDecrementType"
    // InternalXScript.g:3128:1: entryRuleXVarDecrementType returns [EObject current=null] : iv_ruleXVarDecrementType= ruleXVarDecrementType EOF ;
    public final EObject entryRuleXVarDecrementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarDecrementType = null;


        try {
            // InternalXScript.g:3128:58: (iv_ruleXVarDecrementType= ruleXVarDecrementType EOF )
            // InternalXScript.g:3129:2: iv_ruleXVarDecrementType= ruleXVarDecrementType EOF
            {
             newCompositeNode(grammarAccess.getXVarDecrementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarDecrementType=ruleXVarDecrementType();

            state._fsp--;

             current =iv_ruleXVarDecrementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarDecrementType"


    // $ANTLR start "ruleXVarDecrementType"
    // InternalXScript.g:3135:1: ruleXVarDecrementType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' ) ;
    public final EObject ruleXVarDecrementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalXScript.g:3141:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' ) )
            // InternalXScript.g:3142:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' )
            {
            // InternalXScript.g:3142:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' )
            // InternalXScript.g:3143:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';'
            {
            // InternalXScript.g:3143:3: ()
            // InternalXScript.g:3144:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarDecrementTypeAccess().getXVarDecrementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3150:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3151:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3151:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3152:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarDecrementTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_38); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarDecrementTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,54,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarDecrementTypeAccess().getHyphenMinusHyphenMinusKeyword_2());
            		
            otherlv_3=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getXVarDecrementTypeAccess().getSemicolonKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarDecrementType"


    // $ANTLR start "entryRuleXSetVarType"
    // InternalXScript.g:3175:1: entryRuleXSetVarType returns [EObject current=null] : iv_ruleXSetVarType= ruleXSetVarType EOF ;
    public final EObject entryRuleXSetVarType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSetVarType = null;


        try {
            // InternalXScript.g:3175:52: (iv_ruleXSetVarType= ruleXSetVarType EOF )
            // InternalXScript.g:3176:2: iv_ruleXSetVarType= ruleXSetVarType EOF
            {
             newCompositeNode(grammarAccess.getXSetVarTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXSetVarType=ruleXSetVarType();

            state._fsp--;

             current =iv_ruleXSetVarType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXSetVarType"


    // $ANTLR start "ruleXSetVarType"
    // InternalXScript.g:3182:1: ruleXSetVarType returns [EObject current=null] : ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' ) ;
    public final EObject ruleXSetVarType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_value_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3188:2: ( ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:3189:2: ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:3189:2: ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:3190:3: () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:3190:3: ()
            // InternalXScript.g:3191:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSetVarTypeAccess().getXSetVarTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,55,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSetVarTypeAccess().getSetStreamVarKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSetVarTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3205:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:3206:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:3206:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:3207:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSetVarTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXSetVarTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_4, grammarAccess.getXSetVarTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:3222:3: ( (lv_value_5_0= ruleXExpressionType ) )
            // InternalXScript.g:3223:4: (lv_value_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:3223:4: (lv_value_5_0= ruleXExpressionType )
            // InternalXScript.g:3224:5: lv_value_5_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXSetVarTypeAccess().getValueXExpressionTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_value_5_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXSetVarTypeRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_5_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_6, grammarAccess.getXSetVarTypeAccess().getRightParenthesisKeyword_6());
            		
            otherlv_7=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getXSetVarTypeAccess().getSemicolonKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXSetVarType"


    // $ANTLR start "entryRuleXDebugType"
    // InternalXScript.g:3253:1: entryRuleXDebugType returns [EObject current=null] : iv_ruleXDebugType= ruleXDebugType EOF ;
    public final EObject entryRuleXDebugType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXDebugType = null;


        try {
            // InternalXScript.g:3253:51: (iv_ruleXDebugType= ruleXDebugType EOF )
            // InternalXScript.g:3254:2: iv_ruleXDebugType= ruleXDebugType EOF
            {
             newCompositeNode(grammarAccess.getXDebugTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXDebugType=ruleXDebugType();

            state._fsp--;

             current =iv_ruleXDebugType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXDebugType"


    // $ANTLR start "ruleXDebugType"
    // InternalXScript.g:3260:1: ruleXDebugType returns [EObject current=null] : ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' ) ;
    public final EObject ruleXDebugType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        EObject lv_args_3_0 = null;

        EObject lv_args_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3266:2: ( ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:3267:2: ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:3267:2: ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:3268:3: () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:3268:3: ()
            // InternalXScript.g:3269:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXDebugTypeAccess().getXDebugTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,56,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXDebugTypeAccess().getDebugKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_39); 

            			newLeafNode(otherlv_2, grammarAccess.getXDebugTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3283:3: ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( ((LA30_0>=RULE_ID && LA30_0<=RULE_STRING)||LA30_0==13||(LA30_0>=30 && LA30_0<=32)||LA30_0==36||LA30_0==42||(LA30_0>=63 && LA30_0<=64)||LA30_0==70||(LA30_0>=72 && LA30_0<=77)) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // InternalXScript.g:3284:4: ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )*
                    {
                    // InternalXScript.g:3284:4: ( (lv_args_3_0= ruleXExpressionType ) )
                    // InternalXScript.g:3285:5: (lv_args_3_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:3285:5: (lv_args_3_0= ruleXExpressionType )
                    // InternalXScript.g:3286:6: lv_args_3_0= ruleXExpressionType
                    {

                    						newCompositeNode(grammarAccess.getXDebugTypeAccess().getArgsXExpressionTypeParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_40);
                    lv_args_3_0=ruleXExpressionType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getXDebugTypeRule());
                    						}
                    						add(
                    							current,
                    							"args",
                    							lv_args_3_0,
                    							"com.dunkware.xstream.XScript.XExpressionType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalXScript.g:3303:4: (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==14) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // InternalXScript.g:3304:5: otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) )
                    	    {
                    	    otherlv_4=(Token)match(input,14,FOLLOW_32); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getXDebugTypeAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalXScript.g:3308:5: ( (lv_args_5_0= ruleXExpressionType ) )
                    	    // InternalXScript.g:3309:6: (lv_args_5_0= ruleXExpressionType )
                    	    {
                    	    // InternalXScript.g:3309:6: (lv_args_5_0= ruleXExpressionType )
                    	    // InternalXScript.g:3310:7: lv_args_5_0= ruleXExpressionType
                    	    {

                    	    							newCompositeNode(grammarAccess.getXDebugTypeAccess().getArgsXExpressionTypeParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_40);
                    	    lv_args_5_0=ruleXExpressionType();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getXDebugTypeRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"args",
                    	    								lv_args_5_0,
                    	    								"com.dunkware.xstream.XScript.XExpressionType");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_6, grammarAccess.getXDebugTypeAccess().getRightParenthesisKeyword_4());
            		
            otherlv_7=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getXDebugTypeAccess().getSemicolonKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXDebugType"


    // $ANTLR start "entryRuleXIfStatementType"
    // InternalXScript.g:3341:1: entryRuleXIfStatementType returns [EObject current=null] : iv_ruleXIfStatementType= ruleXIfStatementType EOF ;
    public final EObject entryRuleXIfStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXIfStatementType = null;


        try {
            // InternalXScript.g:3341:57: (iv_ruleXIfStatementType= ruleXIfStatementType EOF )
            // InternalXScript.g:3342:2: iv_ruleXIfStatementType= ruleXIfStatementType EOF
            {
             newCompositeNode(grammarAccess.getXIfStatementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXIfStatementType=ruleXIfStatementType();

            state._fsp--;

             current =iv_ruleXIfStatementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXIfStatementType"


    // $ANTLR start "ruleXIfStatementType"
    // InternalXScript.g:3348:1: ruleXIfStatementType returns [EObject current=null] : ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? ) ;
    public final EObject ruleXIfStatementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_expression_3_0 = null;

        EObject lv_elements_6_0 = null;

        EObject lv_elseIfElements_8_0 = null;

        EObject lv_elseElement_9_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3354:2: ( ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? ) )
            // InternalXScript.g:3355:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? )
            {
            // InternalXScript.g:3355:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? )
            // InternalXScript.g:3356:3: () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )?
            {
            // InternalXScript.g:3356:3: ()
            // InternalXScript.g:3357:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXIfStatementTypeAccess().getXIfStatementTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,57,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXIfStatementTypeAccess().getIfKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_32); 

            			newLeafNode(otherlv_2, grammarAccess.getXIfStatementTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3371:3: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3372:4: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3372:4: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:3373:5: lv_expression_3_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXIfStatementTypeAccess().getExpressionXExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_9);
            lv_expression_3_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXIfStatementTypeRule());
            					}
            					set(
            						current,
            						"expression",
            						lv_expression_3_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_25); 

            			newLeafNode(otherlv_4, grammarAccess.getXIfStatementTypeAccess().getRightParenthesisKeyword_4());
            		
            otherlv_5=(Token)match(input,37,FOLLOW_34); 

            			newLeafNode(otherlv_5, grammarAccess.getXIfStatementTypeAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalXScript.g:3398:3: ( (lv_elements_6_0= ruleXClassFunctionElementType ) )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==RULE_ID||LA31_0==45||LA31_0==47||(LA31_0>=49 && LA31_0<=52)||(LA31_0>=55 && LA31_0<=57)||LA31_0==60||LA31_0==62) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalXScript.g:3399:4: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:3399:4: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:3400:5: lv_elements_6_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXIfStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_34);
            	    lv_elements_6_0=ruleXClassFunctionElementType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXIfStatementTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_6_0,
            	    						"com.dunkware.xstream.XScript.XClassFunctionElementType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            otherlv_7=(Token)match(input,38,FOLLOW_41); 

            			newLeafNode(otherlv_7, grammarAccess.getXIfStatementTypeAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalXScript.g:3421:3: ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==58) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalXScript.g:3422:4: (lv_elseIfElements_8_0= ruleXElseIfStatementType )
            	    {
            	    // InternalXScript.g:3422:4: (lv_elseIfElements_8_0= ruleXElseIfStatementType )
            	    // InternalXScript.g:3423:5: lv_elseIfElements_8_0= ruleXElseIfStatementType
            	    {

            	    					newCompositeNode(grammarAccess.getXIfStatementTypeAccess().getElseIfElementsXElseIfStatementTypeParserRuleCall_8_0());
            	    				
            	    pushFollow(FOLLOW_41);
            	    lv_elseIfElements_8_0=ruleXElseIfStatementType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXIfStatementTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elseIfElements",
            	    						lv_elseIfElements_8_0,
            	    						"com.dunkware.xstream.XScript.XElseIfStatementType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            // InternalXScript.g:3440:3: ( (lv_elseElement_9_0= ruleXElseStatementType ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==59) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalXScript.g:3441:4: (lv_elseElement_9_0= ruleXElseStatementType )
                    {
                    // InternalXScript.g:3441:4: (lv_elseElement_9_0= ruleXElseStatementType )
                    // InternalXScript.g:3442:5: lv_elseElement_9_0= ruleXElseStatementType
                    {

                    					newCompositeNode(grammarAccess.getXIfStatementTypeAccess().getElseElementXElseStatementTypeParserRuleCall_9_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_elseElement_9_0=ruleXElseStatementType();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getXIfStatementTypeRule());
                    					}
                    					set(
                    						current,
                    						"elseElement",
                    						lv_elseElement_9_0,
                    						"com.dunkware.xstream.XScript.XElseStatementType");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXIfStatementType"


    // $ANTLR start "entryRuleXElseIfStatementType"
    // InternalXScript.g:3463:1: entryRuleXElseIfStatementType returns [EObject current=null] : iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF ;
    public final EObject entryRuleXElseIfStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXElseIfStatementType = null;


        try {
            // InternalXScript.g:3463:61: (iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF )
            // InternalXScript.g:3464:2: iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF
            {
             newCompositeNode(grammarAccess.getXElseIfStatementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXElseIfStatementType=ruleXElseIfStatementType();

            state._fsp--;

             current =iv_ruleXElseIfStatementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXElseIfStatementType"


    // $ANTLR start "ruleXElseIfStatementType"
    // InternalXScript.g:3470:1: ruleXElseIfStatementType returns [EObject current=null] : ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) ) ;
    public final EObject ruleXElseIfStatementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_expression_3_0 = null;

        EObject lv_elements_6_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3476:2: ( ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) ) )
            // InternalXScript.g:3477:2: ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) )
            {
            // InternalXScript.g:3477:2: ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) )
            // InternalXScript.g:3478:3: () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' )
            {
            // InternalXScript.g:3478:3: ()
            // InternalXScript.g:3479:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXElseIfStatementTypeAccess().getXElseIfStatementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3485:3: (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' )
            // InternalXScript.g:3486:4: otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}'
            {
            otherlv_1=(Token)match(input,58,FOLLOW_5); 

            				newLeafNode(otherlv_1, grammarAccess.getXElseIfStatementTypeAccess().getElseifKeyword_1_0());
            			
            otherlv_2=(Token)match(input,13,FOLLOW_32); 

            				newLeafNode(otherlv_2, grammarAccess.getXElseIfStatementTypeAccess().getLeftParenthesisKeyword_1_1());
            			
            // InternalXScript.g:3494:4: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3495:5: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3495:5: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:3496:6: lv_expression_3_0= ruleXExpressionType
            {

            						newCompositeNode(grammarAccess.getXElseIfStatementTypeAccess().getExpressionXExpressionTypeParserRuleCall_1_2_0());
            					
            pushFollow(FOLLOW_9);
            lv_expression_3_0=ruleXExpressionType();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getXElseIfStatementTypeRule());
            						}
            						set(
            							current,
            							"expression",
            							lv_expression_3_0,
            							"com.dunkware.xstream.XScript.XExpressionType");
            						afterParserOrEnumRuleCall();
            					

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_25); 

            				newLeafNode(otherlv_4, grammarAccess.getXElseIfStatementTypeAccess().getRightParenthesisKeyword_1_3());
            			
            otherlv_5=(Token)match(input,37,FOLLOW_34); 

            				newLeafNode(otherlv_5, grammarAccess.getXElseIfStatementTypeAccess().getLeftCurlyBracketKeyword_1_4());
            			
            // InternalXScript.g:3521:4: ( (lv_elements_6_0= ruleXClassFunctionElementType ) )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==RULE_ID||LA34_0==45||LA34_0==47||(LA34_0>=49 && LA34_0<=52)||(LA34_0>=55 && LA34_0<=57)||LA34_0==60||LA34_0==62) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // InternalXScript.g:3522:5: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:3522:5: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:3523:6: lv_elements_6_0= ruleXClassFunctionElementType
            	    {

            	    						newCompositeNode(grammarAccess.getXElseIfStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_1_5_0());
            	    					
            	    pushFollow(FOLLOW_34);
            	    lv_elements_6_0=ruleXClassFunctionElementType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXElseIfStatementTypeRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elements",
            	    							lv_elements_6_0,
            	    							"com.dunkware.xstream.XScript.XClassFunctionElementType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }
            	    break;

            	default :
            	    break loop34;
                }
            } while (true);

            otherlv_7=(Token)match(input,38,FOLLOW_2); 

            				newLeafNode(otherlv_7, grammarAccess.getXElseIfStatementTypeAccess().getRightCurlyBracketKeyword_1_6());
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXElseIfStatementType"


    // $ANTLR start "entryRuleXElseStatementType"
    // InternalXScript.g:3549:1: entryRuleXElseStatementType returns [EObject current=null] : iv_ruleXElseStatementType= ruleXElseStatementType EOF ;
    public final EObject entryRuleXElseStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXElseStatementType = null;


        try {
            // InternalXScript.g:3549:59: (iv_ruleXElseStatementType= ruleXElseStatementType EOF )
            // InternalXScript.g:3550:2: iv_ruleXElseStatementType= ruleXElseStatementType EOF
            {
             newCompositeNode(grammarAccess.getXElseStatementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXElseStatementType=ruleXElseStatementType();

            state._fsp--;

             current =iv_ruleXElseStatementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXElseStatementType"


    // $ANTLR start "ruleXElseStatementType"
    // InternalXScript.g:3556:1: ruleXElseStatementType returns [EObject current=null] : ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' ) ;
    public final EObject ruleXElseStatementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3562:2: ( ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' ) )
            // InternalXScript.g:3563:2: ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' )
            {
            // InternalXScript.g:3563:2: ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' )
            // InternalXScript.g:3564:3: () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}'
            {
            // InternalXScript.g:3564:3: ()
            // InternalXScript.g:3565:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXElseStatementTypeAccess().getXElseStatementTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,59,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getXElseStatementTypeAccess().getElseKeyword_1());
            		
            otherlv_2=(Token)match(input,37,FOLLOW_34); 

            			newLeafNode(otherlv_2, grammarAccess.getXElseStatementTypeAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalXScript.g:3579:3: ( (lv_elements_3_0= ruleXClassFunctionElementType ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_ID||LA35_0==45||LA35_0==47||(LA35_0>=49 && LA35_0<=52)||(LA35_0>=55 && LA35_0<=57)||LA35_0==60||LA35_0==62) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalXScript.g:3580:4: (lv_elements_3_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:3580:4: (lv_elements_3_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:3581:5: lv_elements_3_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXElseStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_34);
            	    lv_elements_3_0=ruleXClassFunctionElementType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXElseStatementTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_3_0,
            	    						"com.dunkware.xstream.XScript.XClassFunctionElementType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop35;
                }
            } while (true);

            otherlv_4=(Token)match(input,38,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getXElseStatementTypeAccess().getRightCurlyBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXElseStatementType"


    // $ANTLR start "entryRuleXWhileType"
    // InternalXScript.g:3606:1: entryRuleXWhileType returns [EObject current=null] : iv_ruleXWhileType= ruleXWhileType EOF ;
    public final EObject entryRuleXWhileType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileType = null;


        try {
            // InternalXScript.g:3606:51: (iv_ruleXWhileType= ruleXWhileType EOF )
            // InternalXScript.g:3607:2: iv_ruleXWhileType= ruleXWhileType EOF
            {
             newCompositeNode(grammarAccess.getXWhileTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXWhileType=ruleXWhileType();

            state._fsp--;

             current =iv_ruleXWhileType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXWhileType"


    // $ANTLR start "ruleXWhileType"
    // InternalXScript.g:3613:1: ruleXWhileType returns [EObject current=null] : ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' ) ;
    public final EObject ruleXWhileType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_expression_3_0 = null;

        EObject lv_elements_6_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3619:2: ( ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' ) )
            // InternalXScript.g:3620:2: ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' )
            {
            // InternalXScript.g:3620:2: ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' )
            // InternalXScript.g:3621:3: () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}'
            {
            // InternalXScript.g:3621:3: ()
            // InternalXScript.g:3622:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXWhileTypeAccess().getXWhileTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,60,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXWhileTypeAccess().getWhilstKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_32); 

            			newLeafNode(otherlv_2, grammarAccess.getXWhileTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3636:3: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3637:4: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3637:4: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:3638:5: lv_expression_3_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXWhileTypeAccess().getExpressionXExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_9);
            lv_expression_3_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXWhileTypeRule());
            					}
            					set(
            						current,
            						"expression",
            						lv_expression_3_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_25); 

            			newLeafNode(otherlv_4, grammarAccess.getXWhileTypeAccess().getRightParenthesisKeyword_4());
            		
            otherlv_5=(Token)match(input,37,FOLLOW_42); 

            			newLeafNode(otherlv_5, grammarAccess.getXWhileTypeAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalXScript.g:3663:3: ( (lv_elements_6_0= ruleXWhileElementType ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_ID||LA36_0==45||LA36_0==47||(LA36_0>=49 && LA36_0<=52)||(LA36_0>=55 && LA36_0<=57)||(LA36_0>=60 && LA36_0<=62)) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalXScript.g:3664:4: (lv_elements_6_0= ruleXWhileElementType )
            	    {
            	    // InternalXScript.g:3664:4: (lv_elements_6_0= ruleXWhileElementType )
            	    // InternalXScript.g:3665:5: lv_elements_6_0= ruleXWhileElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXWhileTypeAccess().getElementsXWhileElementTypeParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_42);
            	    lv_elements_6_0=ruleXWhileElementType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXWhileTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_6_0,
            	    						"com.dunkware.xstream.XScript.XWhileElementType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            otherlv_7=(Token)match(input,38,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getXWhileTypeAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXWhileType"


    // $ANTLR start "entryRuleXWhileElementType"
    // InternalXScript.g:3690:1: entryRuleXWhileElementType returns [EObject current=null] : iv_ruleXWhileElementType= ruleXWhileElementType EOF ;
    public final EObject entryRuleXWhileElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileElementType = null;


        try {
            // InternalXScript.g:3690:58: (iv_ruleXWhileElementType= ruleXWhileElementType EOF )
            // InternalXScript.g:3691:2: iv_ruleXWhileElementType= ruleXWhileElementType EOF
            {
             newCompositeNode(grammarAccess.getXWhileElementTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXWhileElementType=ruleXWhileElementType();

            state._fsp--;

             current =iv_ruleXWhileElementType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXWhileElementType"


    // $ANTLR start "ruleXWhileElementType"
    // InternalXScript.g:3697:1: ruleXWhileElementType returns [EObject current=null] : (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType ) ;
    public final EObject ruleXWhileElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XWhileBreakType_0 = null;

        EObject this_XClassFunctionElementType_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:3703:2: ( (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType ) )
            // InternalXScript.g:3704:2: (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType )
            {
            // InternalXScript.g:3704:2: (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==61) ) {
                alt37=1;
            }
            else if ( (LA37_0==RULE_ID||LA37_0==45||LA37_0==47||(LA37_0>=49 && LA37_0<=52)||(LA37_0>=55 && LA37_0<=57)||LA37_0==60||LA37_0==62) ) {
                alt37=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;
            }
            switch (alt37) {
                case 1 :
                    // InternalXScript.g:3705:3: this_XWhileBreakType_0= ruleXWhileBreakType
                    {

                    			newCompositeNode(grammarAccess.getXWhileElementTypeAccess().getXWhileBreakTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_XWhileBreakType_0=ruleXWhileBreakType();

                    state._fsp--;


                    			current = this_XWhileBreakType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalXScript.g:3714:3: this_XClassFunctionElementType_1= ruleXClassFunctionElementType
                    {

                    			newCompositeNode(grammarAccess.getXWhileElementTypeAccess().getXClassFunctionElementTypeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_XClassFunctionElementType_1=ruleXClassFunctionElementType();

                    state._fsp--;


                    			current = this_XClassFunctionElementType_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXWhileElementType"


    // $ANTLR start "entryRuleXWhileBreakType"
    // InternalXScript.g:3726:1: entryRuleXWhileBreakType returns [EObject current=null] : iv_ruleXWhileBreakType= ruleXWhileBreakType EOF ;
    public final EObject entryRuleXWhileBreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileBreakType = null;


        try {
            // InternalXScript.g:3726:56: (iv_ruleXWhileBreakType= ruleXWhileBreakType EOF )
            // InternalXScript.g:3727:2: iv_ruleXWhileBreakType= ruleXWhileBreakType EOF
            {
             newCompositeNode(grammarAccess.getXWhileBreakTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXWhileBreakType=ruleXWhileBreakType();

            state._fsp--;

             current =iv_ruleXWhileBreakType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXWhileBreakType"


    // $ANTLR start "ruleXWhileBreakType"
    // InternalXScript.g:3733:1: ruleXWhileBreakType returns [EObject current=null] : ( () otherlv_1= 'break' otherlv_2= ';' ) ;
    public final EObject ruleXWhileBreakType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalXScript.g:3739:2: ( ( () otherlv_1= 'break' otherlv_2= ';' ) )
            // InternalXScript.g:3740:2: ( () otherlv_1= 'break' otherlv_2= ';' )
            {
            // InternalXScript.g:3740:2: ( () otherlv_1= 'break' otherlv_2= ';' )
            // InternalXScript.g:3741:3: () otherlv_1= 'break' otherlv_2= ';'
            {
            // InternalXScript.g:3741:3: ()
            // InternalXScript.g:3742:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXWhileBreakTypeAccess().getXWhileBreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,61,FOLLOW_12); 

            			newLeafNode(otherlv_1, grammarAccess.getXWhileBreakTypeAccess().getBreakKeyword_1());
            		
            otherlv_2=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getXWhileBreakTypeAccess().getSemicolonKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXWhileBreakType"


    // $ANTLR start "entryRuleXSleepType"
    // InternalXScript.g:3760:1: entryRuleXSleepType returns [EObject current=null] : iv_ruleXSleepType= ruleXSleepType EOF ;
    public final EObject entryRuleXSleepType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSleepType = null;


        try {
            // InternalXScript.g:3760:51: (iv_ruleXSleepType= ruleXSleepType EOF )
            // InternalXScript.g:3761:2: iv_ruleXSleepType= ruleXSleepType EOF
            {
             newCompositeNode(grammarAccess.getXSleepTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXSleepType=ruleXSleepType();

            state._fsp--;

             current =iv_ruleXSleepType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXSleepType"


    // $ANTLR start "ruleXSleepType"
    // InternalXScript.g:3767:1: ruleXSleepType returns [EObject current=null] : ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' ) ;
    public final EObject ruleXSleepType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_interval_3_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Enumerator lv_unit_4_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3773:2: ( ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' ) )
            // InternalXScript.g:3774:2: ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' )
            {
            // InternalXScript.g:3774:2: ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' )
            // InternalXScript.g:3775:3: () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';'
            {
            // InternalXScript.g:3775:3: ()
            // InternalXScript.g:3776:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSleepTypeAccess().getXSleepTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,62,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSleepTypeAccess().getSleepKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getXSleepTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3790:3: ( (lv_interval_3_0= RULE_INT ) )
            // InternalXScript.g:3791:4: (lv_interval_3_0= RULE_INT )
            {
            // InternalXScript.g:3791:4: (lv_interval_3_0= RULE_INT )
            // InternalXScript.g:3792:5: lv_interval_3_0= RULE_INT
            {
            lv_interval_3_0=(Token)match(input,RULE_INT,FOLLOW_24); 

            					newLeafNode(lv_interval_3_0, grammarAccess.getXSleepTypeAccess().getIntervalINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSleepTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"interval",
            						lv_interval_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalXScript.g:3808:3: ( (lv_unit_4_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:3809:4: (lv_unit_4_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:3809:4: (lv_unit_4_0= ruleStreamTimeUnit )
            // InternalXScript.g:3810:5: lv_unit_4_0= ruleStreamTimeUnit
            {

            					newCompositeNode(grammarAccess.getXSleepTypeAccess().getUnitStreamTimeUnitEnumRuleCall_4_0());
            				
            pushFollow(FOLLOW_9);
            lv_unit_4_0=ruleStreamTimeUnit();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXSleepTypeRule());
            					}
            					set(
            						current,
            						"unit",
            						lv_unit_4_0,
            						"com.dunkware.xstream.XScript.StreamTimeUnit");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_5, grammarAccess.getXSleepTypeAccess().getRightParenthesisKeyword_5());
            		
            otherlv_6=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getXSleepTypeAccess().getSemicolonKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXSleepType"


    // $ANTLR start "entryRuleXExpressionType"
    // InternalXScript.g:3839:1: entryRuleXExpressionType returns [EObject current=null] : iv_ruleXExpressionType= ruleXExpressionType EOF ;
    public final EObject entryRuleXExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionType = null;


        try {
            // InternalXScript.g:3839:56: (iv_ruleXExpressionType= ruleXExpressionType EOF )
            // InternalXScript.g:3840:2: iv_ruleXExpressionType= ruleXExpressionType EOF
            {
             newCompositeNode(grammarAccess.getXExpressionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXExpressionType=ruleXExpressionType();

            state._fsp--;

             current =iv_ruleXExpressionType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXExpressionType"


    // $ANTLR start "ruleXExpressionType"
    // InternalXScript.g:3846:1: ruleXExpressionType returns [EObject current=null] : this_XOrType_0= ruleXOrType ;
    public final EObject ruleXExpressionType() throws RecognitionException {
        EObject current = null;

        EObject this_XOrType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3852:2: (this_XOrType_0= ruleXOrType )
            // InternalXScript.g:3853:2: this_XOrType_0= ruleXOrType
            {

            		newCompositeNode(grammarAccess.getXExpressionTypeAccess().getXOrTypeParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_XOrType_0=ruleXOrType();

            state._fsp--;


            		current = this_XOrType_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXExpressionType"


    // $ANTLR start "entryRuleXOrType"
    // InternalXScript.g:3864:1: entryRuleXOrType returns [EObject current=null] : iv_ruleXOrType= ruleXOrType EOF ;
    public final EObject entryRuleXOrType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOrType = null;


        try {
            // InternalXScript.g:3864:48: (iv_ruleXOrType= ruleXOrType EOF )
            // InternalXScript.g:3865:2: iv_ruleXOrType= ruleXOrType EOF
            {
             newCompositeNode(grammarAccess.getXOrTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXOrType=ruleXOrType();

            state._fsp--;

             current =iv_ruleXOrType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXOrType"


    // $ANTLR start "ruleXOrType"
    // InternalXScript.g:3871:1: ruleXOrType returns [EObject current=null] : (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* ) ;
    public final EObject ruleXOrType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XAndType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3877:2: ( (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* ) )
            // InternalXScript.g:3878:2: (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* )
            {
            // InternalXScript.g:3878:2: (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* )
            // InternalXScript.g:3879:3: this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXOrTypeAccess().getXAndTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_13);
            this_XAndType_0=ruleXAndType();

            state._fsp--;


            			current = this_XAndType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:3887:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )*
            loop38:
            do {
                int alt38=2;
                int LA38_0 = input.LA(1);

                if ( (LA38_0==18) ) {
                    alt38=1;
                }


                switch (alt38) {
            	case 1 :
            	    // InternalXScript.g:3888:4: () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) )
            	    {
            	    // InternalXScript.g:3888:4: ()
            	    // InternalXScript.g:3889:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXOrTypeAccess().getXOrTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,18,FOLLOW_32); 

            	    				newLeafNode(otherlv_2, grammarAccess.getXOrTypeAccess().getVerticalLineVerticalLineKeyword_1_1());
            	    			
            	    // InternalXScript.g:3899:4: ( (lv_right_3_0= ruleXAndType ) )
            	    // InternalXScript.g:3900:5: (lv_right_3_0= ruleXAndType )
            	    {
            	    // InternalXScript.g:3900:5: (lv_right_3_0= ruleXAndType )
            	    // InternalXScript.g:3901:6: lv_right_3_0= ruleXAndType
            	    {

            	    						newCompositeNode(grammarAccess.getXOrTypeAccess().getRightXAndTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_13);
            	    lv_right_3_0=ruleXAndType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXOrTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.XAndType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop38;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXOrType"


    // $ANTLR start "entryRuleXAndType"
    // InternalXScript.g:3923:1: entryRuleXAndType returns [EObject current=null] : iv_ruleXAndType= ruleXAndType EOF ;
    public final EObject entryRuleXAndType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAndType = null;


        try {
            // InternalXScript.g:3923:49: (iv_ruleXAndType= ruleXAndType EOF )
            // InternalXScript.g:3924:2: iv_ruleXAndType= ruleXAndType EOF
            {
             newCompositeNode(grammarAccess.getXAndTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXAndType=ruleXAndType();

            state._fsp--;

             current =iv_ruleXAndType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXAndType"


    // $ANTLR start "ruleXAndType"
    // InternalXScript.g:3930:1: ruleXAndType returns [EObject current=null] : (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* ) ;
    public final EObject ruleXAndType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XEqualityType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3936:2: ( (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* ) )
            // InternalXScript.g:3937:2: (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* )
            {
            // InternalXScript.g:3937:2: (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* )
            // InternalXScript.g:3938:3: this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXAndTypeAccess().getXEqualityTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_14);
            this_XEqualityType_0=ruleXEqualityType();

            state._fsp--;


            			current = this_XEqualityType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:3946:3: ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==19) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalXScript.g:3947:4: () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) )
            	    {
            	    // InternalXScript.g:3947:4: ()
            	    // InternalXScript.g:3948:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXAndTypeAccess().getXAndTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,19,FOLLOW_32); 

            	    				newLeafNode(otherlv_2, grammarAccess.getXAndTypeAccess().getAmpersandAmpersandKeyword_1_1());
            	    			
            	    // InternalXScript.g:3958:4: ( (lv_right_3_0= ruleXEqualityType ) )
            	    // InternalXScript.g:3959:5: (lv_right_3_0= ruleXEqualityType )
            	    {
            	    // InternalXScript.g:3959:5: (lv_right_3_0= ruleXEqualityType )
            	    // InternalXScript.g:3960:6: lv_right_3_0= ruleXEqualityType
            	    {

            	    						newCompositeNode(grammarAccess.getXAndTypeAccess().getRightXEqualityTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_right_3_0=ruleXEqualityType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXAndTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.XEqualityType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXAndType"


    // $ANTLR start "entryRuleXEqualityType"
    // InternalXScript.g:3982:1: entryRuleXEqualityType returns [EObject current=null] : iv_ruleXEqualityType= ruleXEqualityType EOF ;
    public final EObject entryRuleXEqualityType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXEqualityType = null;


        try {
            // InternalXScript.g:3982:54: (iv_ruleXEqualityType= ruleXEqualityType EOF )
            // InternalXScript.g:3983:2: iv_ruleXEqualityType= ruleXEqualityType EOF
            {
             newCompositeNode(grammarAccess.getXEqualityTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXEqualityType=ruleXEqualityType();

            state._fsp--;

             current =iv_ruleXEqualityType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXEqualityType"


    // $ANTLR start "ruleXEqualityType"
    // InternalXScript.g:3989:1: ruleXEqualityType returns [EObject current=null] : (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* ) ;
    public final EObject ruleXEqualityType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_XComparisonType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3995:2: ( (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* ) )
            // InternalXScript.g:3996:2: (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* )
            {
            // InternalXScript.g:3996:2: (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* )
            // InternalXScript.g:3997:3: this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXEqualityTypeAccess().getXComparisonTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_15);
            this_XComparisonType_0=ruleXComparisonType();

            state._fsp--;


            			current = this_XComparisonType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4005:3: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )*
            loop41:
            do {
                int alt41=2;
                int LA41_0 = input.LA(1);

                if ( ((LA41_0>=20 && LA41_0<=21)) ) {
                    alt41=1;
                }


                switch (alt41) {
            	case 1 :
            	    // InternalXScript.g:4006:4: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) )
            	    {
            	    // InternalXScript.g:4006:4: ()
            	    // InternalXScript.g:4007:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXEqualityTypeAccess().getXEqualityTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4013:4: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // InternalXScript.g:4014:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // InternalXScript.g:4014:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // InternalXScript.g:4015:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // InternalXScript.g:4015:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    int alt40=2;
            	    int LA40_0 = input.LA(1);

            	    if ( (LA40_0==20) ) {
            	        alt40=1;
            	    }
            	    else if ( (LA40_0==21) ) {
            	        alt40=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 40, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt40) {
            	        case 1 :
            	            // InternalXScript.g:4016:7: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,20,FOLLOW_32); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXEqualityTypeAccess().getOpEqualsSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXEqualityTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4027:7: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,21,FOLLOW_32); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getXEqualityTypeAccess().getOpExclamationMarkEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXEqualityTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalXScript.g:4040:4: ( (lv_right_3_0= ruleXComparisonType ) )
            	    // InternalXScript.g:4041:5: (lv_right_3_0= ruleXComparisonType )
            	    {
            	    // InternalXScript.g:4041:5: (lv_right_3_0= ruleXComparisonType )
            	    // InternalXScript.g:4042:6: lv_right_3_0= ruleXComparisonType
            	    {

            	    						newCompositeNode(grammarAccess.getXEqualityTypeAccess().getRightXComparisonTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_15);
            	    lv_right_3_0=ruleXComparisonType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXEqualityTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.XComparisonType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop41;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXEqualityType"


    // $ANTLR start "entryRuleXComparisonType"
    // InternalXScript.g:4064:1: entryRuleXComparisonType returns [EObject current=null] : iv_ruleXComparisonType= ruleXComparisonType EOF ;
    public final EObject entryRuleXComparisonType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXComparisonType = null;


        try {
            // InternalXScript.g:4064:56: (iv_ruleXComparisonType= ruleXComparisonType EOF )
            // InternalXScript.g:4065:2: iv_ruleXComparisonType= ruleXComparisonType EOF
            {
             newCompositeNode(grammarAccess.getXComparisonTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXComparisonType=ruleXComparisonType();

            state._fsp--;

             current =iv_ruleXComparisonType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXComparisonType"


    // $ANTLR start "ruleXComparisonType"
    // InternalXScript.g:4071:1: ruleXComparisonType returns [EObject current=null] : (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* ) ;
    public final EObject ruleXComparisonType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        Token lv_op_2_4=null;
        EObject this_XPlusOrMinusType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4077:2: ( (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* ) )
            // InternalXScript.g:4078:2: (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* )
            {
            // InternalXScript.g:4078:2: (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* )
            // InternalXScript.g:4079:3: this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXComparisonTypeAccess().getXPlusOrMinusTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_16);
            this_XPlusOrMinusType_0=ruleXPlusOrMinusType();

            state._fsp--;


            			current = this_XPlusOrMinusType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4087:3: ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( ((LA43_0>=22 && LA43_0<=25)) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // InternalXScript.g:4088:4: () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) )
            	    {
            	    // InternalXScript.g:4088:4: ()
            	    // InternalXScript.g:4089:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXComparisonTypeAccess().getXComparisonTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4095:4: ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) )
            	    // InternalXScript.g:4096:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    {
            	    // InternalXScript.g:4096:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    // InternalXScript.g:4097:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    {
            	    // InternalXScript.g:4097:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    int alt42=4;
            	    switch ( input.LA(1) ) {
            	    case 22:
            	        {
            	        alt42=1;
            	        }
            	        break;
            	    case 23:
            	        {
            	        alt42=2;
            	        }
            	        break;
            	    case 24:
            	        {
            	        alt42=3;
            	        }
            	        break;
            	    case 25:
            	        {
            	        alt42=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 42, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt42) {
            	        case 1 :
            	            // InternalXScript.g:4098:7: lv_op_2_1= '>='
            	            {
            	            lv_op_2_1=(Token)match(input,22,FOLLOW_32); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXComparisonTypeAccess().getOpGreaterThanSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4109:7: lv_op_2_2= '<='
            	            {
            	            lv_op_2_2=(Token)match(input,23,FOLLOW_32); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getXComparisonTypeAccess().getOpLessThanSignEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalXScript.g:4120:7: lv_op_2_3= '>'
            	            {
            	            lv_op_2_3=(Token)match(input,24,FOLLOW_32); 

            	            							newLeafNode(lv_op_2_3, grammarAccess.getXComparisonTypeAccess().getOpGreaterThanSignKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalXScript.g:4131:7: lv_op_2_4= '<'
            	            {
            	            lv_op_2_4=(Token)match(input,25,FOLLOW_32); 

            	            							newLeafNode(lv_op_2_4, grammarAccess.getXComparisonTypeAccess().getOpLessThanSignKeyword_1_1_0_3());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_4, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalXScript.g:4144:4: ( (lv_right_3_0= ruleXPlusOrMinusType ) )
            	    // InternalXScript.g:4145:5: (lv_right_3_0= ruleXPlusOrMinusType )
            	    {
            	    // InternalXScript.g:4145:5: (lv_right_3_0= ruleXPlusOrMinusType )
            	    // InternalXScript.g:4146:6: lv_right_3_0= ruleXPlusOrMinusType
            	    {

            	    						newCompositeNode(grammarAccess.getXComparisonTypeAccess().getRightXPlusOrMinusTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_16);
            	    lv_right_3_0=ruleXPlusOrMinusType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXComparisonTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.XPlusOrMinusType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXComparisonType"


    // $ANTLR start "entryRuleXPlusOrMinusType"
    // InternalXScript.g:4168:1: entryRuleXPlusOrMinusType returns [EObject current=null] : iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF ;
    public final EObject entryRuleXPlusOrMinusType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPlusOrMinusType = null;


        try {
            // InternalXScript.g:4168:57: (iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF )
            // InternalXScript.g:4169:2: iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF
            {
             newCompositeNode(grammarAccess.getXPlusOrMinusTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXPlusOrMinusType=ruleXPlusOrMinusType();

            state._fsp--;

             current =iv_ruleXPlusOrMinusType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXPlusOrMinusType"


    // $ANTLR start "ruleXPlusOrMinusType"
    // InternalXScript.g:4175:1: ruleXPlusOrMinusType returns [EObject current=null] : (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* ) ;
    public final EObject ruleXPlusOrMinusType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_XMulOrDivType_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4181:2: ( (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* ) )
            // InternalXScript.g:4182:2: (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* )
            {
            // InternalXScript.g:4182:2: (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* )
            // InternalXScript.g:4183:3: this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXPlusOrMinusTypeAccess().getXMulOrDivTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_17);
            this_XMulOrDivType_0=ruleXMulOrDivType();

            state._fsp--;


            			current = this_XMulOrDivType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4191:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )*
            loop45:
            do {
                int alt45=2;
                int LA45_0 = input.LA(1);

                if ( ((LA45_0>=26 && LA45_0<=27)) ) {
                    alt45=1;
                }


                switch (alt45) {
            	case 1 :
            	    // InternalXScript.g:4192:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) )
            	    {
            	    // InternalXScript.g:4192:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt44=2;
            	    int LA44_0 = input.LA(1);

            	    if ( (LA44_0==26) ) {
            	        alt44=1;
            	    }
            	    else if ( (LA44_0==27) ) {
            	        alt44=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 44, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt44) {
            	        case 1 :
            	            // InternalXScript.g:4193:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalXScript.g:4193:5: ( () otherlv_2= '+' )
            	            // InternalXScript.g:4194:6: () otherlv_2= '+'
            	            {
            	            // InternalXScript.g:4194:6: ()
            	            // InternalXScript.g:4195:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getXPlusOrMinusTypeAccess().getXPlusTypeLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,26,FOLLOW_32); 

            	            						newLeafNode(otherlv_2, grammarAccess.getXPlusOrMinusTypeAccess().getPlusSignKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4207:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalXScript.g:4207:5: ( () otherlv_4= '-' )
            	            // InternalXScript.g:4208:6: () otherlv_4= '-'
            	            {
            	            // InternalXScript.g:4208:6: ()
            	            // InternalXScript.g:4209:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getXPlusOrMinusTypeAccess().getXMinusTypeLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,27,FOLLOW_32); 

            	            						newLeafNode(otherlv_4, grammarAccess.getXPlusOrMinusTypeAccess().getHyphenMinusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalXScript.g:4221:4: ( (lv_right_5_0= ruleXMulOrDivType ) )
            	    // InternalXScript.g:4222:5: (lv_right_5_0= ruleXMulOrDivType )
            	    {
            	    // InternalXScript.g:4222:5: (lv_right_5_0= ruleXMulOrDivType )
            	    // InternalXScript.g:4223:6: lv_right_5_0= ruleXMulOrDivType
            	    {

            	    						newCompositeNode(grammarAccess.getXPlusOrMinusTypeAccess().getRightXMulOrDivTypeParserRuleCall_1_1_0());
            	    					
            	    pushFollow(FOLLOW_17);
            	    lv_right_5_0=ruleXMulOrDivType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXPlusOrMinusTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_5_0,
            	    							"com.dunkware.xstream.XScript.XMulOrDivType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop45;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXPlusOrMinusType"


    // $ANTLR start "entryRuleXMulOrDivType"
    // InternalXScript.g:4245:1: entryRuleXMulOrDivType returns [EObject current=null] : iv_ruleXMulOrDivType= ruleXMulOrDivType EOF ;
    public final EObject entryRuleXMulOrDivType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXMulOrDivType = null;


        try {
            // InternalXScript.g:4245:54: (iv_ruleXMulOrDivType= ruleXMulOrDivType EOF )
            // InternalXScript.g:4246:2: iv_ruleXMulOrDivType= ruleXMulOrDivType EOF
            {
             newCompositeNode(grammarAccess.getXMulOrDivTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXMulOrDivType=ruleXMulOrDivType();

            state._fsp--;

             current =iv_ruleXMulOrDivType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXMulOrDivType"


    // $ANTLR start "ruleXMulOrDivType"
    // InternalXScript.g:4252:1: ruleXMulOrDivType returns [EObject current=null] : (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* ) ;
    public final EObject ruleXMulOrDivType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_XPrimaryType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4258:2: ( (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* ) )
            // InternalXScript.g:4259:2: (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* )
            {
            // InternalXScript.g:4259:2: (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* )
            // InternalXScript.g:4260:3: this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXMulOrDivTypeAccess().getXPrimaryTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_18);
            this_XPrimaryType_0=ruleXPrimaryType();

            state._fsp--;


            			current = this_XPrimaryType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4268:3: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )*
            loop47:
            do {
                int alt47=2;
                int LA47_0 = input.LA(1);

                if ( ((LA47_0>=28 && LA47_0<=29)) ) {
                    alt47=1;
                }


                switch (alt47) {
            	case 1 :
            	    // InternalXScript.g:4269:4: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) )
            	    {
            	    // InternalXScript.g:4269:4: ()
            	    // InternalXScript.g:4270:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXMulOrDivTypeAccess().getXMulOrDivTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4276:4: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // InternalXScript.g:4277:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // InternalXScript.g:4277:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // InternalXScript.g:4278:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // InternalXScript.g:4278:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt46=2;
            	    int LA46_0 = input.LA(1);

            	    if ( (LA46_0==28) ) {
            	        alt46=1;
            	    }
            	    else if ( (LA46_0==29) ) {
            	        alt46=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 46, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt46) {
            	        case 1 :
            	            // InternalXScript.g:4279:7: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,28,FOLLOW_32); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXMulOrDivTypeAccess().getOpAsteriskKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXMulOrDivTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4290:7: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,29,FOLLOW_32); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getXMulOrDivTypeAccess().getOpSolidusKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXMulOrDivTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;

            	    }


            	    }


            	    }

            	    // InternalXScript.g:4303:4: ( (lv_right_3_0= ruleXPrimaryType ) )
            	    // InternalXScript.g:4304:5: (lv_right_3_0= ruleXPrimaryType )
            	    {
            	    // InternalXScript.g:4304:5: (lv_right_3_0= ruleXPrimaryType )
            	    // InternalXScript.g:4305:6: lv_right_3_0= ruleXPrimaryType
            	    {

            	    						newCompositeNode(grammarAccess.getXMulOrDivTypeAccess().getRightXPrimaryTypeParserRuleCall_1_2_0());
            	    					
            	    pushFollow(FOLLOW_18);
            	    lv_right_3_0=ruleXPrimaryType();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getXMulOrDivTypeRule());
            	    						}
            	    						set(
            	    							current,
            	    							"right",
            	    							lv_right_3_0,
            	    							"com.dunkware.xstream.XScript.XPrimaryType");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop47;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXMulOrDivType"


    // $ANTLR start "entryRuleXPrimaryType"
    // InternalXScript.g:4327:1: entryRuleXPrimaryType returns [EObject current=null] : iv_ruleXPrimaryType= ruleXPrimaryType EOF ;
    public final EObject entryRuleXPrimaryType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPrimaryType = null;


        try {
            // InternalXScript.g:4327:53: (iv_ruleXPrimaryType= ruleXPrimaryType EOF )
            // InternalXScript.g:4328:2: iv_ruleXPrimaryType= ruleXPrimaryType EOF
            {
             newCompositeNode(grammarAccess.getXPrimaryTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXPrimaryType=ruleXPrimaryType();

            state._fsp--;

             current =iv_ruleXPrimaryType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXPrimaryType"


    // $ANTLR start "ruleXPrimaryType"
    // InternalXScript.g:4334:1: ruleXPrimaryType returns [EObject current=null] : ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType ) ;
    public final EObject ruleXPrimaryType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_XExpressionType_1 = null;

        EObject lv_expression_5_0 = null;

        EObject this_XAtomicType_6 = null;



        	enterRule();

        try {
            // InternalXScript.g:4340:2: ( ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType ) )
            // InternalXScript.g:4341:2: ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType )
            {
            // InternalXScript.g:4341:2: ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType )
            int alt48=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt48=1;
                }
                break;
            case 30:
                {
                alt48=2;
                }
                break;
            case RULE_ID:
            case RULE_INT:
            case RULE_DOUBLE:
            case RULE_STRING:
            case 31:
            case 32:
            case 36:
            case 42:
            case 63:
            case 64:
            case 70:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
                {
                alt48=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 48, 0, input);

                throw nvae;
            }

            switch (alt48) {
                case 1 :
                    // InternalXScript.g:4342:3: (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' )
                    {
                    // InternalXScript.g:4342:3: (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' )
                    // InternalXScript.g:4343:4: otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,13,FOLLOW_32); 

                    				newLeafNode(otherlv_0, grammarAccess.getXPrimaryTypeAccess().getLeftParenthesisKeyword_0_0());
                    			

                    				newCompositeNode(grammarAccess.getXPrimaryTypeAccess().getXExpressionTypeParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_9);
                    this_XExpressionType_1=ruleXExpressionType();

                    state._fsp--;


                    				current = this_XExpressionType_1;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_2=(Token)match(input,15,FOLLOW_2); 

                    				newLeafNode(otherlv_2, grammarAccess.getXPrimaryTypeAccess().getRightParenthesisKeyword_0_2());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:4361:3: ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) )
                    {
                    // InternalXScript.g:4361:3: ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) )
                    // InternalXScript.g:4362:4: () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) )
                    {
                    // InternalXScript.g:4362:4: ()
                    // InternalXScript.g:4363:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXPrimaryTypeAccess().getXNotTypeAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_4=(Token)match(input,30,FOLLOW_32); 

                    				newLeafNode(otherlv_4, grammarAccess.getXPrimaryTypeAccess().getExclamationMarkKeyword_1_1());
                    			
                    // InternalXScript.g:4373:4: ( (lv_expression_5_0= ruleXPrimaryType ) )
                    // InternalXScript.g:4374:5: (lv_expression_5_0= ruleXPrimaryType )
                    {
                    // InternalXScript.g:4374:5: (lv_expression_5_0= ruleXPrimaryType )
                    // InternalXScript.g:4375:6: lv_expression_5_0= ruleXPrimaryType
                    {

                    						newCompositeNode(grammarAccess.getXPrimaryTypeAccess().getExpressionXPrimaryTypeParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_expression_5_0=ruleXPrimaryType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getXPrimaryTypeRule());
                    						}
                    						set(
                    							current,
                    							"expression",
                    							lv_expression_5_0,
                    							"com.dunkware.xstream.XScript.XPrimaryType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:4394:3: this_XAtomicType_6= ruleXAtomicType
                    {

                    			newCompositeNode(grammarAccess.getXPrimaryTypeAccess().getXAtomicTypeParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_XAtomicType_6=ruleXAtomicType();

                    state._fsp--;


                    			current = this_XAtomicType_6;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXPrimaryType"


    // $ANTLR start "entryRuleXAtomicType"
    // InternalXScript.g:4406:1: entryRuleXAtomicType returns [EObject current=null] : iv_ruleXAtomicType= ruleXAtomicType EOF ;
    public final EObject entryRuleXAtomicType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAtomicType = null;


        try {
            // InternalXScript.g:4406:52: (iv_ruleXAtomicType= ruleXAtomicType EOF )
            // InternalXScript.g:4407:2: iv_ruleXAtomicType= ruleXAtomicType EOF
            {
             newCompositeNode(grammarAccess.getXAtomicTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXAtomicType=ruleXAtomicType();

            state._fsp--;

             current =iv_ruleXAtomicType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXAtomicType"


    // $ANTLR start "ruleXAtomicType"
    // InternalXScript.g:4413:1: ruleXAtomicType returns [EObject current=null] : this_XAtomicBaseType_0= ruleXAtomicBaseType ;
    public final EObject ruleXAtomicType() throws RecognitionException {
        EObject current = null;

        EObject this_XAtomicBaseType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4419:2: (this_XAtomicBaseType_0= ruleXAtomicBaseType )
            // InternalXScript.g:4420:2: this_XAtomicBaseType_0= ruleXAtomicBaseType
            {

            		newCompositeNode(grammarAccess.getXAtomicTypeAccess().getXAtomicBaseTypeParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_XAtomicBaseType_0=ruleXAtomicBaseType();

            state._fsp--;


            		current = this_XAtomicBaseType_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXAtomicType"


    // $ANTLR start "entryRuleXAtomicBaseType"
    // InternalXScript.g:4431:1: entryRuleXAtomicBaseType returns [EObject current=null] : iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF ;
    public final EObject entryRuleXAtomicBaseType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAtomicBaseType = null;


        try {
            // InternalXScript.g:4431:56: (iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF )
            // InternalXScript.g:4432:2: iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF
            {
             newCompositeNode(grammarAccess.getXAtomicBaseTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXAtomicBaseType=ruleXAtomicBaseType();

            state._fsp--;

             current =iv_ruleXAtomicBaseType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXAtomicBaseType"


    // $ANTLR start "ruleXAtomicBaseType"
    // InternalXScript.g:4438:1: ruleXAtomicBaseType returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType ) ;
    public final EObject ruleXAtomicBaseType() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;
        Token lv_value_3_0=null;
        Token lv_value_5_0=null;
        Token lv_value_7_1=null;
        Token lv_value_7_2=null;
        EObject this_XPercentChangeExpType_8 = null;

        EObject this_XSubExpType_9 = null;

        EObject this_XVarExpType_10 = null;

        EObject this_XStreamWrapperExpType_11 = null;

        EObject this_XStreamVarValueExpType_12 = null;

        EObject this_XFunctionCallExpType_13 = null;

        EObject this_XVarStreakType_14 = null;

        EObject this_XVarCompareStreakType_15 = null;

        EObject this_XSlrAvgExpType_16 = null;

        EObject this_XLastSignalTriggerType_17 = null;

        EObject this_XSignalTriggerCountType_18 = null;

        EObject this_XVarianceAverageType_19 = null;

        EObject this_XRocExpType_20 = null;

        EObject this_XVarianceMaxType_21 = null;



        	enterRule();

        try {
            // InternalXScript.g:4444:2: ( ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType ) )
            // InternalXScript.g:4445:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )
            {
            // InternalXScript.g:4445:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )
            int alt50=18;
            alt50 = dfa50.predict(input);
            switch (alt50) {
                case 1 :
                    // InternalXScript.g:4446:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    {
                    // InternalXScript.g:4446:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    // InternalXScript.g:4447:4: () ( (lv_value_1_0= RULE_DOUBLE ) )
                    {
                    // InternalXScript.g:4447:4: ()
                    // InternalXScript.g:4448:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXDoubleConstantTypeAction_0_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:4454:4: ( (lv_value_1_0= RULE_DOUBLE ) )
                    // InternalXScript.g:4455:5: (lv_value_1_0= RULE_DOUBLE )
                    {
                    // InternalXScript.g:4455:5: (lv_value_1_0= RULE_DOUBLE )
                    // InternalXScript.g:4456:6: lv_value_1_0= RULE_DOUBLE
                    {
                    lv_value_1_0=(Token)match(input,RULE_DOUBLE,FOLLOW_2); 

                    						newLeafNode(lv_value_1_0, grammarAccess.getXAtomicBaseTypeAccess().getValueDOUBLETerminalRuleCall_0_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXAtomicBaseTypeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_1_0,
                    							"com.dunkware.xstream.XScript.DOUBLE");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:4474:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    {
                    // InternalXScript.g:4474:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    // InternalXScript.g:4475:4: () ( (lv_value_3_0= RULE_INT ) )
                    {
                    // InternalXScript.g:4475:4: ()
                    // InternalXScript.g:4476:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXIntConstantTypeAction_1_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:4482:4: ( (lv_value_3_0= RULE_INT ) )
                    // InternalXScript.g:4483:5: (lv_value_3_0= RULE_INT )
                    {
                    // InternalXScript.g:4483:5: (lv_value_3_0= RULE_INT )
                    // InternalXScript.g:4484:6: lv_value_3_0= RULE_INT
                    {
                    lv_value_3_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_value_3_0, grammarAccess.getXAtomicBaseTypeAccess().getValueINTTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXAtomicBaseTypeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_3_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:4502:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    {
                    // InternalXScript.g:4502:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    // InternalXScript.g:4503:4: () ( (lv_value_5_0= RULE_STRING ) )
                    {
                    // InternalXScript.g:4503:4: ()
                    // InternalXScript.g:4504:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXStringConstantTypeAction_2_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:4510:4: ( (lv_value_5_0= RULE_STRING ) )
                    // InternalXScript.g:4511:5: (lv_value_5_0= RULE_STRING )
                    {
                    // InternalXScript.g:4511:5: (lv_value_5_0= RULE_STRING )
                    // InternalXScript.g:4512:6: lv_value_5_0= RULE_STRING
                    {
                    lv_value_5_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_value_5_0, grammarAccess.getXAtomicBaseTypeAccess().getValueSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXAtomicBaseTypeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_5_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalXScript.g:4530:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    {
                    // InternalXScript.g:4530:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    // InternalXScript.g:4531:4: () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    {
                    // InternalXScript.g:4531:4: ()
                    // InternalXScript.g:4532:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXBoolConstantTypeAction_3_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:4538:4: ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    // InternalXScript.g:4539:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    {
                    // InternalXScript.g:4539:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    // InternalXScript.g:4540:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    {
                    // InternalXScript.g:4540:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    int alt49=2;
                    int LA49_0 = input.LA(1);

                    if ( (LA49_0==31) ) {
                        alt49=1;
                    }
                    else if ( (LA49_0==32) ) {
                        alt49=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 49, 0, input);

                        throw nvae;
                    }
                    switch (alt49) {
                        case 1 :
                            // InternalXScript.g:4541:7: lv_value_7_1= 'true'
                            {
                            lv_value_7_1=(Token)match(input,31,FOLLOW_2); 

                            							newLeafNode(lv_value_7_1, grammarAccess.getXAtomicBaseTypeAccess().getValueTrueKeyword_3_1_0_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getXAtomicBaseTypeRule());
                            							}
                            							setWithLastConsumed(current, "value", lv_value_7_1, null);
                            						

                            }
                            break;
                        case 2 :
                            // InternalXScript.g:4552:7: lv_value_7_2= 'false'
                            {
                            lv_value_7_2=(Token)match(input,32,FOLLOW_2); 

                            							newLeafNode(lv_value_7_2, grammarAccess.getXAtomicBaseTypeAccess().getValueFalseKeyword_3_1_0_1());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getXAtomicBaseTypeRule());
                            							}
                            							setWithLastConsumed(current, "value", lv_value_7_2, null);
                            						

                            }
                            break;

                    }


                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalXScript.g:4567:3: this_XPercentChangeExpType_8= ruleXPercentChangeExpType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXPercentChangeExpTypeParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_XPercentChangeExpType_8=ruleXPercentChangeExpType();

                    state._fsp--;


                    			current = this_XPercentChangeExpType_8;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 6 :
                    // InternalXScript.g:4576:3: this_XSubExpType_9= ruleXSubExpType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXSubExpTypeParserRuleCall_5());
                    		
                    pushFollow(FOLLOW_2);
                    this_XSubExpType_9=ruleXSubExpType();

                    state._fsp--;


                    			current = this_XSubExpType_9;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 7 :
                    // InternalXScript.g:4585:3: this_XVarExpType_10= ruleXVarExpType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXVarExpTypeParserRuleCall_6());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarExpType_10=ruleXVarExpType();

                    state._fsp--;


                    			current = this_XVarExpType_10;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 8 :
                    // InternalXScript.g:4594:3: this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXStreamWrapperExpTypeParserRuleCall_7());
                    		
                    pushFollow(FOLLOW_2);
                    this_XStreamWrapperExpType_11=ruleXStreamWrapperExpType();

                    state._fsp--;


                    			current = this_XStreamWrapperExpType_11;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 9 :
                    // InternalXScript.g:4603:3: this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXStreamVarValueExpTypeParserRuleCall_8());
                    		
                    pushFollow(FOLLOW_2);
                    this_XStreamVarValueExpType_12=ruleXStreamVarValueExpType();

                    state._fsp--;


                    			current = this_XStreamVarValueExpType_12;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 10 :
                    // InternalXScript.g:4612:3: this_XFunctionCallExpType_13= ruleXFunctionCallExpType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXFunctionCallExpTypeParserRuleCall_9());
                    		
                    pushFollow(FOLLOW_2);
                    this_XFunctionCallExpType_13=ruleXFunctionCallExpType();

                    state._fsp--;


                    			current = this_XFunctionCallExpType_13;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 11 :
                    // InternalXScript.g:4621:3: this_XVarStreakType_14= ruleXVarStreakType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXVarStreakTypeParserRuleCall_10());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarStreakType_14=ruleXVarStreakType();

                    state._fsp--;


                    			current = this_XVarStreakType_14;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 12 :
                    // InternalXScript.g:4630:3: this_XVarCompareStreakType_15= ruleXVarCompareStreakType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXVarCompareStreakTypeParserRuleCall_11());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarCompareStreakType_15=ruleXVarCompareStreakType();

                    state._fsp--;


                    			current = this_XVarCompareStreakType_15;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 13 :
                    // InternalXScript.g:4639:3: this_XSlrAvgExpType_16= ruleXSlrAvgExpType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXSlrAvgExpTypeParserRuleCall_12());
                    		
                    pushFollow(FOLLOW_2);
                    this_XSlrAvgExpType_16=ruleXSlrAvgExpType();

                    state._fsp--;


                    			current = this_XSlrAvgExpType_16;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 14 :
                    // InternalXScript.g:4648:3: this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXLastSignalTriggerTypeParserRuleCall_13());
                    		
                    pushFollow(FOLLOW_2);
                    this_XLastSignalTriggerType_17=ruleXLastSignalTriggerType();

                    state._fsp--;


                    			current = this_XLastSignalTriggerType_17;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 15 :
                    // InternalXScript.g:4657:3: this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXSignalTriggerCountTypeParserRuleCall_14());
                    		
                    pushFollow(FOLLOW_2);
                    this_XSignalTriggerCountType_18=ruleXSignalTriggerCountType();

                    state._fsp--;


                    			current = this_XSignalTriggerCountType_18;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 16 :
                    // InternalXScript.g:4666:3: this_XVarianceAverageType_19= ruleXVarianceAverageType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXVarianceAverageTypeParserRuleCall_15());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarianceAverageType_19=ruleXVarianceAverageType();

                    state._fsp--;


                    			current = this_XVarianceAverageType_19;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 17 :
                    // InternalXScript.g:4675:3: this_XRocExpType_20= ruleXRocExpType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXRocExpTypeParserRuleCall_16());
                    		
                    pushFollow(FOLLOW_2);
                    this_XRocExpType_20=ruleXRocExpType();

                    state._fsp--;


                    			current = this_XRocExpType_20;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 18 :
                    // InternalXScript.g:4684:3: this_XVarianceMaxType_21= ruleXVarianceMaxType
                    {

                    			newCompositeNode(grammarAccess.getXAtomicBaseTypeAccess().getXVarianceMaxTypeParserRuleCall_17());
                    		
                    pushFollow(FOLLOW_2);
                    this_XVarianceMaxType_21=ruleXVarianceMaxType();

                    state._fsp--;


                    			current = this_XVarianceMaxType_21;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXAtomicBaseType"


    // $ANTLR start "entryRuleXPercentChangeExpType"
    // InternalXScript.g:4696:1: entryRuleXPercentChangeExpType returns [EObject current=null] : iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF ;
    public final EObject entryRuleXPercentChangeExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPercentChangeExpType = null;


        try {
            // InternalXScript.g:4696:62: (iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF )
            // InternalXScript.g:4697:2: iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF
            {
             newCompositeNode(grammarAccess.getXPercentChangeExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXPercentChangeExpType=ruleXPercentChangeExpType();

            state._fsp--;

             current =iv_ruleXPercentChangeExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXPercentChangeExpType"


    // $ANTLR start "ruleXPercentChangeExpType"
    // InternalXScript.g:4703:1: ruleXPercentChangeExpType returns [EObject current=null] : ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
    public final EObject ruleXPercentChangeExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_value1_3_0 = null;

        EObject lv_value2_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4709:2: ( ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:4710:2: ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:4710:2: ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:4711:3: () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:4711:3: ()
            // InternalXScript.g:4712:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXPercentChangeExpTypeAccess().getXPercentChangeExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,63,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXPercentChangeExpTypeAccess().getPercentChangeKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_32); 

            			newLeafNode(otherlv_2, grammarAccess.getXPercentChangeExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:4726:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:4727:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:4727:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:4728:5: lv_value1_3_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXPercentChangeExpTypeAccess().getValue1XExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_value1_3_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXPercentChangeExpTypeRule());
            					}
            					set(
            						current,
            						"value1",
            						lv_value1_3_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_4, grammarAccess.getXPercentChangeExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:4749:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:4750:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:4750:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:4751:5: lv_value2_5_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXPercentChangeExpTypeAccess().getValue2XExpressionTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_value2_5_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXPercentChangeExpTypeRule());
            					}
            					set(
            						current,
            						"value2",
            						lv_value2_5_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getXPercentChangeExpTypeAccess().getRightParenthesisKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXPercentChangeExpType"


    // $ANTLR start "entryRuleXSubExpType"
    // InternalXScript.g:4776:1: entryRuleXSubExpType returns [EObject current=null] : iv_ruleXSubExpType= ruleXSubExpType EOF ;
    public final EObject entryRuleXSubExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSubExpType = null;


        try {
            // InternalXScript.g:4776:52: (iv_ruleXSubExpType= ruleXSubExpType EOF )
            // InternalXScript.g:4777:2: iv_ruleXSubExpType= ruleXSubExpType EOF
            {
             newCompositeNode(grammarAccess.getXSubExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXSubExpType=ruleXSubExpType();

            state._fsp--;

             current =iv_ruleXSubExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXSubExpType"


    // $ANTLR start "ruleXSubExpType"
    // InternalXScript.g:4783:1: ruleXSubExpType returns [EObject current=null] : ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
    public final EObject ruleXSubExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_value1_3_0 = null;

        EObject lv_value2_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4789:2: ( ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:4790:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:4790:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:4791:3: () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:4791:3: ()
            // InternalXScript.g:4792:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSubExpTypeAccess().getXSubExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,42,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSubExpTypeAccess().getSubKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_32); 

            			newLeafNode(otherlv_2, grammarAccess.getXSubExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:4806:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:4807:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:4807:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:4808:5: lv_value1_3_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXSubExpTypeAccess().getValue1XExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_value1_3_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXSubExpTypeRule());
            					}
            					set(
            						current,
            						"value1",
            						lv_value1_3_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_4, grammarAccess.getXSubExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:4829:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:4830:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:4830:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:4831:5: lv_value2_5_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXSubExpTypeAccess().getValue2XExpressionTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_value2_5_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXSubExpTypeRule());
            					}
            					set(
            						current,
            						"value2",
            						lv_value2_5_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getXSubExpTypeAccess().getRightParenthesisKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXSubExpType"


    // $ANTLR start "entryRuleXVarExpType"
    // InternalXScript.g:4856:1: entryRuleXVarExpType returns [EObject current=null] : iv_ruleXVarExpType= ruleXVarExpType EOF ;
    public final EObject entryRuleXVarExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarExpType = null;


        try {
            // InternalXScript.g:4856:52: (iv_ruleXVarExpType= ruleXVarExpType EOF )
            // InternalXScript.g:4857:2: iv_ruleXVarExpType= ruleXVarExpType EOF
            {
             newCompositeNode(grammarAccess.getXVarExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarExpType=ruleXVarExpType();

            state._fsp--;

             current =iv_ruleXVarExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarExpType"


    // $ANTLR start "ruleXVarExpType"
    // InternalXScript.g:4863:1: ruleXVarExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleXVarExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalXScript.g:4869:2: ( ( () ( (otherlv_1= RULE_ID ) ) ) )
            // InternalXScript.g:4870:2: ( () ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalXScript.g:4870:2: ( () ( (otherlv_1= RULE_ID ) ) )
            // InternalXScript.g:4871:3: () ( (otherlv_1= RULE_ID ) )
            {
            // InternalXScript.g:4871:3: ()
            // InternalXScript.g:4872:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarExpTypeAccess().getXVarExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:4878:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:4879:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:4879:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:4880:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarExpTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarExpTypeAccess().getExpVarXVarTypeCrossReference_1_0());
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarExpType"


    // $ANTLR start "entryRuleXStreamWrapperExpType"
    // InternalXScript.g:4895:1: entryRuleXStreamWrapperExpType returns [EObject current=null] : iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF ;
    public final EObject entryRuleXStreamWrapperExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamWrapperExpType = null;


        try {
            // InternalXScript.g:4895:62: (iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF )
            // InternalXScript.g:4896:2: iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF
            {
             newCompositeNode(grammarAccess.getXStreamWrapperExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXStreamWrapperExpType=ruleXStreamWrapperExpType();

            state._fsp--;

             current =iv_ruleXStreamWrapperExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXStreamWrapperExpType"


    // $ANTLR start "ruleXStreamWrapperExpType"
    // InternalXScript.g:4902:1: ruleXStreamWrapperExpType returns [EObject current=null] : ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' ) ;
    public final EObject ruleXStreamWrapperExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_wrapperExp_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4908:2: ( ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' ) )
            // InternalXScript.g:4909:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:4909:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            // InternalXScript.g:4910:3: () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')'
            {
            // InternalXScript.g:4910:3: ()
            // InternalXScript.g:4911:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamWrapperExpTypeAccess().getXStreamWrapperExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,36,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXStreamWrapperExpTypeAccess().getExpKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getXStreamWrapperExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:4925:3: ( (lv_wrapperExp_3_0= ruleExpressionType ) )
            // InternalXScript.g:4926:4: (lv_wrapperExp_3_0= ruleExpressionType )
            {
            // InternalXScript.g:4926:4: (lv_wrapperExp_3_0= ruleExpressionType )
            // InternalXScript.g:4927:5: lv_wrapperExp_3_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getXStreamWrapperExpTypeAccess().getWrapperExpExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_9);
            lv_wrapperExp_3_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXStreamWrapperExpTypeRule());
            					}
            					set(
            						current,
            						"wrapperExp",
            						lv_wrapperExp_3_0,
            						"com.dunkware.xstream.XScript.ExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getXStreamWrapperExpTypeAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXStreamWrapperExpType"


    // $ANTLR start "entryRuleXStreamVarValueExpType"
    // InternalXScript.g:4952:1: entryRuleXStreamVarValueExpType returns [EObject current=null] : iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF ;
    public final EObject entryRuleXStreamVarValueExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamVarValueExpType = null;


        try {
            // InternalXScript.g:4952:63: (iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF )
            // InternalXScript.g:4953:2: iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF
            {
             newCompositeNode(grammarAccess.getXStreamVarValueExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXStreamVarValueExpType=ruleXStreamVarValueExpType();

            state._fsp--;

             current =iv_ruleXStreamVarValueExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXStreamVarValueExpType"


    // $ANTLR start "ruleXStreamVarValueExpType"
    // InternalXScript.g:4959:1: ruleXStreamVarValueExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' ) ;
    public final EObject ruleXStreamVarValueExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expressionValue_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4965:2: ( ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' ) )
            // InternalXScript.g:4966:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' )
            {
            // InternalXScript.g:4966:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' )
            // InternalXScript.g:4967:3: () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']'
            {
            // InternalXScript.g:4967:3: ()
            // InternalXScript.g:4968:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamVarValueExpTypeAccess().getXStreamVarValueExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:4974:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:4975:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:4975:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:4976:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXStreamVarValueExpTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(otherlv_1, grammarAccess.getXStreamVarValueExpTypeAccess().getVarVarTypeCrossReference_1_0());
            				

            }


            }

            // InternalXScript.g:4987:3: (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) )
            // InternalXScript.g:4988:4: otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) )
            {
            otherlv_2=(Token)match(input,34,FOLLOW_32); 

            				newLeafNode(otherlv_2, grammarAccess.getXStreamVarValueExpTypeAccess().getLeftSquareBracketKeyword_2_0());
            			
            // InternalXScript.g:4992:4: ( (lv_expressionValue_3_0= ruleXExpressionType ) )
            // InternalXScript.g:4993:5: (lv_expressionValue_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:4993:5: (lv_expressionValue_3_0= ruleXExpressionType )
            // InternalXScript.g:4994:6: lv_expressionValue_3_0= ruleXExpressionType
            {

            						newCompositeNode(grammarAccess.getXStreamVarValueExpTypeAccess().getExpressionValueXExpressionTypeParserRuleCall_2_1_0());
            					
            pushFollow(FOLLOW_20);
            lv_expressionValue_3_0=ruleXExpressionType();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getXStreamVarValueExpTypeRule());
            						}
            						set(
            							current,
            							"expressionValue",
            							lv_expressionValue_3_0,
            							"com.dunkware.xstream.XScript.XExpressionType");
            						afterParserOrEnumRuleCall();
            					

            }


            }


            }

            otherlv_4=(Token)match(input,35,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getXStreamVarValueExpTypeAccess().getRightSquareBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXStreamVarValueExpType"


    // $ANTLR start "entryRuleXFunctionCallExpType"
    // InternalXScript.g:5020:1: entryRuleXFunctionCallExpType returns [EObject current=null] : iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF ;
    public final EObject entryRuleXFunctionCallExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCallExpType = null;


        try {
            // InternalXScript.g:5020:61: (iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF )
            // InternalXScript.g:5021:2: iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF
            {
             newCompositeNode(grammarAccess.getXFunctionCallExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXFunctionCallExpType=ruleXFunctionCallExpType();

            state._fsp--;

             current =iv_ruleXFunctionCallExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXFunctionCallExpType"


    // $ANTLR start "ruleXFunctionCallExpType"
    // InternalXScript.g:5027:1: ruleXFunctionCallExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' ) ;
    public final EObject ruleXFunctionCallExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_parms_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalXScript.g:5033:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' ) )
            // InternalXScript.g:5034:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' )
            {
            // InternalXScript.g:5034:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' )
            // InternalXScript.g:5035:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')'
            {
            // InternalXScript.g:5035:3: ()
            // InternalXScript.g:5036:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionCallExpTypeAccess().getXFunctionCallExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5042:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:5043:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:5043:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:5044:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionCallExpTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(otherlv_1, grammarAccess.getXFunctionCallExpTypeAccess().getFunctionXFunctionTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_36); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionCallExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5059:3: ( (lv_parms_3_0= RULE_STRING ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==RULE_STRING) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // InternalXScript.g:5060:4: (lv_parms_3_0= RULE_STRING )
                    {
                    // InternalXScript.g:5060:4: (lv_parms_3_0= RULE_STRING )
                    // InternalXScript.g:5061:5: lv_parms_3_0= RULE_STRING
                    {
                    lv_parms_3_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

                    					newLeafNode(lv_parms_3_0, grammarAccess.getXFunctionCallExpTypeAccess().getParmsSTRINGTerminalRuleCall_3_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getXFunctionCallExpTypeRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"parms",
                    						lv_parms_3_0,
                    						"org.eclipse.xtext.common.Terminals.STRING");
                    				

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getXFunctionCallExpTypeAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXFunctionCallExpType"


    // $ANTLR start "entryRuleXVarStreakType"
    // InternalXScript.g:5085:1: entryRuleXVarStreakType returns [EObject current=null] : iv_ruleXVarStreakType= ruleXVarStreakType EOF ;
    public final EObject entryRuleXVarStreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakType = null;


        try {
            // InternalXScript.g:5085:55: (iv_ruleXVarStreakType= ruleXVarStreakType EOF )
            // InternalXScript.g:5086:2: iv_ruleXVarStreakType= ruleXVarStreakType EOF
            {
             newCompositeNode(grammarAccess.getXVarStreakTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarStreakType=ruleXVarStreakType();

            state._fsp--;

             current =iv_ruleXVarStreakType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarStreakType"


    // $ANTLR start "ruleXVarStreakType"
    // InternalXScript.g:5092:1: ruleXVarStreakType returns [EObject current=null] : ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' ) ;
    public final EObject ruleXVarStreakType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_direction_5_1=null;
        Token lv_direction_5_2=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_11=null;
        EObject lv_startIndexExp_7_0 = null;

        EObject lv_compare_9_0 = null;

        EObject lv_value_10_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:5098:2: ( ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' ) )
            // InternalXScript.g:5099:2: ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' )
            {
            // InternalXScript.g:5099:2: ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' )
            // InternalXScript.g:5100:3: () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')'
            {
            // InternalXScript.g:5100:3: ()
            // InternalXScript.g:5101:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakTypeAccess().getXVarStreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,64,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarStreakTypeAccess().getColumnStrkKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarStreakTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5115:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5116:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5116:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5117:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarStreakTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXVarStreakTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_43); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5132:3: ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) )
            // InternalXScript.g:5133:4: ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) )
            {
            // InternalXScript.g:5133:4: ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) )
            // InternalXScript.g:5134:5: (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' )
            {
            // InternalXScript.g:5134:5: (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' )
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==65) ) {
                alt52=1;
            }
            else if ( (LA52_0==66) ) {
                alt52=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 52, 0, input);

                throw nvae;
            }
            switch (alt52) {
                case 1 :
                    // InternalXScript.g:5135:6: lv_direction_5_1= 'bwd'
                    {
                    lv_direction_5_1=(Token)match(input,65,FOLLOW_7); 

                    						newLeafNode(lv_direction_5_1, grammarAccess.getXVarStreakTypeAccess().getDirectionBwdKeyword_5_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "direction", lv_direction_5_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5146:6: lv_direction_5_2= 'fwd'
                    {
                    lv_direction_5_2=(Token)match(input,66,FOLLOW_7); 

                    						newLeafNode(lv_direction_5_2, grammarAccess.getXVarStreakTypeAccess().getDirectionFwdKeyword_5_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "direction", lv_direction_5_2, null);
                    					

                    }
                    break;

            }


            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:5163:3: ( (lv_startIndexExp_7_0= ruleXExpressionType ) )
            // InternalXScript.g:5164:4: (lv_startIndexExp_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:5164:4: (lv_startIndexExp_7_0= ruleXExpressionType )
            // InternalXScript.g:5165:5: lv_startIndexExp_7_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXVarStreakTypeAccess().getStartIndexExpXExpressionTypeParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_7);
            lv_startIndexExp_7_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXVarStreakTypeRule());
            					}
            					set(
            						current,
            						"startIndexExp",
            						lv_startIndexExp_7_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,14,FOLLOW_44); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:5186:3: ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( ((LA53_0>=67 && LA53_0<=68)) ) {
                alt53=1;
            }
            else if ( (LA53_0==69) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // InternalXScript.g:5187:4: ( (lv_compare_9_0= ruleXVarStreakCompareType ) )
                    {
                    // InternalXScript.g:5187:4: ( (lv_compare_9_0= ruleXVarStreakCompareType ) )
                    // InternalXScript.g:5188:5: (lv_compare_9_0= ruleXVarStreakCompareType )
                    {
                    // InternalXScript.g:5188:5: (lv_compare_9_0= ruleXVarStreakCompareType )
                    // InternalXScript.g:5189:6: lv_compare_9_0= ruleXVarStreakCompareType
                    {

                    						newCompositeNode(grammarAccess.getXVarStreakTypeAccess().getCompareXVarStreakCompareTypeParserRuleCall_9_0_0());
                    					
                    pushFollow(FOLLOW_9);
                    lv_compare_9_0=ruleXVarStreakCompareType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getXVarStreakTypeRule());
                    						}
                    						set(
                    							current,
                    							"compare",
                    							lv_compare_9_0,
                    							"com.dunkware.xstream.XScript.XVarStreakCompareType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:5207:4: ( (lv_value_10_0= ruleXVarStreakValueType ) )
                    {
                    // InternalXScript.g:5207:4: ( (lv_value_10_0= ruleXVarStreakValueType ) )
                    // InternalXScript.g:5208:5: (lv_value_10_0= ruleXVarStreakValueType )
                    {
                    // InternalXScript.g:5208:5: (lv_value_10_0= ruleXVarStreakValueType )
                    // InternalXScript.g:5209:6: lv_value_10_0= ruleXVarStreakValueType
                    {

                    						newCompositeNode(grammarAccess.getXVarStreakTypeAccess().getValueXVarStreakValueTypeParserRuleCall_9_1_0());
                    					
                    pushFollow(FOLLOW_9);
                    lv_value_10_0=ruleXVarStreakValueType();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getXVarStreakTypeRule());
                    						}
                    						set(
                    							current,
                    							"value",
                    							lv_value_10_0,
                    							"com.dunkware.xstream.XScript.XVarStreakValueType");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_11=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getXVarStreakTypeAccess().getRightParenthesisKeyword_10());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarStreakType"


    // $ANTLR start "entryRuleXVarStreakCompareType"
    // InternalXScript.g:5235:1: entryRuleXVarStreakCompareType returns [EObject current=null] : iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF ;
    public final EObject entryRuleXVarStreakCompareType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakCompareType = null;


        try {
            // InternalXScript.g:5235:62: (iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF )
            // InternalXScript.g:5236:2: iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF
            {
             newCompositeNode(grammarAccess.getXVarStreakCompareTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarStreakCompareType=ruleXVarStreakCompareType();

            state._fsp--;

             current =iv_ruleXVarStreakCompareType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarStreakCompareType"


    // $ANTLR start "ruleXVarStreakCompareType"
    // InternalXScript.g:5242:1: ruleXVarStreakCompareType returns [EObject current=null] : ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' ) ;
    public final EObject ruleXVarStreakCompareType() throws RecognitionException {
        EObject current = null;

        Token lv_function_1_1=null;
        Token lv_function_1_2=null;
        Token otherlv_2=null;
        Token lv_offset_3_0=null;
        Token otherlv_4=null;
        Token lv_op_5_1=null;
        Token lv_op_5_2=null;
        Token lv_op_5_3=null;
        Token lv_test_6_0=null;
        Token otherlv_7=null;


        	enterRule();

        try {
            // InternalXScript.g:5248:2: ( ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' ) )
            // InternalXScript.g:5249:2: ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' )
            {
            // InternalXScript.g:5249:2: ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' )
            // InternalXScript.g:5250:3: () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')'
            {
            // InternalXScript.g:5250:3: ()
            // InternalXScript.g:5251:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakCompareTypeAccess().getXVarStreakCompareTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5257:3: ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) )
            // InternalXScript.g:5258:4: ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) )
            {
            // InternalXScript.g:5258:4: ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) )
            // InternalXScript.g:5259:5: (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' )
            {
            // InternalXScript.g:5259:5: (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==67) ) {
                alt54=1;
            }
            else if ( (LA54_0==68) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // InternalXScript.g:5260:6: lv_function_1_1= 'sum'
                    {
                    lv_function_1_1=(Token)match(input,67,FOLLOW_5); 

                    						newLeafNode(lv_function_1_1, grammarAccess.getXVarStreakCompareTypeAccess().getFunctionSumKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5271:6: lv_function_1_2= 'diff'
                    {
                    lv_function_1_2=(Token)match(input,68,FOLLOW_5); 

                    						newLeafNode(lv_function_1_2, grammarAccess.getXVarStreakCompareTypeAccess().getFunctionDiffKeyword_1_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_1_2, null);
                    					

                    }
                    break;

            }


            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarStreakCompareTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5288:3: ( (lv_offset_3_0= RULE_INT ) )
            // InternalXScript.g:5289:4: (lv_offset_3_0= RULE_INT )
            {
            // InternalXScript.g:5289:4: (lv_offset_3_0= RULE_INT )
            // InternalXScript.g:5290:5: lv_offset_3_0= RULE_INT
            {
            lv_offset_3_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            					newLeafNode(lv_offset_3_0, grammarAccess.getXVarStreakCompareTypeAccess().getOffsetINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"offset",
            						lv_offset_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_26); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarStreakCompareTypeAccess().getRightParenthesisKeyword_4());
            		
            // InternalXScript.g:5310:3: ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) )
            // InternalXScript.g:5311:4: ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) )
            {
            // InternalXScript.g:5311:4: ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) )
            // InternalXScript.g:5312:5: (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' )
            {
            // InternalXScript.g:5312:5: (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' )
            int alt55=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt55=1;
                }
                break;
            case 24:
                {
                alt55=2;
                }
                break;
            case 16:
                {
                alt55=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }

            switch (alt55) {
                case 1 :
                    // InternalXScript.g:5313:6: lv_op_5_1= '<'
                    {
                    lv_op_5_1=(Token)match(input,25,FOLLOW_45); 

                    						newLeafNode(lv_op_5_1, grammarAccess.getXVarStreakCompareTypeAccess().getOpLessThanSignKeyword_5_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_5_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5324:6: lv_op_5_2= '>'
                    {
                    lv_op_5_2=(Token)match(input,24,FOLLOW_45); 

                    						newLeafNode(lv_op_5_2, grammarAccess.getXVarStreakCompareTypeAccess().getOpGreaterThanSignKeyword_5_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_5_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5335:6: lv_op_5_3= '='
                    {
                    lv_op_5_3=(Token)match(input,16,FOLLOW_45); 

                    						newLeafNode(lv_op_5_3, grammarAccess.getXVarStreakCompareTypeAccess().getOpEqualsSignKeyword_5_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_5_3, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalXScript.g:5348:3: ( (lv_test_6_0= RULE_DOUBLE ) )
            // InternalXScript.g:5349:4: (lv_test_6_0= RULE_DOUBLE )
            {
            // InternalXScript.g:5349:4: (lv_test_6_0= RULE_DOUBLE )
            // InternalXScript.g:5350:5: lv_test_6_0= RULE_DOUBLE
            {
            lv_test_6_0=(Token)match(input,RULE_DOUBLE,FOLLOW_9); 

            					newLeafNode(lv_test_6_0, grammarAccess.getXVarStreakCompareTypeAccess().getTestDOUBLETerminalRuleCall_6_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"test",
            						lv_test_6_0,
            						"com.dunkware.xstream.XScript.DOUBLE");
            				

            }


            }

            otherlv_7=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getXVarStreakCompareTypeAccess().getRightParenthesisKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarStreakCompareType"


    // $ANTLR start "entryRuleXVarStreakValueType"
    // InternalXScript.g:5374:1: entryRuleXVarStreakValueType returns [EObject current=null] : iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF ;
    public final EObject entryRuleXVarStreakValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakValueType = null;


        try {
            // InternalXScript.g:5374:60: (iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF )
            // InternalXScript.g:5375:2: iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF
            {
             newCompositeNode(grammarAccess.getXVarStreakValueTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarStreakValueType=ruleXVarStreakValueType();

            state._fsp--;

             current =iv_ruleXVarStreakValueType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarStreakValueType"


    // $ANTLR start "ruleXVarStreakValueType"
    // InternalXScript.g:5381:1: ruleXVarStreakValueType returns [EObject current=null] : ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleXVarStreakValueType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        Token lv_test_3_0=null;


        	enterRule();

        try {
            // InternalXScript.g:5387:2: ( ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) ) )
            // InternalXScript.g:5388:2: ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) )
            {
            // InternalXScript.g:5388:2: ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) )
            // InternalXScript.g:5389:3: () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) )
            {
            // InternalXScript.g:5389:3: ()
            // InternalXScript.g:5390:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakValueTypeAccess().getXVarStreakValueTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,69,FOLLOW_26); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarStreakValueTypeAccess().getValueKeyword_1());
            		
            // InternalXScript.g:5400:3: ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) )
            // InternalXScript.g:5401:4: ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) )
            {
            // InternalXScript.g:5401:4: ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) )
            // InternalXScript.g:5402:5: (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' )
            {
            // InternalXScript.g:5402:5: (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' )
            int alt56=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt56=1;
                }
                break;
            case 24:
                {
                alt56=2;
                }
                break;
            case 16:
                {
                alt56=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 56, 0, input);

                throw nvae;
            }

            switch (alt56) {
                case 1 :
                    // InternalXScript.g:5403:6: lv_op_2_1= '<'
                    {
                    lv_op_2_1=(Token)match(input,25,FOLLOW_45); 

                    						newLeafNode(lv_op_2_1, grammarAccess.getXVarStreakValueTypeAccess().getOpLessThanSignKeyword_2_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakValueTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_2_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5414:6: lv_op_2_2= '>'
                    {
                    lv_op_2_2=(Token)match(input,24,FOLLOW_45); 

                    						newLeafNode(lv_op_2_2, grammarAccess.getXVarStreakValueTypeAccess().getOpGreaterThanSignKeyword_2_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakValueTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_2_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5425:6: lv_op_2_3= '='
                    {
                    lv_op_2_3=(Token)match(input,16,FOLLOW_45); 

                    						newLeafNode(lv_op_2_3, grammarAccess.getXVarStreakValueTypeAccess().getOpEqualsSignKeyword_2_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakValueTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_2_3, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalXScript.g:5438:3: ( (lv_test_3_0= RULE_DOUBLE ) )
            // InternalXScript.g:5439:4: (lv_test_3_0= RULE_DOUBLE )
            {
            // InternalXScript.g:5439:4: (lv_test_3_0= RULE_DOUBLE )
            // InternalXScript.g:5440:5: lv_test_3_0= RULE_DOUBLE
            {
            lv_test_3_0=(Token)match(input,RULE_DOUBLE,FOLLOW_2); 

            					newLeafNode(lv_test_3_0, grammarAccess.getXVarStreakValueTypeAccess().getTestDOUBLETerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarStreakValueTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"test",
            						lv_test_3_0,
            						"com.dunkware.xstream.XScript.DOUBLE");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarStreakValueType"


    // $ANTLR start "entryRuleXVarCompareStreakType"
    // InternalXScript.g:5460:1: entryRuleXVarCompareStreakType returns [EObject current=null] : iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF ;
    public final EObject entryRuleXVarCompareStreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarCompareStreakType = null;


        try {
            // InternalXScript.g:5460:62: (iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF )
            // InternalXScript.g:5461:2: iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF
            {
             newCompositeNode(grammarAccess.getXVarCompareStreakTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarCompareStreakType=ruleXVarCompareStreakType();

            state._fsp--;

             current =iv_ruleXVarCompareStreakType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarCompareStreakType"


    // $ANTLR start "ruleXVarCompareStreakType"
    // InternalXScript.g:5467:1: ruleXVarCompareStreakType returns [EObject current=null] : ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' ) ;
    public final EObject ruleXVarCompareStreakType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_direction_7_1=null;
        Token lv_direction_7_2=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token lv_function_11_1=null;
        Token lv_function_11_2=null;
        Token lv_function_11_3=null;
        Token lv_function_11_4=null;
        Token lv_op_12_1=null;
        Token lv_op_12_2=null;
        Token lv_op_12_3=null;
        Token lv_test_13_0=null;
        Token otherlv_14=null;
        EObject lv_startIndexExp_9_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:5473:2: ( ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' ) )
            // InternalXScript.g:5474:2: ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' )
            {
            // InternalXScript.g:5474:2: ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' )
            // InternalXScript.g:5475:3: () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')'
            {
            // InternalXScript.g:5475:3: ()
            // InternalXScript.g:5476:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarCompareStreakTypeAccess().getXVarCompareStreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,70,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarCompareStreakTypeAccess().getColumnPairStrkKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarCompareStreakTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5490:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5491:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5491:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5492:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXVarCompareStreakTypeAccess().getTargetVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5507:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:5508:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:5508:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:5509:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarCompareStreakTypeAccess().getCompareVarVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_43); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:5524:3: ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) )
            // InternalXScript.g:5525:4: ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) )
            {
            // InternalXScript.g:5525:4: ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) )
            // InternalXScript.g:5526:5: (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' )
            {
            // InternalXScript.g:5526:5: (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' )
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==65) ) {
                alt57=1;
            }
            else if ( (LA57_0==66) ) {
                alt57=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }
            switch (alt57) {
                case 1 :
                    // InternalXScript.g:5527:6: lv_direction_7_1= 'bwd'
                    {
                    lv_direction_7_1=(Token)match(input,65,FOLLOW_7); 

                    						newLeafNode(lv_direction_7_1, grammarAccess.getXVarCompareStreakTypeAccess().getDirectionBwdKeyword_7_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "direction", lv_direction_7_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5538:6: lv_direction_7_2= 'fwd'
                    {
                    lv_direction_7_2=(Token)match(input,66,FOLLOW_7); 

                    						newLeafNode(lv_direction_7_2, grammarAccess.getXVarCompareStreakTypeAccess().getDirectionFwdKeyword_7_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "direction", lv_direction_7_2, null);
                    					

                    }
                    break;

            }


            }


            }

            otherlv_8=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:5555:3: ( (lv_startIndexExp_9_0= ruleXExpressionType ) )
            // InternalXScript.g:5556:4: (lv_startIndexExp_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:5556:4: (lv_startIndexExp_9_0= ruleXExpressionType )
            // InternalXScript.g:5557:5: lv_startIndexExp_9_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXVarCompareStreakTypeAccess().getStartIndexExpXExpressionTypeParserRuleCall_9_0());
            				
            pushFollow(FOLLOW_7);
            lv_startIndexExp_9_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXVarCompareStreakTypeRule());
            					}
            					set(
            						current,
            						"startIndexExp",
            						lv_startIndexExp_9_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,14,FOLLOW_46); 

            			newLeafNode(otherlv_10, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_10());
            		
            // InternalXScript.g:5578:3: ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) )
            // InternalXScript.g:5579:4: ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) )
            {
            // InternalXScript.g:5579:4: ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) )
            // InternalXScript.g:5580:5: (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' )
            {
            // InternalXScript.g:5580:5: (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' )
            int alt58=4;
            switch ( input.LA(1) ) {
            case 67:
                {
                alt58=1;
                }
                break;
            case 68:
                {
                alt58=2;
                }
                break;
            case 69:
                {
                alt58=3;
                }
                break;
            case 71:
                {
                alt58=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // InternalXScript.g:5581:6: lv_function_11_1= 'sum'
                    {
                    lv_function_11_1=(Token)match(input,67,FOLLOW_26); 

                    						newLeafNode(lv_function_11_1, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionSumKeyword_11_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5592:6: lv_function_11_2= 'diff'
                    {
                    lv_function_11_2=(Token)match(input,68,FOLLOW_26); 

                    						newLeafNode(lv_function_11_2, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionDiffKeyword_11_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5603:6: lv_function_11_3= 'value'
                    {
                    lv_function_11_3=(Token)match(input,69,FOLLOW_26); 

                    						newLeafNode(lv_function_11_3, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionValueKeyword_11_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalXScript.g:5614:6: lv_function_11_4= 'variance'
                    {
                    lv_function_11_4=(Token)match(input,71,FOLLOW_26); 

                    						newLeafNode(lv_function_11_4, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionVarianceKeyword_11_0_3());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_4, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalXScript.g:5627:3: ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) )
            // InternalXScript.g:5628:4: ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) )
            {
            // InternalXScript.g:5628:4: ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) )
            // InternalXScript.g:5629:5: (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' )
            {
            // InternalXScript.g:5629:5: (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' )
            int alt59=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt59=1;
                }
                break;
            case 24:
                {
                alt59=2;
                }
                break;
            case 16:
                {
                alt59=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }

            switch (alt59) {
                case 1 :
                    // InternalXScript.g:5630:6: lv_op_12_1= '<'
                    {
                    lv_op_12_1=(Token)match(input,25,FOLLOW_45); 

                    						newLeafNode(lv_op_12_1, grammarAccess.getXVarCompareStreakTypeAccess().getOpLessThanSignKeyword_12_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_12_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5641:6: lv_op_12_2= '>'
                    {
                    lv_op_12_2=(Token)match(input,24,FOLLOW_45); 

                    						newLeafNode(lv_op_12_2, grammarAccess.getXVarCompareStreakTypeAccess().getOpGreaterThanSignKeyword_12_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_12_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5652:6: lv_op_12_3= '='
                    {
                    lv_op_12_3=(Token)match(input,16,FOLLOW_45); 

                    						newLeafNode(lv_op_12_3, grammarAccess.getXVarCompareStreakTypeAccess().getOpEqualsSignKeyword_12_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_12_3, null);
                    					

                    }
                    break;

            }


            }


            }

            // InternalXScript.g:5665:3: ( (lv_test_13_0= RULE_DOUBLE ) )
            // InternalXScript.g:5666:4: (lv_test_13_0= RULE_DOUBLE )
            {
            // InternalXScript.g:5666:4: (lv_test_13_0= RULE_DOUBLE )
            // InternalXScript.g:5667:5: lv_test_13_0= RULE_DOUBLE
            {
            lv_test_13_0=(Token)match(input,RULE_DOUBLE,FOLLOW_9); 

            					newLeafNode(lv_test_13_0, grammarAccess.getXVarCompareStreakTypeAccess().getTestDOUBLETerminalRuleCall_13_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"test",
            						lv_test_13_0,
            						"com.dunkware.xstream.XScript.DOUBLE");
            				

            }


            }

            otherlv_14=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_14, grammarAccess.getXVarCompareStreakTypeAccess().getRightParenthesisKeyword_14());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarCompareStreakType"


    // $ANTLR start "entryRuleXSlrAvgExpType"
    // InternalXScript.g:5691:1: entryRuleXSlrAvgExpType returns [EObject current=null] : iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF ;
    public final EObject entryRuleXSlrAvgExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSlrAvgExpType = null;


        try {
            // InternalXScript.g:5691:55: (iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF )
            // InternalXScript.g:5692:2: iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF
            {
             newCompositeNode(grammarAccess.getXSlrAvgExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXSlrAvgExpType=ruleXSlrAvgExpType();

            state._fsp--;

             current =iv_ruleXSlrAvgExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXSlrAvgExpType"


    // $ANTLR start "ruleXSlrAvgExpType"
    // InternalXScript.g:5698:1: ruleXSlrAvgExpType returns [EObject current=null] : ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' ) ;
    public final EObject ruleXSlrAvgExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_startValue_5_0 = null;

        EObject lv_endValue_7_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:5704:2: ( ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' ) )
            // InternalXScript.g:5705:2: ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:5705:2: ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' )
            // InternalXScript.g:5706:3: () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')'
            {
            // InternalXScript.g:5706:3: ()
            // InternalXScript.g:5707:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSlrAvgExpTypeAccess().getXSlrAvgExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,72,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSlrAvgExpTypeAccess().getSlrAvgKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSlrAvgExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5721:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5722:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5722:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5723:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSlrAvgExpTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXSlrAvgExpTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_4, grammarAccess.getXSlrAvgExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5738:3: ( (lv_startValue_5_0= ruleXExpressionType ) )
            // InternalXScript.g:5739:4: (lv_startValue_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:5739:4: (lv_startValue_5_0= ruleXExpressionType )
            // InternalXScript.g:5740:5: lv_startValue_5_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXSlrAvgExpTypeAccess().getStartValueXExpressionTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_7);
            lv_startValue_5_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXSlrAvgExpTypeRule());
            					}
            					set(
            						current,
            						"startValue",
            						lv_startValue_5_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_6, grammarAccess.getXSlrAvgExpTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:5761:3: ( (lv_endValue_7_0= ruleXExpressionType ) )
            // InternalXScript.g:5762:4: (lv_endValue_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:5762:4: (lv_endValue_7_0= ruleXExpressionType )
            // InternalXScript.g:5763:5: lv_endValue_7_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXSlrAvgExpTypeAccess().getEndValueXExpressionTypeParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_9);
            lv_endValue_7_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXSlrAvgExpTypeRule());
            					}
            					set(
            						current,
            						"endValue",
            						lv_endValue_7_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getXSlrAvgExpTypeAccess().getRightParenthesisKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXSlrAvgExpType"


    // $ANTLR start "entryRuleXLastSignalTriggerType"
    // InternalXScript.g:5788:1: entryRuleXLastSignalTriggerType returns [EObject current=null] : iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF ;
    public final EObject entryRuleXLastSignalTriggerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXLastSignalTriggerType = null;


        try {
            // InternalXScript.g:5788:63: (iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF )
            // InternalXScript.g:5789:2: iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF
            {
             newCompositeNode(grammarAccess.getXLastSignalTriggerTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXLastSignalTriggerType=ruleXLastSignalTriggerType();

            state._fsp--;

             current =iv_ruleXLastSignalTriggerType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXLastSignalTriggerType"


    // $ANTLR start "ruleXLastSignalTriggerType"
    // InternalXScript.g:5795:1: ruleXLastSignalTriggerType returns [EObject current=null] : ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' ) ;
    public final EObject ruleXLastSignalTriggerType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalXScript.g:5801:2: ( ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' ) )
            // InternalXScript.g:5802:2: ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:5802:2: ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' )
            // InternalXScript.g:5803:3: () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')'
            {
            // InternalXScript.g:5803:3: ()
            // InternalXScript.g:5804:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXLastSignalTriggerTypeAccess().getXLastSignalTriggerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,73,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXLastSignalTriggerTypeAccess().getLstKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXLastSignalTriggerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5818:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5819:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5819:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5820:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXLastSignalTriggerTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_9); 

            					newLeafNode(otherlv_3, grammarAccess.getXLastSignalTriggerTypeAccess().getSignalSignalTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getXLastSignalTriggerTypeAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXLastSignalTriggerType"


    // $ANTLR start "entryRuleXSignalTriggerCountType"
    // InternalXScript.g:5839:1: entryRuleXSignalTriggerCountType returns [EObject current=null] : iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF ;
    public final EObject entryRuleXSignalTriggerCountType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalTriggerCountType = null;


        try {
            // InternalXScript.g:5839:64: (iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF )
            // InternalXScript.g:5840:2: iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF
            {
             newCompositeNode(grammarAccess.getXSignalTriggerCountTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXSignalTriggerCountType=ruleXSignalTriggerCountType();

            state._fsp--;

             current =iv_ruleXSignalTriggerCountType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXSignalTriggerCountType"


    // $ANTLR start "ruleXSignalTriggerCountType"
    // InternalXScript.g:5846:1: ruleXSignalTriggerCountType returns [EObject current=null] : ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' ) ;
    public final EObject ruleXSignalTriggerCountType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_lookback_5_0=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalXScript.g:5852:2: ( ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' ) )
            // InternalXScript.g:5853:2: ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:5853:2: ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' )
            // InternalXScript.g:5854:3: () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')'
            {
            // InternalXScript.g:5854:3: ()
            // InternalXScript.g:5855:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalTriggerCountTypeAccess().getXSignalTriggerCountTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,74,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalTriggerCountTypeAccess().getStcKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalTriggerCountTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5869:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5870:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5870:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5871:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSignalTriggerCountTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXSignalTriggerCountTypeAccess().getSignalSignalTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getXSignalTriggerCountTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5886:3: ( (lv_lookback_5_0= RULE_INT ) )
            // InternalXScript.g:5887:4: (lv_lookback_5_0= RULE_INT )
            {
            // InternalXScript.g:5887:4: (lv_lookback_5_0= RULE_INT )
            // InternalXScript.g:5888:5: lv_lookback_5_0= RULE_INT
            {
            lv_lookback_5_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            					newLeafNode(lv_lookback_5_0, grammarAccess.getXSignalTriggerCountTypeAccess().getLookbackINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSignalTriggerCountTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"lookback",
            						lv_lookback_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getXSignalTriggerCountTypeAccess().getRightParenthesisKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXSignalTriggerCountType"


    // $ANTLR start "entryRuleXVarianceAverageType"
    // InternalXScript.g:5912:1: entryRuleXVarianceAverageType returns [EObject current=null] : iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF ;
    public final EObject entryRuleXVarianceAverageType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarianceAverageType = null;


        try {
            // InternalXScript.g:5912:61: (iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF )
            // InternalXScript.g:5913:2: iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF
            {
             newCompositeNode(grammarAccess.getXVarianceAverageTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarianceAverageType=ruleXVarianceAverageType();

            state._fsp--;

             current =iv_ruleXVarianceAverageType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarianceAverageType"


    // $ANTLR start "ruleXVarianceAverageType"
    // InternalXScript.g:5919:1: ruleXVarianceAverageType returns [EObject current=null] : ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) ;
    public final EObject ruleXVarianceAverageType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_start_7_0 = null;

        EObject lv_end_9_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:5925:2: ( ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) )
            // InternalXScript.g:5926:2: ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            {
            // InternalXScript.g:5926:2: ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            // InternalXScript.g:5927:3: () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')'
            {
            // InternalXScript.g:5927:3: ()
            // InternalXScript.g:5928:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarianceAverageTypeAccess().getXVarianceAverageTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,75,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarianceAverageTypeAccess().getVarAvgKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarianceAverageTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5942:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5943:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5943:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5944:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarianceAverageTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXVarianceAverageTypeAccess().getTargetVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarianceAverageTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5959:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:5960:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:5960:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:5961:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarianceAverageTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarianceAverageTypeAccess().getCompareVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarianceAverageTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:5976:3: ( (lv_start_7_0= ruleXExpressionType ) )
            // InternalXScript.g:5977:4: (lv_start_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:5977:4: (lv_start_7_0= ruleXExpressionType )
            // InternalXScript.g:5978:5: lv_start_7_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXVarianceAverageTypeAccess().getStartXExpressionTypeParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_7);
            lv_start_7_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXVarianceAverageTypeRule());
            					}
            					set(
            						current,
            						"start",
            						lv_start_7_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarianceAverageTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:5999:3: ( (lv_end_9_0= ruleXExpressionType ) )
            // InternalXScript.g:6000:4: (lv_end_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:6000:4: (lv_end_9_0= ruleXExpressionType )
            // InternalXScript.g:6001:5: lv_end_9_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXVarianceAverageTypeAccess().getEndXExpressionTypeParserRuleCall_9_0());
            				
            pushFollow(FOLLOW_9);
            lv_end_9_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXVarianceAverageTypeRule());
            					}
            					set(
            						current,
            						"end",
            						lv_end_9_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getXVarianceAverageTypeAccess().getRightParenthesisKeyword_10());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarianceAverageType"


    // $ANTLR start "entryRuleXVarianceMaxType"
    // InternalXScript.g:6026:1: entryRuleXVarianceMaxType returns [EObject current=null] : iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF ;
    public final EObject entryRuleXVarianceMaxType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarianceMaxType = null;


        try {
            // InternalXScript.g:6026:57: (iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF )
            // InternalXScript.g:6027:2: iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF
            {
             newCompositeNode(grammarAccess.getXVarianceMaxTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXVarianceMaxType=ruleXVarianceMaxType();

            state._fsp--;

             current =iv_ruleXVarianceMaxType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXVarianceMaxType"


    // $ANTLR start "ruleXVarianceMaxType"
    // InternalXScript.g:6033:1: ruleXVarianceMaxType returns [EObject current=null] : ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) ;
    public final EObject ruleXVarianceMaxType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        EObject lv_start_7_0 = null;

        EObject lv_end_9_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6039:2: ( ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) )
            // InternalXScript.g:6040:2: ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            {
            // InternalXScript.g:6040:2: ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            // InternalXScript.g:6041:3: () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')'
            {
            // InternalXScript.g:6041:3: ()
            // InternalXScript.g:6042:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarianceMaxTypeAccess().getXVarianceMaxTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,76,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarianceMaxTypeAccess().getVarMaxKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarianceMaxTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6056:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6057:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6057:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6058:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarianceMaxTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXVarianceMaxTypeAccess().getTargetVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_4); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarianceMaxTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6073:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:6074:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:6074:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:6075:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarianceMaxTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarianceMaxTypeAccess().getCompareVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarianceMaxTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6090:3: ( (lv_start_7_0= ruleXExpressionType ) )
            // InternalXScript.g:6091:4: (lv_start_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:6091:4: (lv_start_7_0= ruleXExpressionType )
            // InternalXScript.g:6092:5: lv_start_7_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXVarianceMaxTypeAccess().getStartXExpressionTypeParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_7);
            lv_start_7_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXVarianceMaxTypeRule());
            					}
            					set(
            						current,
            						"start",
            						lv_start_7_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarianceMaxTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:6113:3: ( (lv_end_9_0= ruleXExpressionType ) )
            // InternalXScript.g:6114:4: (lv_end_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:6114:4: (lv_end_9_0= ruleXExpressionType )
            // InternalXScript.g:6115:5: lv_end_9_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXVarianceMaxTypeAccess().getEndXExpressionTypeParserRuleCall_9_0());
            				
            pushFollow(FOLLOW_9);
            lv_end_9_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXVarianceMaxTypeRule());
            					}
            					set(
            						current,
            						"end",
            						lv_end_9_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_10=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getXVarianceMaxTypeAccess().getRightParenthesisKeyword_10());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXVarianceMaxType"


    // $ANTLR start "entryRuleXRocExpType"
    // InternalXScript.g:6140:1: entryRuleXRocExpType returns [EObject current=null] : iv_ruleXRocExpType= ruleXRocExpType EOF ;
    public final EObject entryRuleXRocExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXRocExpType = null;


        try {
            // InternalXScript.g:6140:52: (iv_ruleXRocExpType= ruleXRocExpType EOF )
            // InternalXScript.g:6141:2: iv_ruleXRocExpType= ruleXRocExpType EOF
            {
             newCompositeNode(grammarAccess.getXRocExpTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXRocExpType=ruleXRocExpType();

            state._fsp--;

             current =iv_ruleXRocExpType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXRocExpType"


    // $ANTLR start "ruleXRocExpType"
    // InternalXScript.g:6147:1: ruleXRocExpType returns [EObject current=null] : ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
    public final EObject ruleXRocExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_value1_3_0 = null;

        EObject lv_value2_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6153:2: ( ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:6154:2: ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:6154:2: ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:6155:3: () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:6155:3: ()
            // InternalXScript.g:6156:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXRocExpTypeAccess().getXRocExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,77,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXRocExpTypeAccess().getRoxKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_32); 

            			newLeafNode(otherlv_2, grammarAccess.getXRocExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6170:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:6171:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:6171:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:6172:5: lv_value1_3_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXRocExpTypeAccess().getValue1XExpressionTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_value1_3_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXRocExpTypeRule());
            					}
            					set(
            						current,
            						"value1",
            						lv_value1_3_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_32); 

            			newLeafNode(otherlv_4, grammarAccess.getXRocExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6193:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:6194:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:6194:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:6195:5: lv_value2_5_0= ruleXExpressionType
            {

            					newCompositeNode(grammarAccess.getXRocExpTypeAccess().getValue2XExpressionTypeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_value2_5_0=ruleXExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXRocExpTypeRule());
            					}
            					set(
            						current,
            						"value2",
            						lv_value2_5_0,
            						"com.dunkware.xstream.XScript.XExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getXRocExpTypeAccess().getRightParenthesisKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXRocExpType"


    // $ANTLR start "entryRuleXTimeRange"
    // InternalXScript.g:6220:1: entryRuleXTimeRange returns [EObject current=null] : iv_ruleXTimeRange= ruleXTimeRange EOF ;
    public final EObject entryRuleXTimeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXTimeRange = null;


        try {
            // InternalXScript.g:6220:51: (iv_ruleXTimeRange= ruleXTimeRange EOF )
            // InternalXScript.g:6221:2: iv_ruleXTimeRange= ruleXTimeRange EOF
            {
             newCompositeNode(grammarAccess.getXTimeRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXTimeRange=ruleXTimeRange();

            state._fsp--;

             current =iv_ruleXTimeRange; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXTimeRange"


    // $ANTLR start "ruleXTimeRange"
    // InternalXScript.g:6227:1: ruleXTimeRange returns [EObject current=null] : this_XTimeRangeRelative_0= ruleXTimeRangeRelative ;
    public final EObject ruleXTimeRange() throws RecognitionException {
        EObject current = null;

        EObject this_XTimeRangeRelative_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6233:2: (this_XTimeRangeRelative_0= ruleXTimeRangeRelative )
            // InternalXScript.g:6234:2: this_XTimeRangeRelative_0= ruleXTimeRangeRelative
            {

            		newCompositeNode(grammarAccess.getXTimeRangeAccess().getXTimeRangeRelativeParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_XTimeRangeRelative_0=ruleXTimeRangeRelative();

            state._fsp--;


            		current = this_XTimeRangeRelative_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXTimeRange"


    // $ANTLR start "entryRuleXTimeRangeRelative"
    // InternalXScript.g:6245:1: entryRuleXTimeRangeRelative returns [EObject current=null] : iv_ruleXTimeRangeRelative= ruleXTimeRangeRelative EOF ;
    public final EObject entryRuleXTimeRangeRelative() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXTimeRangeRelative = null;


        try {
            // InternalXScript.g:6245:59: (iv_ruleXTimeRangeRelative= ruleXTimeRangeRelative EOF )
            // InternalXScript.g:6246:2: iv_ruleXTimeRangeRelative= ruleXTimeRangeRelative EOF
            {
             newCompositeNode(grammarAccess.getXTimeRangeRelativeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXTimeRangeRelative=ruleXTimeRangeRelative();

            state._fsp--;

             current =iv_ruleXTimeRangeRelative; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXTimeRangeRelative"


    // $ANTLR start "ruleXTimeRangeRelative"
    // InternalXScript.g:6252:1: ruleXTimeRangeRelative returns [EObject current=null] : ( () otherlv_1= 'relative' otherlv_2= '(' ( (lv_unit_3_0= ruleStreamTimeUnit ) ) otherlv_4= ',' ( (lv_value_5_0= RULE_INT ) ) otherlv_6= ')' ) ;
    public final EObject ruleXTimeRangeRelative() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_value_5_0=null;
        Token otherlv_6=null;
        Enumerator lv_unit_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6258:2: ( ( () otherlv_1= 'relative' otherlv_2= '(' ( (lv_unit_3_0= ruleStreamTimeUnit ) ) otherlv_4= ',' ( (lv_value_5_0= RULE_INT ) ) otherlv_6= ')' ) )
            // InternalXScript.g:6259:2: ( () otherlv_1= 'relative' otherlv_2= '(' ( (lv_unit_3_0= ruleStreamTimeUnit ) ) otherlv_4= ',' ( (lv_value_5_0= RULE_INT ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:6259:2: ( () otherlv_1= 'relative' otherlv_2= '(' ( (lv_unit_3_0= ruleStreamTimeUnit ) ) otherlv_4= ',' ( (lv_value_5_0= RULE_INT ) ) otherlv_6= ')' )
            // InternalXScript.g:6260:3: () otherlv_1= 'relative' otherlv_2= '(' ( (lv_unit_3_0= ruleStreamTimeUnit ) ) otherlv_4= ',' ( (lv_value_5_0= RULE_INT ) ) otherlv_6= ')'
            {
            // InternalXScript.g:6260:3: ()
            // InternalXScript.g:6261:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXTimeRangeRelativeAccess().getXTimeRangeRelativeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,78,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXTimeRangeRelativeAccess().getRelativeKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_24); 

            			newLeafNode(otherlv_2, grammarAccess.getXTimeRangeRelativeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6275:3: ( (lv_unit_3_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:6276:4: (lv_unit_3_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:6276:4: (lv_unit_3_0= ruleStreamTimeUnit )
            // InternalXScript.g:6277:5: lv_unit_3_0= ruleStreamTimeUnit
            {

            					newCompositeNode(grammarAccess.getXTimeRangeRelativeAccess().getUnitStreamTimeUnitEnumRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_unit_3_0=ruleStreamTimeUnit();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXTimeRangeRelativeRule());
            					}
            					set(
            						current,
            						"unit",
            						lv_unit_3_0,
            						"com.dunkware.xstream.XScript.StreamTimeUnit");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getXTimeRangeRelativeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6298:3: ( (lv_value_5_0= RULE_INT ) )
            // InternalXScript.g:6299:4: (lv_value_5_0= RULE_INT )
            {
            // InternalXScript.g:6299:4: (lv_value_5_0= RULE_INT )
            // InternalXScript.g:6300:5: lv_value_5_0= RULE_INT
            {
            lv_value_5_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            					newLeafNode(lv_value_5_0, grammarAccess.getXTimeRangeRelativeAccess().getValueINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXTimeRangeRelativeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getXTimeRangeRelativeAccess().getRightParenthesisKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXTimeRangeRelative"


    // $ANTLR start "entryRuleXValueType"
    // InternalXScript.g:6324:1: entryRuleXValueType returns [EObject current=null] : iv_ruleXValueType= ruleXValueType EOF ;
    public final EObject entryRuleXValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXValueType = null;


        try {
            // InternalXScript.g:6324:51: (iv_ruleXValueType= ruleXValueType EOF )
            // InternalXScript.g:6325:2: iv_ruleXValueType= ruleXValueType EOF
            {
             newCompositeNode(grammarAccess.getXValueTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXValueType=ruleXValueType();

            state._fsp--;

             current =iv_ruleXValueType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXValueType"


    // $ANTLR start "ruleXValueType"
    // InternalXScript.g:6331:1: ruleXValueType returns [EObject current=null] : (this_XValueVarCurrentType_0= ruleXValueVarCurrentType | this_XValueVarSessionAggType_1= ruleXValueVarSessionAggType | this_XValueSignalSessionCountType_2= ruleXValueSignalSessionCountType | this_XValueSignalHistoricalCountType_3= ruleXValueSignalHistoricalCountType ) ;
    public final EObject ruleXValueType() throws RecognitionException {
        EObject current = null;

        EObject this_XValueVarCurrentType_0 = null;

        EObject this_XValueVarSessionAggType_1 = null;

        EObject this_XValueSignalSessionCountType_2 = null;

        EObject this_XValueSignalHistoricalCountType_3 = null;



        	enterRule();

        try {
            // InternalXScript.g:6337:2: ( (this_XValueVarCurrentType_0= ruleXValueVarCurrentType | this_XValueVarSessionAggType_1= ruleXValueVarSessionAggType | this_XValueSignalSessionCountType_2= ruleXValueSignalSessionCountType | this_XValueSignalHistoricalCountType_3= ruleXValueSignalHistoricalCountType ) )
            // InternalXScript.g:6338:2: (this_XValueVarCurrentType_0= ruleXValueVarCurrentType | this_XValueVarSessionAggType_1= ruleXValueVarSessionAggType | this_XValueSignalSessionCountType_2= ruleXValueSignalSessionCountType | this_XValueSignalHistoricalCountType_3= ruleXValueSignalHistoricalCountType )
            {
            // InternalXScript.g:6338:2: (this_XValueVarCurrentType_0= ruleXValueVarCurrentType | this_XValueVarSessionAggType_1= ruleXValueVarSessionAggType | this_XValueSignalSessionCountType_2= ruleXValueSignalSessionCountType | this_XValueSignalHistoricalCountType_3= ruleXValueSignalHistoricalCountType )
            int alt60=4;
            switch ( input.LA(1) ) {
            case 79:
                {
                alt60=1;
                }
                break;
            case 80:
                {
                alt60=2;
                }
                break;
            case 81:
                {
                alt60=3;
                }
                break;
            case 82:
                {
                alt60=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }

            switch (alt60) {
                case 1 :
                    // InternalXScript.g:6339:3: this_XValueVarCurrentType_0= ruleXValueVarCurrentType
                    {

                    			newCompositeNode(grammarAccess.getXValueTypeAccess().getXValueVarCurrentTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_XValueVarCurrentType_0=ruleXValueVarCurrentType();

                    state._fsp--;


                    			current = this_XValueVarCurrentType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalXScript.g:6348:3: this_XValueVarSessionAggType_1= ruleXValueVarSessionAggType
                    {

                    			newCompositeNode(grammarAccess.getXValueTypeAccess().getXValueVarSessionAggTypeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_XValueVarSessionAggType_1=ruleXValueVarSessionAggType();

                    state._fsp--;


                    			current = this_XValueVarSessionAggType_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalXScript.g:6357:3: this_XValueSignalSessionCountType_2= ruleXValueSignalSessionCountType
                    {

                    			newCompositeNode(grammarAccess.getXValueTypeAccess().getXValueSignalSessionCountTypeParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_XValueSignalSessionCountType_2=ruleXValueSignalSessionCountType();

                    state._fsp--;


                    			current = this_XValueSignalSessionCountType_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalXScript.g:6366:3: this_XValueSignalHistoricalCountType_3= ruleXValueSignalHistoricalCountType
                    {

                    			newCompositeNode(grammarAccess.getXValueTypeAccess().getXValueSignalHistoricalCountTypeParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_XValueSignalHistoricalCountType_3=ruleXValueSignalHistoricalCountType();

                    state._fsp--;


                    			current = this_XValueSignalHistoricalCountType_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXValueType"


    // $ANTLR start "entryRuleXValueVarCurrentType"
    // InternalXScript.g:6378:1: entryRuleXValueVarCurrentType returns [EObject current=null] : iv_ruleXValueVarCurrentType= ruleXValueVarCurrentType EOF ;
    public final EObject entryRuleXValueVarCurrentType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXValueVarCurrentType = null;


        try {
            // InternalXScript.g:6378:61: (iv_ruleXValueVarCurrentType= ruleXValueVarCurrentType EOF )
            // InternalXScript.g:6379:2: iv_ruleXValueVarCurrentType= ruleXValueVarCurrentType EOF
            {
             newCompositeNode(grammarAccess.getXValueVarCurrentTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXValueVarCurrentType=ruleXValueVarCurrentType();

            state._fsp--;

             current =iv_ruleXValueVarCurrentType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXValueVarCurrentType"


    // $ANTLR start "ruleXValueVarCurrentType"
    // InternalXScript.g:6385:1: ruleXValueVarCurrentType returns [EObject current=null] : ( () otherlv_1= 'sessionVarValue' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_index_5_0= RULE_INT ) ) otherlv_6= ')' ) ;
    public final EObject ruleXValueVarCurrentType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_index_5_0=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalXScript.g:6391:2: ( ( () otherlv_1= 'sessionVarValue' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_index_5_0= RULE_INT ) ) otherlv_6= ')' ) )
            // InternalXScript.g:6392:2: ( () otherlv_1= 'sessionVarValue' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_index_5_0= RULE_INT ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:6392:2: ( () otherlv_1= 'sessionVarValue' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_index_5_0= RULE_INT ) ) otherlv_6= ')' )
            // InternalXScript.g:6393:3: () otherlv_1= 'sessionVarValue' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_index_5_0= RULE_INT ) ) otherlv_6= ')'
            {
            // InternalXScript.g:6393:3: ()
            // InternalXScript.g:6394:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXValueVarCurrentTypeAccess().getXValueVarCurrentTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,79,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXValueVarCurrentTypeAccess().getSessionVarValueKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXValueVarCurrentTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6408:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6409:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6409:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6410:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXValueVarCurrentTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXValueVarCurrentTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getXValueVarCurrentTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6425:3: ( (lv_index_5_0= RULE_INT ) )
            // InternalXScript.g:6426:4: (lv_index_5_0= RULE_INT )
            {
            // InternalXScript.g:6426:4: (lv_index_5_0= RULE_INT )
            // InternalXScript.g:6427:5: lv_index_5_0= RULE_INT
            {
            lv_index_5_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            					newLeafNode(lv_index_5_0, grammarAccess.getXValueVarCurrentTypeAccess().getIndexINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXValueVarCurrentTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"index",
            						lv_index_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getXValueVarCurrentTypeAccess().getRightParenthesisKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXValueVarCurrentType"


    // $ANTLR start "entryRuleXValueVarSessionAggType"
    // InternalXScript.g:6451:1: entryRuleXValueVarSessionAggType returns [EObject current=null] : iv_ruleXValueVarSessionAggType= ruleXValueVarSessionAggType EOF ;
    public final EObject entryRuleXValueVarSessionAggType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXValueVarSessionAggType = null;


        try {
            // InternalXScript.g:6451:64: (iv_ruleXValueVarSessionAggType= ruleXValueVarSessionAggType EOF )
            // InternalXScript.g:6452:2: iv_ruleXValueVarSessionAggType= ruleXValueVarSessionAggType EOF
            {
             newCompositeNode(grammarAccess.getXValueVarSessionAggTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXValueVarSessionAggType=ruleXValueVarSessionAggType();

            state._fsp--;

             current =iv_ruleXValueVarSessionAggType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXValueVarSessionAggType"


    // $ANTLR start "ruleXValueVarSessionAggType"
    // InternalXScript.g:6458:1: ruleXValueVarSessionAggType returns [EObject current=null] : ( () otherlv_1= 'sessionVarAgg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleXValueVarSessionAggFunction ) ) otherlv_6= ',' ( (lv_range_7_0= ruleXTimeRange ) ) otherlv_8= ')' ) ;
    public final EObject ruleXValueVarSessionAggType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_function_5_0 = null;

        EObject lv_range_7_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6464:2: ( ( () otherlv_1= 'sessionVarAgg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleXValueVarSessionAggFunction ) ) otherlv_6= ',' ( (lv_range_7_0= ruleXTimeRange ) ) otherlv_8= ')' ) )
            // InternalXScript.g:6465:2: ( () otherlv_1= 'sessionVarAgg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleXValueVarSessionAggFunction ) ) otherlv_6= ',' ( (lv_range_7_0= ruleXTimeRange ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:6465:2: ( () otherlv_1= 'sessionVarAgg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleXValueVarSessionAggFunction ) ) otherlv_6= ',' ( (lv_range_7_0= ruleXTimeRange ) ) otherlv_8= ')' )
            // InternalXScript.g:6466:3: () otherlv_1= 'sessionVarAgg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleXValueVarSessionAggFunction ) ) otherlv_6= ',' ( (lv_range_7_0= ruleXTimeRange ) ) otherlv_8= ')'
            {
            // InternalXScript.g:6466:3: ()
            // InternalXScript.g:6467:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXValueVarSessionAggTypeAccess().getXValueVarSessionAggTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,80,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXValueVarSessionAggTypeAccess().getSessionVarAggKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXValueVarSessionAggTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6481:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6482:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6482:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6483:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXValueVarSessionAggTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXValueVarSessionAggTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_47); 

            			newLeafNode(otherlv_4, grammarAccess.getXValueVarSessionAggTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6498:3: ( (lv_function_5_0= ruleXValueVarSessionAggFunction ) )
            // InternalXScript.g:6499:4: (lv_function_5_0= ruleXValueVarSessionAggFunction )
            {
            // InternalXScript.g:6499:4: (lv_function_5_0= ruleXValueVarSessionAggFunction )
            // InternalXScript.g:6500:5: lv_function_5_0= ruleXValueVarSessionAggFunction
            {

            					newCompositeNode(grammarAccess.getXValueVarSessionAggTypeAccess().getFunctionXValueVarSessionAggFunctionEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_7);
            lv_function_5_0=ruleXValueVarSessionAggFunction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXValueVarSessionAggTypeRule());
            					}
            					set(
            						current,
            						"function",
            						lv_function_5_0,
            						"com.dunkware.xstream.XScript.XValueVarSessionAggFunction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_48); 

            			newLeafNode(otherlv_6, grammarAccess.getXValueVarSessionAggTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6521:3: ( (lv_range_7_0= ruleXTimeRange ) )
            // InternalXScript.g:6522:4: (lv_range_7_0= ruleXTimeRange )
            {
            // InternalXScript.g:6522:4: (lv_range_7_0= ruleXTimeRange )
            // InternalXScript.g:6523:5: lv_range_7_0= ruleXTimeRange
            {

            					newCompositeNode(grammarAccess.getXValueVarSessionAggTypeAccess().getRangeXTimeRangeParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_9);
            lv_range_7_0=ruleXTimeRange();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXValueVarSessionAggTypeRule());
            					}
            					set(
            						current,
            						"range",
            						lv_range_7_0,
            						"com.dunkware.xstream.XScript.XTimeRange");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getXValueVarSessionAggTypeAccess().getRightParenthesisKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXValueVarSessionAggType"


    // $ANTLR start "entryRuleXValueSignalSessionCountType"
    // InternalXScript.g:6548:1: entryRuleXValueSignalSessionCountType returns [EObject current=null] : iv_ruleXValueSignalSessionCountType= ruleXValueSignalSessionCountType EOF ;
    public final EObject entryRuleXValueSignalSessionCountType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXValueSignalSessionCountType = null;


        try {
            // InternalXScript.g:6548:69: (iv_ruleXValueSignalSessionCountType= ruleXValueSignalSessionCountType EOF )
            // InternalXScript.g:6549:2: iv_ruleXValueSignalSessionCountType= ruleXValueSignalSessionCountType EOF
            {
             newCompositeNode(grammarAccess.getXValueSignalSessionCountTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXValueSignalSessionCountType=ruleXValueSignalSessionCountType();

            state._fsp--;

             current =iv_ruleXValueSignalSessionCountType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXValueSignalSessionCountType"


    // $ANTLR start "ruleXValueSignalSessionCountType"
    // InternalXScript.g:6555:1: ruleXValueSignalSessionCountType returns [EObject current=null] : ( () otherlv_1= 'sessionSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleXTimeRange ) ) otherlv_6= ',' ( (lv_value_7_0= RULE_INT ) ) otherlv_8= ')' ) ;
    public final EObject ruleXValueSignalSessionCountType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_value_7_0=null;
        Token otherlv_8=null;
        EObject lv_timeRange_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6561:2: ( ( () otherlv_1= 'sessionSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleXTimeRange ) ) otherlv_6= ',' ( (lv_value_7_0= RULE_INT ) ) otherlv_8= ')' ) )
            // InternalXScript.g:6562:2: ( () otherlv_1= 'sessionSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleXTimeRange ) ) otherlv_6= ',' ( (lv_value_7_0= RULE_INT ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:6562:2: ( () otherlv_1= 'sessionSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleXTimeRange ) ) otherlv_6= ',' ( (lv_value_7_0= RULE_INT ) ) otherlv_8= ')' )
            // InternalXScript.g:6563:3: () otherlv_1= 'sessionSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleXTimeRange ) ) otherlv_6= ',' ( (lv_value_7_0= RULE_INT ) ) otherlv_8= ')'
            {
            // InternalXScript.g:6563:3: ()
            // InternalXScript.g:6564:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXValueSignalSessionCountTypeAccess().getXValueSignalSessionCountTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,81,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXValueSignalSessionCountTypeAccess().getSessionSignalCountKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXValueSignalSessionCountTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6578:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6579:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6579:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6580:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXValueSignalSessionCountTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXValueSignalSessionCountTypeAccess().getSignalSignalTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_48); 

            			newLeafNode(otherlv_4, grammarAccess.getXValueSignalSessionCountTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6595:3: ( (lv_timeRange_5_0= ruleXTimeRange ) )
            // InternalXScript.g:6596:4: (lv_timeRange_5_0= ruleXTimeRange )
            {
            // InternalXScript.g:6596:4: (lv_timeRange_5_0= ruleXTimeRange )
            // InternalXScript.g:6597:5: lv_timeRange_5_0= ruleXTimeRange
            {

            					newCompositeNode(grammarAccess.getXValueSignalSessionCountTypeAccess().getTimeRangeXTimeRangeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_7);
            lv_timeRange_5_0=ruleXTimeRange();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXValueSignalSessionCountTypeRule());
            					}
            					set(
            						current,
            						"timeRange",
            						lv_timeRange_5_0,
            						"com.dunkware.xstream.XScript.XTimeRange");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_6, grammarAccess.getXValueSignalSessionCountTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6618:3: ( (lv_value_7_0= RULE_INT ) )
            // InternalXScript.g:6619:4: (lv_value_7_0= RULE_INT )
            {
            // InternalXScript.g:6619:4: (lv_value_7_0= RULE_INT )
            // InternalXScript.g:6620:5: lv_value_7_0= RULE_INT
            {
            lv_value_7_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            					newLeafNode(lv_value_7_0, grammarAccess.getXValueSignalSessionCountTypeAccess().getValueINTTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXValueSignalSessionCountTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_7_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_8=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getXValueSignalSessionCountTypeAccess().getRightParenthesisKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXValueSignalSessionCountType"


    // $ANTLR start "entryRuleXValueSignalHistoricalCountType"
    // InternalXScript.g:6644:1: entryRuleXValueSignalHistoricalCountType returns [EObject current=null] : iv_ruleXValueSignalHistoricalCountType= ruleXValueSignalHistoricalCountType EOF ;
    public final EObject entryRuleXValueSignalHistoricalCountType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXValueSignalHistoricalCountType = null;


        try {
            // InternalXScript.g:6644:72: (iv_ruleXValueSignalHistoricalCountType= ruleXValueSignalHistoricalCountType EOF )
            // InternalXScript.g:6645:2: iv_ruleXValueSignalHistoricalCountType= ruleXValueSignalHistoricalCountType EOF
            {
             newCompositeNode(grammarAccess.getXValueSignalHistoricalCountTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXValueSignalHistoricalCountType=ruleXValueSignalHistoricalCountType();

            state._fsp--;

             current =iv_ruleXValueSignalHistoricalCountType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXValueSignalHistoricalCountType"


    // $ANTLR start "ruleXValueSignalHistoricalCountType"
    // InternalXScript.g:6651:1: ruleXValueSignalHistoricalCountType returns [EObject current=null] : ( () otherlv_1= 'historicalSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_days_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_count_7_0= RULE_INT ) ) otherlv_8= ')' ) ;
    public final EObject ruleXValueSignalHistoricalCountType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_days_5_0=null;
        Token otherlv_6=null;
        Token lv_count_7_0=null;
        Token otherlv_8=null;


        	enterRule();

        try {
            // InternalXScript.g:6657:2: ( ( () otherlv_1= 'historicalSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_days_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_count_7_0= RULE_INT ) ) otherlv_8= ')' ) )
            // InternalXScript.g:6658:2: ( () otherlv_1= 'historicalSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_days_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_count_7_0= RULE_INT ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:6658:2: ( () otherlv_1= 'historicalSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_days_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_count_7_0= RULE_INT ) ) otherlv_8= ')' )
            // InternalXScript.g:6659:3: () otherlv_1= 'historicalSignalCount' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_days_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_count_7_0= RULE_INT ) ) otherlv_8= ')'
            {
            // InternalXScript.g:6659:3: ()
            // InternalXScript.g:6660:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXValueSignalHistoricalCountTypeAccess().getXValueSignalHistoricalCountTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,82,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXValueSignalHistoricalCountTypeAccess().getHistoricalSignalCountKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXValueSignalHistoricalCountTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6674:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6675:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6675:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6676:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXValueSignalHistoricalCountTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXValueSignalHistoricalCountTypeAccess().getSignalSignalTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_4, grammarAccess.getXValueSignalHistoricalCountTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6691:3: ( (lv_days_5_0= RULE_INT ) )
            // InternalXScript.g:6692:4: (lv_days_5_0= RULE_INT )
            {
            // InternalXScript.g:6692:4: (lv_days_5_0= RULE_INT )
            // InternalXScript.g:6693:5: lv_days_5_0= RULE_INT
            {
            lv_days_5_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            					newLeafNode(lv_days_5_0, grammarAccess.getXValueSignalHistoricalCountTypeAccess().getDaysINTTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXValueSignalHistoricalCountTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"days",
            						lv_days_5_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_6); 

            			newLeafNode(otherlv_6, grammarAccess.getXValueSignalHistoricalCountTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6713:3: ( (lv_count_7_0= RULE_INT ) )
            // InternalXScript.g:6714:4: (lv_count_7_0= RULE_INT )
            {
            // InternalXScript.g:6714:4: (lv_count_7_0= RULE_INT )
            // InternalXScript.g:6715:5: lv_count_7_0= RULE_INT
            {
            lv_count_7_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            					newLeafNode(lv_count_7_0, grammarAccess.getXValueSignalHistoricalCountTypeAccess().getCountINTTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXValueSignalHistoricalCountTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"count",
            						lv_count_7_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_8=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getXValueSignalHistoricalCountTypeAccess().getRightParenthesisKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXValueSignalHistoricalCountType"


    // $ANTLR start "entryRuleXQueryType"
    // InternalXScript.g:6739:1: entryRuleXQueryType returns [EObject current=null] : iv_ruleXQueryType= ruleXQueryType EOF ;
    public final EObject entryRuleXQueryType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXQueryType = null;


        try {
            // InternalXScript.g:6739:51: (iv_ruleXQueryType= ruleXQueryType EOF )
            // InternalXScript.g:6740:2: iv_ruleXQueryType= ruleXQueryType EOF
            {
             newCompositeNode(grammarAccess.getXQueryTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXQueryType=ruleXQueryType();

            state._fsp--;

             current =iv_ruleXQueryType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXQueryType"


    // $ANTLR start "ruleXQueryType"
    // InternalXScript.g:6746:1: ruleXQueryType returns [EObject current=null] : (otherlv_0= 'query' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_filters_5_0= ruleXQueryFilterType ) )* otherlv_6= '}' ) ;
    public final EObject ruleXQueryType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_filters_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6752:2: ( (otherlv_0= 'query' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_filters_5_0= ruleXQueryFilterType ) )* otherlv_6= '}' ) )
            // InternalXScript.g:6753:2: (otherlv_0= 'query' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_filters_5_0= ruleXQueryFilterType ) )* otherlv_6= '}' )
            {
            // InternalXScript.g:6753:2: (otherlv_0= 'query' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_filters_5_0= ruleXQueryFilterType ) )* otherlv_6= '}' )
            // InternalXScript.g:6754:3: otherlv_0= 'query' otherlv_1= '(' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_filters_5_0= ruleXQueryFilterType ) )* otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,83,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getXQueryTypeAccess().getQueryKeyword_0());
            		
            otherlv_1=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXQueryTypeAccess().getLeftParenthesisKeyword_1());
            		
            // InternalXScript.g:6762:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:6763:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:6763:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:6764:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_9); 

            					newLeafNode(lv_name_2_0, grammarAccess.getXQueryTypeAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXQueryTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,15,FOLLOW_25); 

            			newLeafNode(otherlv_3, grammarAccess.getXQueryTypeAccess().getRightParenthesisKeyword_3());
            		
            otherlv_4=(Token)match(input,37,FOLLOW_49); 

            			newLeafNode(otherlv_4, grammarAccess.getXQueryTypeAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalXScript.g:6788:3: ( (lv_filters_5_0= ruleXQueryFilterType ) )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( ((LA61_0>=84 && LA61_0<=85)) ) {
                    alt61=1;
                }


                switch (alt61) {
            	case 1 :
            	    // InternalXScript.g:6789:4: (lv_filters_5_0= ruleXQueryFilterType )
            	    {
            	    // InternalXScript.g:6789:4: (lv_filters_5_0= ruleXQueryFilterType )
            	    // InternalXScript.g:6790:5: lv_filters_5_0= ruleXQueryFilterType
            	    {

            	    					newCompositeNode(grammarAccess.getXQueryTypeAccess().getFiltersXQueryFilterTypeParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_49);
            	    lv_filters_5_0=ruleXQueryFilterType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getXQueryTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"filters",
            	    						lv_filters_5_0,
            	    						"com.dunkware.xstream.XScript.XQueryFilterType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);

            otherlv_6=(Token)match(input,38,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getXQueryTypeAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXQueryType"


    // $ANTLR start "entryRuleXQueryFilterType"
    // InternalXScript.g:6815:1: entryRuleXQueryFilterType returns [EObject current=null] : iv_ruleXQueryFilterType= ruleXQueryFilterType EOF ;
    public final EObject entryRuleXQueryFilterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXQueryFilterType = null;


        try {
            // InternalXScript.g:6815:57: (iv_ruleXQueryFilterType= ruleXQueryFilterType EOF )
            // InternalXScript.g:6816:2: iv_ruleXQueryFilterType= ruleXQueryFilterType EOF
            {
             newCompositeNode(grammarAccess.getXQueryFilterTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXQueryFilterType=ruleXQueryFilterType();

            state._fsp--;

             current =iv_ruleXQueryFilterType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXQueryFilterType"


    // $ANTLR start "ruleXQueryFilterType"
    // InternalXScript.g:6822:1: ruleXQueryFilterType returns [EObject current=null] : (this_XQueryFilterValueCompareType_0= ruleXQueryFilterValueCompareType | this_XQueryFilterValueType_1= ruleXQueryFilterValueType ) ;
    public final EObject ruleXQueryFilterType() throws RecognitionException {
        EObject current = null;

        EObject this_XQueryFilterValueCompareType_0 = null;

        EObject this_XQueryFilterValueType_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:6828:2: ( (this_XQueryFilterValueCompareType_0= ruleXQueryFilterValueCompareType | this_XQueryFilterValueType_1= ruleXQueryFilterValueType ) )
            // InternalXScript.g:6829:2: (this_XQueryFilterValueCompareType_0= ruleXQueryFilterValueCompareType | this_XQueryFilterValueType_1= ruleXQueryFilterValueType )
            {
            // InternalXScript.g:6829:2: (this_XQueryFilterValueCompareType_0= ruleXQueryFilterValueCompareType | this_XQueryFilterValueType_1= ruleXQueryFilterValueType )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==85) ) {
                alt62=1;
            }
            else if ( (LA62_0==84) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // InternalXScript.g:6830:3: this_XQueryFilterValueCompareType_0= ruleXQueryFilterValueCompareType
                    {

                    			newCompositeNode(grammarAccess.getXQueryFilterTypeAccess().getXQueryFilterValueCompareTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_XQueryFilterValueCompareType_0=ruleXQueryFilterValueCompareType();

                    state._fsp--;


                    			current = this_XQueryFilterValueCompareType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalXScript.g:6839:3: this_XQueryFilterValueType_1= ruleXQueryFilterValueType
                    {

                    			newCompositeNode(grammarAccess.getXQueryFilterTypeAccess().getXQueryFilterValueTypeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_XQueryFilterValueType_1=ruleXQueryFilterValueType();

                    state._fsp--;


                    			current = this_XQueryFilterValueType_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXQueryFilterType"


    // $ANTLR start "entryRuleXQueryFilterValueType"
    // InternalXScript.g:6851:1: entryRuleXQueryFilterValueType returns [EObject current=null] : iv_ruleXQueryFilterValueType= ruleXQueryFilterValueType EOF ;
    public final EObject entryRuleXQueryFilterValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXQueryFilterValueType = null;


        try {
            // InternalXScript.g:6851:62: (iv_ruleXQueryFilterValueType= ruleXQueryFilterValueType EOF )
            // InternalXScript.g:6852:2: iv_ruleXQueryFilterValueType= ruleXQueryFilterValueType EOF
            {
             newCompositeNode(grammarAccess.getXQueryFilterValueTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXQueryFilterValueType=ruleXQueryFilterValueType();

            state._fsp--;

             current =iv_ruleXQueryFilterValueType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXQueryFilterValueType"


    // $ANTLR start "ruleXQueryFilterValueType"
    // InternalXScript.g:6858:1: ruleXQueryFilterValueType returns [EObject current=null] : ( () otherlv_1= 'valueFilter' otherlv_2= '(' ( (lv_value_3_0= ruleXValueType ) ) otherlv_4= ',' ( (lv_operator_5_0= ruleXStreamOperator ) ) otherlv_6= ',' ( (lv_criteria_7_0= RULE_STRING ) ) otherlv_8= ')' ) ;
    public final EObject ruleXQueryFilterValueType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token lv_criteria_7_0=null;
        Token otherlv_8=null;
        EObject lv_value_3_0 = null;

        Enumerator lv_operator_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6864:2: ( ( () otherlv_1= 'valueFilter' otherlv_2= '(' ( (lv_value_3_0= ruleXValueType ) ) otherlv_4= ',' ( (lv_operator_5_0= ruleXStreamOperator ) ) otherlv_6= ',' ( (lv_criteria_7_0= RULE_STRING ) ) otherlv_8= ')' ) )
            // InternalXScript.g:6865:2: ( () otherlv_1= 'valueFilter' otherlv_2= '(' ( (lv_value_3_0= ruleXValueType ) ) otherlv_4= ',' ( (lv_operator_5_0= ruleXStreamOperator ) ) otherlv_6= ',' ( (lv_criteria_7_0= RULE_STRING ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:6865:2: ( () otherlv_1= 'valueFilter' otherlv_2= '(' ( (lv_value_3_0= ruleXValueType ) ) otherlv_4= ',' ( (lv_operator_5_0= ruleXStreamOperator ) ) otherlv_6= ',' ( (lv_criteria_7_0= RULE_STRING ) ) otherlv_8= ')' )
            // InternalXScript.g:6866:3: () otherlv_1= 'valueFilter' otherlv_2= '(' ( (lv_value_3_0= ruleXValueType ) ) otherlv_4= ',' ( (lv_operator_5_0= ruleXStreamOperator ) ) otherlv_6= ',' ( (lv_criteria_7_0= RULE_STRING ) ) otherlv_8= ')'
            {
            // InternalXScript.g:6866:3: ()
            // InternalXScript.g:6867:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXQueryFilterValueTypeAccess().getXQueryFilterValueTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,84,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXQueryFilterValueTypeAccess().getValueFilterKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_50); 

            			newLeafNode(otherlv_2, grammarAccess.getXQueryFilterValueTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6881:3: ( (lv_value_3_0= ruleXValueType ) )
            // InternalXScript.g:6882:4: (lv_value_3_0= ruleXValueType )
            {
            // InternalXScript.g:6882:4: (lv_value_3_0= ruleXValueType )
            // InternalXScript.g:6883:5: lv_value_3_0= ruleXValueType
            {

            					newCompositeNode(grammarAccess.getXQueryFilterValueTypeAccess().getValueXValueTypeParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_7);
            lv_value_3_0=ruleXValueType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXQueryFilterValueTypeRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_3_0,
            						"com.dunkware.xstream.XScript.XValueType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_51); 

            			newLeafNode(otherlv_4, grammarAccess.getXQueryFilterValueTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6904:3: ( (lv_operator_5_0= ruleXStreamOperator ) )
            // InternalXScript.g:6905:4: (lv_operator_5_0= ruleXStreamOperator )
            {
            // InternalXScript.g:6905:4: (lv_operator_5_0= ruleXStreamOperator )
            // InternalXScript.g:6906:5: lv_operator_5_0= ruleXStreamOperator
            {

            					newCompositeNode(grammarAccess.getXQueryFilterValueTypeAccess().getOperatorXStreamOperatorEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_7);
            lv_operator_5_0=ruleXStreamOperator();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXQueryFilterValueTypeRule());
            					}
            					set(
            						current,
            						"operator",
            						lv_operator_5_0,
            						"com.dunkware.xstream.XScript.XStreamOperator");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_6, grammarAccess.getXQueryFilterValueTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6927:3: ( (lv_criteria_7_0= RULE_STRING ) )
            // InternalXScript.g:6928:4: (lv_criteria_7_0= RULE_STRING )
            {
            // InternalXScript.g:6928:4: (lv_criteria_7_0= RULE_STRING )
            // InternalXScript.g:6929:5: lv_criteria_7_0= RULE_STRING
            {
            lv_criteria_7_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

            					newLeafNode(lv_criteria_7_0, grammarAccess.getXQueryFilterValueTypeAccess().getCriteriaSTRINGTerminalRuleCall_7_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXQueryFilterValueTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"criteria",
            						lv_criteria_7_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_8=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getXQueryFilterValueTypeAccess().getRightParenthesisKeyword_8());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXQueryFilterValueType"


    // $ANTLR start "entryRuleXQueryFilterValueCompareType"
    // InternalXScript.g:6953:1: entryRuleXQueryFilterValueCompareType returns [EObject current=null] : iv_ruleXQueryFilterValueCompareType= ruleXQueryFilterValueCompareType EOF ;
    public final EObject entryRuleXQueryFilterValueCompareType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXQueryFilterValueCompareType = null;


        try {
            // InternalXScript.g:6953:69: (iv_ruleXQueryFilterValueCompareType= ruleXQueryFilterValueCompareType EOF )
            // InternalXScript.g:6954:2: iv_ruleXQueryFilterValueCompareType= ruleXQueryFilterValueCompareType EOF
            {
             newCompositeNode(grammarAccess.getXQueryFilterValueCompareTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleXQueryFilterValueCompareType=ruleXQueryFilterValueCompareType();

            state._fsp--;

             current =iv_ruleXQueryFilterValueCompareType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleXQueryFilterValueCompareType"


    // $ANTLR start "ruleXQueryFilterValueCompareType"
    // InternalXScript.g:6960:1: ruleXQueryFilterValueCompareType returns [EObject current=null] : (otherlv_0= 'valueCompareFilter' otherlv_1= '(' ( (lv_value1_2_0= ruleXValueType ) ) otherlv_3= ',' ( (lv_value2_4_0= ruleXValueType ) ) otherlv_5= ',' ( (lv_function_6_0= ruleXQueryValueCompareFunction ) ) otherlv_7= ',' ( (lv_operator_8_0= ruleXStreamOperator ) ) otherlv_9= ',' ( (lv_criteria_10_0= RULE_STRING ) ) otherlv_11= ')' ) ;
    public final EObject ruleXQueryFilterValueCompareType() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token lv_criteria_10_0=null;
        Token otherlv_11=null;
        EObject lv_value1_2_0 = null;

        EObject lv_value2_4_0 = null;

        Enumerator lv_function_6_0 = null;

        Enumerator lv_operator_8_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:6966:2: ( (otherlv_0= 'valueCompareFilter' otherlv_1= '(' ( (lv_value1_2_0= ruleXValueType ) ) otherlv_3= ',' ( (lv_value2_4_0= ruleXValueType ) ) otherlv_5= ',' ( (lv_function_6_0= ruleXQueryValueCompareFunction ) ) otherlv_7= ',' ( (lv_operator_8_0= ruleXStreamOperator ) ) otherlv_9= ',' ( (lv_criteria_10_0= RULE_STRING ) ) otherlv_11= ')' ) )
            // InternalXScript.g:6967:2: (otherlv_0= 'valueCompareFilter' otherlv_1= '(' ( (lv_value1_2_0= ruleXValueType ) ) otherlv_3= ',' ( (lv_value2_4_0= ruleXValueType ) ) otherlv_5= ',' ( (lv_function_6_0= ruleXQueryValueCompareFunction ) ) otherlv_7= ',' ( (lv_operator_8_0= ruleXStreamOperator ) ) otherlv_9= ',' ( (lv_criteria_10_0= RULE_STRING ) ) otherlv_11= ')' )
            {
            // InternalXScript.g:6967:2: (otherlv_0= 'valueCompareFilter' otherlv_1= '(' ( (lv_value1_2_0= ruleXValueType ) ) otherlv_3= ',' ( (lv_value2_4_0= ruleXValueType ) ) otherlv_5= ',' ( (lv_function_6_0= ruleXQueryValueCompareFunction ) ) otherlv_7= ',' ( (lv_operator_8_0= ruleXStreamOperator ) ) otherlv_9= ',' ( (lv_criteria_10_0= RULE_STRING ) ) otherlv_11= ')' )
            // InternalXScript.g:6968:3: otherlv_0= 'valueCompareFilter' otherlv_1= '(' ( (lv_value1_2_0= ruleXValueType ) ) otherlv_3= ',' ( (lv_value2_4_0= ruleXValueType ) ) otherlv_5= ',' ( (lv_function_6_0= ruleXQueryValueCompareFunction ) ) otherlv_7= ',' ( (lv_operator_8_0= ruleXStreamOperator ) ) otherlv_9= ',' ( (lv_criteria_10_0= RULE_STRING ) ) otherlv_11= ')'
            {
            otherlv_0=(Token)match(input,85,FOLLOW_5); 

            			newLeafNode(otherlv_0, grammarAccess.getXQueryFilterValueCompareTypeAccess().getValueCompareFilterKeyword_0());
            		
            otherlv_1=(Token)match(input,13,FOLLOW_50); 

            			newLeafNode(otherlv_1, grammarAccess.getXQueryFilterValueCompareTypeAccess().getLeftParenthesisKeyword_1());
            		
            // InternalXScript.g:6976:3: ( (lv_value1_2_0= ruleXValueType ) )
            // InternalXScript.g:6977:4: (lv_value1_2_0= ruleXValueType )
            {
            // InternalXScript.g:6977:4: (lv_value1_2_0= ruleXValueType )
            // InternalXScript.g:6978:5: lv_value1_2_0= ruleXValueType
            {

            					newCompositeNode(grammarAccess.getXQueryFilterValueCompareTypeAccess().getValue1XValueTypeParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_7);
            lv_value1_2_0=ruleXValueType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXQueryFilterValueCompareTypeRule());
            					}
            					set(
            						current,
            						"value1",
            						lv_value1_2_0,
            						"com.dunkware.xstream.XScript.XValueType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,14,FOLLOW_50); 

            			newLeafNode(otherlv_3, grammarAccess.getXQueryFilterValueCompareTypeAccess().getCommaKeyword_3());
            		
            // InternalXScript.g:6999:3: ( (lv_value2_4_0= ruleXValueType ) )
            // InternalXScript.g:7000:4: (lv_value2_4_0= ruleXValueType )
            {
            // InternalXScript.g:7000:4: (lv_value2_4_0= ruleXValueType )
            // InternalXScript.g:7001:5: lv_value2_4_0= ruleXValueType
            {

            					newCompositeNode(grammarAccess.getXQueryFilterValueCompareTypeAccess().getValue2XValueTypeParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_7);
            lv_value2_4_0=ruleXValueType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXQueryFilterValueCompareTypeRule());
            					}
            					set(
            						current,
            						"value2",
            						lv_value2_4_0,
            						"com.dunkware.xstream.XScript.XValueType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,14,FOLLOW_52); 

            			newLeafNode(otherlv_5, grammarAccess.getXQueryFilterValueCompareTypeAccess().getCommaKeyword_5());
            		
            // InternalXScript.g:7022:3: ( (lv_function_6_0= ruleXQueryValueCompareFunction ) )
            // InternalXScript.g:7023:4: (lv_function_6_0= ruleXQueryValueCompareFunction )
            {
            // InternalXScript.g:7023:4: (lv_function_6_0= ruleXQueryValueCompareFunction )
            // InternalXScript.g:7024:5: lv_function_6_0= ruleXQueryValueCompareFunction
            {

            					newCompositeNode(grammarAccess.getXQueryFilterValueCompareTypeAccess().getFunctionXQueryValueCompareFunctionEnumRuleCall_6_0());
            				
            pushFollow(FOLLOW_7);
            lv_function_6_0=ruleXQueryValueCompareFunction();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXQueryFilterValueCompareTypeRule());
            					}
            					set(
            						current,
            						"function",
            						lv_function_6_0,
            						"com.dunkware.xstream.XScript.XQueryValueCompareFunction");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,14,FOLLOW_51); 

            			newLeafNode(otherlv_7, grammarAccess.getXQueryFilterValueCompareTypeAccess().getCommaKeyword_7());
            		
            // InternalXScript.g:7045:3: ( (lv_operator_8_0= ruleXStreamOperator ) )
            // InternalXScript.g:7046:4: (lv_operator_8_0= ruleXStreamOperator )
            {
            // InternalXScript.g:7046:4: (lv_operator_8_0= ruleXStreamOperator )
            // InternalXScript.g:7047:5: lv_operator_8_0= ruleXStreamOperator
            {

            					newCompositeNode(grammarAccess.getXQueryFilterValueCompareTypeAccess().getOperatorXStreamOperatorEnumRuleCall_8_0());
            				
            pushFollow(FOLLOW_7);
            lv_operator_8_0=ruleXStreamOperator();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getXQueryFilterValueCompareTypeRule());
            					}
            					set(
            						current,
            						"operator",
            						lv_operator_8_0,
            						"com.dunkware.xstream.XScript.XStreamOperator");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_9=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_9, grammarAccess.getXQueryFilterValueCompareTypeAccess().getCommaKeyword_9());
            		
            // InternalXScript.g:7068:3: ( (lv_criteria_10_0= RULE_STRING ) )
            // InternalXScript.g:7069:4: (lv_criteria_10_0= RULE_STRING )
            {
            // InternalXScript.g:7069:4: (lv_criteria_10_0= RULE_STRING )
            // InternalXScript.g:7070:5: lv_criteria_10_0= RULE_STRING
            {
            lv_criteria_10_0=(Token)match(input,RULE_STRING,FOLLOW_9); 

            					newLeafNode(lv_criteria_10_0, grammarAccess.getXQueryFilterValueCompareTypeAccess().getCriteriaSTRINGTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXQueryFilterValueCompareTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"criteria",
            						lv_criteria_10_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_11=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getXQueryFilterValueCompareTypeAccess().getRightParenthesisKeyword_11());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXQueryFilterValueCompareType"


    // $ANTLR start "ruleStreamTimeUnit"
    // InternalXScript.g:7094:1: ruleStreamTimeUnit returns [Enumerator current=null] : ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) ) ;
    public final Enumerator ruleStreamTimeUnit() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalXScript.g:7100:2: ( ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) ) )
            // InternalXScript.g:7101:2: ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) )
            {
            // InternalXScript.g:7101:2: ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) )
            int alt63=3;
            switch ( input.LA(1) ) {
            case 86:
                {
                alt63=1;
                }
                break;
            case 87:
                {
                alt63=2;
                }
                break;
            case 88:
                {
                alt63=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }

            switch (alt63) {
                case 1 :
                    // InternalXScript.g:7102:3: (enumLiteral_0= 'SEC' )
                    {
                    // InternalXScript.g:7102:3: (enumLiteral_0= 'SEC' )
                    // InternalXScript.g:7103:4: enumLiteral_0= 'SEC'
                    {
                    enumLiteral_0=(Token)match(input,86,FOLLOW_2); 

                    				current = grammarAccess.getStreamTimeUnitAccess().getSecondEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getStreamTimeUnitAccess().getSecondEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:7110:3: (enumLiteral_1= 'MIN' )
                    {
                    // InternalXScript.g:7110:3: (enumLiteral_1= 'MIN' )
                    // InternalXScript.g:7111:4: enumLiteral_1= 'MIN'
                    {
                    enumLiteral_1=(Token)match(input,87,FOLLOW_2); 

                    				current = grammarAccess.getStreamTimeUnitAccess().getMinuteEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getStreamTimeUnitAccess().getMinuteEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:7118:3: (enumLiteral_2= 'HOUR' )
                    {
                    // InternalXScript.g:7118:3: (enumLiteral_2= 'HOUR' )
                    // InternalXScript.g:7119:4: enumLiteral_2= 'HOUR'
                    {
                    enumLiteral_2=(Token)match(input,88,FOLLOW_2); 

                    				current = grammarAccess.getStreamTimeUnitAccess().getHourEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getStreamTimeUnitAccess().getHourEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStreamTimeUnit"


    // $ANTLR start "ruleDataType"
    // InternalXScript.g:7129:1: ruleDataType returns [Enumerator current=null] : ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) ) ;
    public final Enumerator ruleDataType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;
        Token enumLiteral_6=null;
        Token enumLiteral_7=null;


        	enterRule();

        try {
            // InternalXScript.g:7135:2: ( ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) ) )
            // InternalXScript.g:7136:2: ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) )
            {
            // InternalXScript.g:7136:2: ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) )
            int alt64=8;
            switch ( input.LA(1) ) {
            case 89:
                {
                alt64=1;
                }
                break;
            case 90:
                {
                alt64=2;
                }
                break;
            case 91:
                {
                alt64=3;
                }
                break;
            case 92:
                {
                alt64=4;
                }
                break;
            case 93:
                {
                alt64=5;
                }
                break;
            case 94:
                {
                alt64=6;
                }
                break;
            case 95:
                {
                alt64=7;
                }
                break;
            case 96:
                {
                alt64=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // InternalXScript.g:7137:3: (enumLiteral_0= 'STR' )
                    {
                    // InternalXScript.g:7137:3: (enumLiteral_0= 'STR' )
                    // InternalXScript.g:7138:4: enumLiteral_0= 'STR'
                    {
                    enumLiteral_0=(Token)match(input,89,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getSTREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getDataTypeAccess().getSTREnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:7145:3: (enumLiteral_1= 'INT' )
                    {
                    // InternalXScript.g:7145:3: (enumLiteral_1= 'INT' )
                    // InternalXScript.g:7146:4: enumLiteral_1= 'INT'
                    {
                    enumLiteral_1=(Token)match(input,90,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getINTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getDataTypeAccess().getINTEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:7153:3: (enumLiteral_2= 'BOOl' )
                    {
                    // InternalXScript.g:7153:3: (enumLiteral_2= 'BOOl' )
                    // InternalXScript.g:7154:4: enumLiteral_2= 'BOOl'
                    {
                    enumLiteral_2=(Token)match(input,91,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getBOOlEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getDataTypeAccess().getBOOlEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalXScript.g:7161:3: (enumLiteral_3= 'T' )
                    {
                    // InternalXScript.g:7161:3: (enumLiteral_3= 'T' )
                    // InternalXScript.g:7162:4: enumLiteral_3= 'T'
                    {
                    enumLiteral_3=(Token)match(input,92,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getDataTypeAccess().getTEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalXScript.g:7169:3: (enumLiteral_4= 'DT' )
                    {
                    // InternalXScript.g:7169:3: (enumLiteral_4= 'DT' )
                    // InternalXScript.g:7170:4: enumLiteral_4= 'DT'
                    {
                    enumLiteral_4=(Token)match(input,93,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getDataTypeAccess().getDTEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalXScript.g:7177:3: (enumLiteral_5= 'DATE' )
                    {
                    // InternalXScript.g:7177:3: (enumLiteral_5= 'DATE' )
                    // InternalXScript.g:7178:4: enumLiteral_5= 'DATE'
                    {
                    enumLiteral_5=(Token)match(input,94,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDATEEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getDataTypeAccess().getDATEEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalXScript.g:7185:3: (enumLiteral_6= 'DUB' )
                    {
                    // InternalXScript.g:7185:3: (enumLiteral_6= 'DUB' )
                    // InternalXScript.g:7186:4: enumLiteral_6= 'DUB'
                    {
                    enumLiteral_6=(Token)match(input,95,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDUBEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getDataTypeAccess().getDUBEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalXScript.g:7193:3: (enumLiteral_7= 'LONG' )
                    {
                    // InternalXScript.g:7193:3: (enumLiteral_7= 'LONG' )
                    // InternalXScript.g:7194:4: enumLiteral_7= 'LONG'
                    {
                    enumLiteral_7=(Token)match(input,96,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getLONGEnumLiteralDeclaration_7().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_7, grammarAccess.getDataTypeAccess().getLONGEnumLiteralDeclaration_7());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDataType"


    // $ANTLR start "ruleXValueVarSessionAggFunction"
    // InternalXScript.g:7204:1: ruleXValueVarSessionAggFunction returns [Enumerator current=null] : ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) ) ;
    public final Enumerator ruleXValueVarSessionAggFunction() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalXScript.g:7210:2: ( ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) ) )
            // InternalXScript.g:7211:2: ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) )
            {
            // InternalXScript.g:7211:2: ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) )
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==97) ) {
                alt65=1;
            }
            else if ( (LA65_0==98) ) {
                alt65=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }
            switch (alt65) {
                case 1 :
                    // InternalXScript.g:7212:3: (enumLiteral_0= 'HIGH' )
                    {
                    // InternalXScript.g:7212:3: (enumLiteral_0= 'HIGH' )
                    // InternalXScript.g:7213:4: enumLiteral_0= 'HIGH'
                    {
                    enumLiteral_0=(Token)match(input,97,FOLLOW_2); 

                    				current = grammarAccess.getXValueVarSessionAggFunctionAccess().getHighEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getXValueVarSessionAggFunctionAccess().getHighEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:7220:3: (enumLiteral_1= 'LOW' )
                    {
                    // InternalXScript.g:7220:3: (enumLiteral_1= 'LOW' )
                    // InternalXScript.g:7221:4: enumLiteral_1= 'LOW'
                    {
                    enumLiteral_1=(Token)match(input,98,FOLLOW_2); 

                    				current = grammarAccess.getXValueVarSessionAggFunctionAccess().getLowEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getXValueVarSessionAggFunctionAccess().getLowEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXValueVarSessionAggFunction"


    // $ANTLR start "ruleXStreamOperator"
    // InternalXScript.g:7231:1: ruleXStreamOperator returns [Enumerator current=null] : ( (enumLiteral_0= 'GT' ) | (enumLiteral_1= 'LT' ) | (enumLiteral_2= 'EQ' ) | (enumLiteral_3= 'NE' ) ) ;
    public final Enumerator ruleXStreamOperator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;


        	enterRule();

        try {
            // InternalXScript.g:7237:2: ( ( (enumLiteral_0= 'GT' ) | (enumLiteral_1= 'LT' ) | (enumLiteral_2= 'EQ' ) | (enumLiteral_3= 'NE' ) ) )
            // InternalXScript.g:7238:2: ( (enumLiteral_0= 'GT' ) | (enumLiteral_1= 'LT' ) | (enumLiteral_2= 'EQ' ) | (enumLiteral_3= 'NE' ) )
            {
            // InternalXScript.g:7238:2: ( (enumLiteral_0= 'GT' ) | (enumLiteral_1= 'LT' ) | (enumLiteral_2= 'EQ' ) | (enumLiteral_3= 'NE' ) )
            int alt66=4;
            switch ( input.LA(1) ) {
            case 99:
                {
                alt66=1;
                }
                break;
            case 100:
                {
                alt66=2;
                }
                break;
            case 101:
                {
                alt66=3;
                }
                break;
            case 102:
                {
                alt66=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 66, 0, input);

                throw nvae;
            }

            switch (alt66) {
                case 1 :
                    // InternalXScript.g:7239:3: (enumLiteral_0= 'GT' )
                    {
                    // InternalXScript.g:7239:3: (enumLiteral_0= 'GT' )
                    // InternalXScript.g:7240:4: enumLiteral_0= 'GT'
                    {
                    enumLiteral_0=(Token)match(input,99,FOLLOW_2); 

                    				current = grammarAccess.getXStreamOperatorAccess().getGtEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getXStreamOperatorAccess().getGtEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:7247:3: (enumLiteral_1= 'LT' )
                    {
                    // InternalXScript.g:7247:3: (enumLiteral_1= 'LT' )
                    // InternalXScript.g:7248:4: enumLiteral_1= 'LT'
                    {
                    enumLiteral_1=(Token)match(input,100,FOLLOW_2); 

                    				current = grammarAccess.getXStreamOperatorAccess().getLtEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getXStreamOperatorAccess().getLtEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:7255:3: (enumLiteral_2= 'EQ' )
                    {
                    // InternalXScript.g:7255:3: (enumLiteral_2= 'EQ' )
                    // InternalXScript.g:7256:4: enumLiteral_2= 'EQ'
                    {
                    enumLiteral_2=(Token)match(input,101,FOLLOW_2); 

                    				current = grammarAccess.getXStreamOperatorAccess().getEqEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getXStreamOperatorAccess().getEqEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalXScript.g:7263:3: (enumLiteral_3= 'NE' )
                    {
                    // InternalXScript.g:7263:3: (enumLiteral_3= 'NE' )
                    // InternalXScript.g:7264:4: enumLiteral_3= 'NE'
                    {
                    enumLiteral_3=(Token)match(input,102,FOLLOW_2); 

                    				current = grammarAccess.getXStreamOperatorAccess().getNqEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getXStreamOperatorAccess().getNqEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXStreamOperator"


    // $ANTLR start "ruleXQueryValueCompareFunction"
    // InternalXScript.g:7274:1: ruleXQueryValueCompareFunction returns [Enumerator current=null] : ( (enumLiteral_0= 'ROC' ) | (enumLiteral_1= 'DIFF' ) ) ;
    public final Enumerator ruleXQueryValueCompareFunction() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalXScript.g:7280:2: ( ( (enumLiteral_0= 'ROC' ) | (enumLiteral_1= 'DIFF' ) ) )
            // InternalXScript.g:7281:2: ( (enumLiteral_0= 'ROC' ) | (enumLiteral_1= 'DIFF' ) )
            {
            // InternalXScript.g:7281:2: ( (enumLiteral_0= 'ROC' ) | (enumLiteral_1= 'DIFF' ) )
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==103) ) {
                alt67=1;
            }
            else if ( (LA67_0==104) ) {
                alt67=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 67, 0, input);

                throw nvae;
            }
            switch (alt67) {
                case 1 :
                    // InternalXScript.g:7282:3: (enumLiteral_0= 'ROC' )
                    {
                    // InternalXScript.g:7282:3: (enumLiteral_0= 'ROC' )
                    // InternalXScript.g:7283:4: enumLiteral_0= 'ROC'
                    {
                    enumLiteral_0=(Token)match(input,103,FOLLOW_2); 

                    				current = grammarAccess.getXQueryValueCompareFunctionAccess().getRocEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getXQueryValueCompareFunctionAccess().getRocEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:7290:3: (enumLiteral_1= 'DIFF' )
                    {
                    // InternalXScript.g:7290:3: (enumLiteral_1= 'DIFF' )
                    // InternalXScript.g:7291:4: enumLiteral_1= 'DIFF'
                    {
                    enumLiteral_1=(Token)match(input,104,FOLLOW_2); 

                    				current = grammarAccess.getXQueryValueCompareFunctionAccess().getDiffEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getXQueryValueCompareFunctionAccess().getDiffEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleXQueryValueCompareFunction"

    // Delegated rules


    protected DFA15 dfa15 = new DFA15(this);
    protected DFA26 dfa26 = new DFA26(this);
    protected DFA50 dfa50 = new DFA50(this);
    static final String dfa_1s = "\22\uffff";
    static final String dfa_2s = "\1\4\12\uffff\1\42\2\uffff\1\5\1\16\2\uffff";
    static final String dfa_3s = "\1\53\12\uffff\1\42\2\uffff\1\44\1\43\2\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\uffff\1\15\1\16\2\uffff\1\14\1\13";
    static final String dfa_5s = "\22\uffff}>";
    static final String[] dfa_6s = {
            "\1\13\1\2\1\1\1\3\27\uffff\2\4\1\5\2\uffff\1\12\1\7\1\uffff\1\6\1\10\1\11\1\14\1\15",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\16",
            "",
            "",
            "\1\17\36\uffff\1\20",
            "\1\21\24\uffff\1\20",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "903:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_SessionSignalExpressionType_17= ruleSessionSignalExpressionType )";
        }
    }
    static final String dfa_7s = "\21\uffff";
    static final String dfa_8s = "\1\4\6\uffff\1\15\11\uffff";
    static final String dfa_9s = "\1\76\6\uffff\1\66\11\uffff";
    static final String dfa_10s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\1\13\1\15\1\16\1\17\1\7\1\14\1\12\1\10";
    static final String dfa_11s = "\21\uffff}>";
    static final String[] dfa_12s = {
            "\1\7\50\uffff\1\10\1\uffff\1\2\1\uffff\1\1\1\4\1\5\1\6\2\uffff\1\11\1\12\1\3\2\uffff\1\14\1\uffff\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\15\2\uffff\1\20\44\uffff\1\16\1\17",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final char[] dfa_8 = DFA.unpackEncodedStringToUnsignedChars(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final short[] dfa_10 = DFA.unpackEncodedString(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[][] dfa_12 = unpackEncodedStringArray(dfa_12s);

    class DFA26 extends DFA {

        public DFA26(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 26;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "2459:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )";
        }
    }
    static final String dfa_13s = "\24\uffff";
    static final String dfa_14s = "\7\uffff\1\21\14\uffff";
    static final String dfa_15s = "\1\4\6\uffff\1\15\14\uffff";
    static final String dfa_16s = "\1\115\6\uffff\1\43\14\uffff";
    static final String dfa_17s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\10\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\7\1\11\1\12";
    static final String dfa_18s = "\24\uffff}>";
    static final String[] dfa_19s = {
            "\1\7\1\2\1\1\1\3\27\uffff\2\4\3\uffff\1\10\5\uffff\1\6\24\uffff\1\5\1\11\5\uffff\1\12\1\uffff\1\13\1\14\1\15\1\16\1\20\1\17",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\23\2\21\1\uffff\15\21\4\uffff\1\22\1\21",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_13 = DFA.unpackEncodedString(dfa_13s);
    static final short[] dfa_14 = DFA.unpackEncodedString(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[][] dfa_19 = unpackEncodedStringArray(dfa_19s);

    class DFA50 extends DFA {

        public DFA50(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 50;
            this.eot = dfa_13;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "4445:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000600000001002L,0x0000000000080000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000000L,0x00000001FE000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000FB3C00020F0L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000300002L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000003C00002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x000000000C000002L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000030000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000001000000020L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x00000FF3C00020F0L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000004000004000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000000L,0x0000000001C00000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000003010000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000002000002000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0001804000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x80000411C00020F0L,0x0000000000003F41L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x539FA04000000010L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x80000411C00220F0L,0x0000000000003F41L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000000008080L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x80000411C000A0F0L,0x0000000000003F41L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0C00000000000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x739FA04000000010L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000006L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000038L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000B8L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000000L,0x0000000600000000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000004000000000L,0x0000000000300000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000000000000000L,0x0000000000078000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000000000000000L,0x0000007800000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000000000000000L,0x0000018000000000L});

}