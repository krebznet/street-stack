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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_DOUBLE", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'svar'", "'('", "','", "')'", "'='", "';'", "'||'", "'&&'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", "'!'", "'true'", "'false'", "'tick'", "'['", "']'", "'exp'", "'{'", "'}'", "'snapshot'", "'roc'", "'avg'", "'sub'", "'ssc'", "'in last'", "'EntityQuery'", "'LIMIT '", "'signal'", "'class'", "'var'", "'function'", "'return'", "'signalListener'", "'streamVarListener'", "'functionRunner'", "'++'", "'--'", "'setStreamVar'", "'debug'", "'if'", "'elseif'", "'else'", "'whilst'", "'break'", "'sleep'", "'percentChange'", "'columnStrk'", "'bwd'", "'fwd'", "'sum'", "'diff'", "'value'", "'columnPairStrk'", "'variance'", "'slrAvg'", "'lst'", "'stc'", "'varAvg'", "'varMax'", "'rox'", "'SEC'", "'MIN'", "'HOUR'", "'STR'", "'INT'", "'BOOl'", "'T'", "'DT'", "'DATE'", "'DUB'", "'LONG'"
    };
    public static final int T__50=50;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__59=59;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__55=55;
    public static final int T__12=12;
    public static final int T__56=56;
    public static final int T__13=13;
    public static final int T__57=57;
    public static final int T__14=14;
    public static final int T__58=58;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int T__66=66;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__67=67;
    public static final int T__24=24;
    public static final int T__68=68;
    public static final int T__25=25;
    public static final int T__69=69;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__20=20;
    public static final int T__64=64;
    public static final int T__21=21;
    public static final int T__65=65;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__37=37;
    public static final int RULE_DOUBLE=6;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__77=77;
    public static final int T__34=34;
    public static final int T__78=78;
    public static final int T__35=35;
    public static final int T__79=79;
    public static final int T__36=36;
    public static final int T__73=73;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__74=74;
    public static final int T__31=31;
    public static final int T__75=75;
    public static final int T__32=32;
    public static final int T__76=76;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__88=88;
    public static final int T__45=45;
    public static final int T__89=89;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__84=84;
    public static final int T__41=41;
    public static final int T__85=85;
    public static final int T__42=42;
    public static final int T__86=86;
    public static final int T__43=43;
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

                if ( (LA1_0==12||LA1_0==45||(LA1_0>=47 && LA1_0<=48)) ) {
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
    // InternalXScript.g:133:1: ruleCoreAbstractElement returns [EObject current=null] : (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType | this_EntityQueryType_3= ruleEntityQueryType ) ;
    public final EObject ruleCoreAbstractElement() throws RecognitionException {
        EObject current = null;

        EObject this_VarType_0 = null;

        EObject this_XClassType_1 = null;

        EObject this_SignalType_2 = null;

        EObject this_EntityQueryType_3 = null;



        	enterRule();

        try {
            // InternalXScript.g:139:2: ( (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType | this_EntityQueryType_3= ruleEntityQueryType ) )
            // InternalXScript.g:140:2: (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType | this_EntityQueryType_3= ruleEntityQueryType )
            {
            // InternalXScript.g:140:2: (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType | this_EntityQueryType_3= ruleEntityQueryType )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 12:
                {
                alt2=1;
                }
                break;
            case 48:
                {
                alt2=2;
                }
                break;
            case 47:
                {
                alt2=3;
                }
                break;
            case 45:
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
                    // InternalXScript.g:168:3: this_EntityQueryType_3= ruleEntityQueryType
                    {

                    			newCompositeNode(grammarAccess.getCoreAbstractElementAccess().getEntityQueryTypeParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_EntityQueryType_3=ruleEntityQueryType();

                    state._fsp--;


                    			current = this_EntityQueryType_3;
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


    // $ANTLR start "entryRuleEntityQueryType"
    // InternalXScript.g:2005:1: entryRuleEntityQueryType returns [EObject current=null] : iv_ruleEntityQueryType= ruleEntityQueryType EOF ;
    public final EObject entryRuleEntityQueryType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntityQueryType = null;


        try {
            // InternalXScript.g:2005:56: (iv_ruleEntityQueryType= ruleEntityQueryType EOF )
            // InternalXScript.g:2006:2: iv_ruleEntityQueryType= ruleEntityQueryType EOF
            {
             newCompositeNode(grammarAccess.getEntityQueryTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntityQueryType=ruleEntityQueryType();

            state._fsp--;

             current =iv_ruleEntityQueryType; 
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
    // $ANTLR end "entryRuleEntityQueryType"


    // $ANTLR start "ruleEntityQueryType"
    // InternalXScript.g:2012:1: ruleEntityQueryType returns [EObject current=null] : ( () otherlv_1= 'EntityQuery' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_exp_4_0= ruleExpressionType ) ) otherlv_5= ')' (otherlv_6= 'LIMIT ' ( (lv_limit_7_0= RULE_INT ) ) )? otherlv_8= ';' ) ;
    public final EObject ruleEntityQueryType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token lv_limit_7_0=null;
        Token otherlv_8=null;
        EObject lv_exp_4_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2018:2: ( ( () otherlv_1= 'EntityQuery' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_exp_4_0= ruleExpressionType ) ) otherlv_5= ')' (otherlv_6= 'LIMIT ' ( (lv_limit_7_0= RULE_INT ) ) )? otherlv_8= ';' ) )
            // InternalXScript.g:2019:2: ( () otherlv_1= 'EntityQuery' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_exp_4_0= ruleExpressionType ) ) otherlv_5= ')' (otherlv_6= 'LIMIT ' ( (lv_limit_7_0= RULE_INT ) ) )? otherlv_8= ';' )
            {
            // InternalXScript.g:2019:2: ( () otherlv_1= 'EntityQuery' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_exp_4_0= ruleExpressionType ) ) otherlv_5= ')' (otherlv_6= 'LIMIT ' ( (lv_limit_7_0= RULE_INT ) ) )? otherlv_8= ';' )
            // InternalXScript.g:2020:3: () otherlv_1= 'EntityQuery' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_exp_4_0= ruleExpressionType ) ) otherlv_5= ')' (otherlv_6= 'LIMIT ' ( (lv_limit_7_0= RULE_INT ) ) )? otherlv_8= ';'
            {
            // InternalXScript.g:2020:3: ()
            // InternalXScript.g:2021:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getEntityQueryTypeAccess().getEntityQueryTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,45,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getEntityQueryTypeAccess().getEntityQueryKeyword_1());
            		
            // InternalXScript.g:2031:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2032:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2032:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2033:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_2_0, grammarAccess.getEntityQueryTypeAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEntityQueryTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_3, grammarAccess.getEntityQueryTypeAccess().getLeftParenthesisKeyword_3());
            		
            // InternalXScript.g:2053:3: ( (lv_exp_4_0= ruleExpressionType ) )
            // InternalXScript.g:2054:4: (lv_exp_4_0= ruleExpressionType )
            {
            // InternalXScript.g:2054:4: (lv_exp_4_0= ruleExpressionType )
            // InternalXScript.g:2055:5: lv_exp_4_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getEntityQueryTypeAccess().getExpExpressionTypeParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_9);
            lv_exp_4_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEntityQueryTypeRule());
            					}
            					set(
            						current,
            						"exp",
            						lv_exp_4_0,
            						"com.dunkware.xstream.XScript.ExpressionType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,15,FOLLOW_28); 

            			newLeafNode(otherlv_5, grammarAccess.getEntityQueryTypeAccess().getRightParenthesisKeyword_5());
            		
            // InternalXScript.g:2076:3: (otherlv_6= 'LIMIT ' ( (lv_limit_7_0= RULE_INT ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==46) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalXScript.g:2077:4: otherlv_6= 'LIMIT ' ( (lv_limit_7_0= RULE_INT ) )
                    {
                    otherlv_6=(Token)match(input,46,FOLLOW_6); 

                    				newLeafNode(otherlv_6, grammarAccess.getEntityQueryTypeAccess().getLIMITKeyword_6_0());
                    			
                    // InternalXScript.g:2081:4: ( (lv_limit_7_0= RULE_INT ) )
                    // InternalXScript.g:2082:5: (lv_limit_7_0= RULE_INT )
                    {
                    // InternalXScript.g:2082:5: (lv_limit_7_0= RULE_INT )
                    // InternalXScript.g:2083:6: lv_limit_7_0= RULE_INT
                    {
                    lv_limit_7_0=(Token)match(input,RULE_INT,FOLLOW_12); 

                    						newLeafNode(lv_limit_7_0, grammarAccess.getEntityQueryTypeAccess().getLimitINTTerminalRuleCall_6_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getEntityQueryTypeRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"limit",
                    							lv_limit_7_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,17,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getEntityQueryTypeAccess().getSemicolonKeyword_7());
            		

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
    // $ANTLR end "ruleEntityQueryType"


    // $ANTLR start "entryRuleSignalType"
    // InternalXScript.g:2108:1: entryRuleSignalType returns [EObject current=null] : iv_ruleSignalType= ruleSignalType EOF ;
    public final EObject entryRuleSignalType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignalType = null;


        try {
            // InternalXScript.g:2108:51: (iv_ruleSignalType= ruleSignalType EOF )
            // InternalXScript.g:2109:2: iv_ruleSignalType= ruleSignalType EOF
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
    // InternalXScript.g:2115:1: ruleSignalType returns [EObject current=null] : ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' ) ;
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
            // InternalXScript.g:2121:2: ( ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' ) )
            // InternalXScript.g:2122:2: ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' )
            {
            // InternalXScript.g:2122:2: ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' )
            // InternalXScript.g:2123:3: () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';'
            {
            // InternalXScript.g:2123:3: ()
            // InternalXScript.g:2124:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSignalTypeAccess().getSignalTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,47,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getSignalTypeAccess().getSignalKeyword_1());
            		
            // InternalXScript.g:2134:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2135:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2135:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2136:5: lv_name_2_0= RULE_ID
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
            		
            // InternalXScript.g:2156:3: ( (lv_id_4_0= RULE_INT ) )
            // InternalXScript.g:2157:4: (lv_id_4_0= RULE_INT )
            {
            // InternalXScript.g:2157:4: (lv_id_4_0= RULE_INT )
            // InternalXScript.g:2158:5: lv_id_4_0= RULE_INT
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
    // InternalXScript.g:2186:1: entryRuleXClassType returns [EObject current=null] : iv_ruleXClassType= ruleXClassType EOF ;
    public final EObject entryRuleXClassType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassType = null;


        try {
            // InternalXScript.g:2186:51: (iv_ruleXClassType= ruleXClassType EOF )
            // InternalXScript.g:2187:2: iv_ruleXClassType= ruleXClassType EOF
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
    // InternalXScript.g:2193:1: ruleXClassType returns [EObject current=null] : ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' ) ;
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
            // InternalXScript.g:2199:2: ( ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' ) )
            // InternalXScript.g:2200:2: ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' )
            {
            // InternalXScript.g:2200:2: ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' )
            // InternalXScript.g:2201:3: () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}'
            {
            // InternalXScript.g:2201:3: ()
            // InternalXScript.g:2202:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXClassTypeAccess().getXClassTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,48,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXClassTypeAccess().getClassKeyword_1());
            		
            // InternalXScript.g:2212:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2213:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2213:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2214:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_29); 

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

            // InternalXScript.g:2230:3: (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==13) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalXScript.g:2231:4: otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_30); 

                    				newLeafNode(otherlv_3, grammarAccess.getXClassTypeAccess().getLeftParenthesisKeyword_3_0());
                    			
                    // InternalXScript.g:2235:4: ( (lv_symbolFilter_4_0= RULE_STRING ) )
                    // InternalXScript.g:2236:5: (lv_symbolFilter_4_0= RULE_STRING )
                    {
                    // InternalXScript.g:2236:5: (lv_symbolFilter_4_0= RULE_STRING )
                    // InternalXScript.g:2237:6: lv_symbolFilter_4_0= RULE_STRING
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

            otherlv_6=(Token)match(input,37,FOLLOW_31); 

            			newLeafNode(otherlv_6, grammarAccess.getXClassTypeAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalXScript.g:2262:3: ( (lv_elements_7_0= ruleXClassElementType ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=49 && LA22_0<=50)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalXScript.g:2263:4: (lv_elements_7_0= ruleXClassElementType )
            	    {
            	    // InternalXScript.g:2263:4: (lv_elements_7_0= ruleXClassElementType )
            	    // InternalXScript.g:2264:5: lv_elements_7_0= ruleXClassElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXClassTypeAccess().getElementsXClassElementTypeParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_31);
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
            	    break loop22;
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
    // InternalXScript.g:2289:1: entryRuleXClassElementType returns [EObject current=null] : iv_ruleXClassElementType= ruleXClassElementType EOF ;
    public final EObject entryRuleXClassElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassElementType = null;


        try {
            // InternalXScript.g:2289:58: (iv_ruleXClassElementType= ruleXClassElementType EOF )
            // InternalXScript.g:2290:2: iv_ruleXClassElementType= ruleXClassElementType EOF
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
    // InternalXScript.g:2296:1: ruleXClassElementType returns [EObject current=null] : this_XClassCoreElementType_0= ruleXClassCoreElementType ;
    public final EObject ruleXClassElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XClassCoreElementType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2302:2: (this_XClassCoreElementType_0= ruleXClassCoreElementType )
            // InternalXScript.g:2303:2: this_XClassCoreElementType_0= ruleXClassCoreElementType
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
    // InternalXScript.g:2314:1: entryRuleXClassCoreElementType returns [EObject current=null] : iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF ;
    public final EObject entryRuleXClassCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassCoreElementType = null;


        try {
            // InternalXScript.g:2314:62: (iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF )
            // InternalXScript.g:2315:2: iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF
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
    // InternalXScript.g:2321:1: ruleXClassCoreElementType returns [EObject current=null] : (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType ) ;
    public final EObject ruleXClassCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XFunctionType_0 = null;

        EObject this_XVarType_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:2327:2: ( (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType ) )
            // InternalXScript.g:2328:2: (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType )
            {
            // InternalXScript.g:2328:2: (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==50) ) {
                alt23=1;
            }
            else if ( (LA23_0==49) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // InternalXScript.g:2329:3: this_XFunctionType_0= ruleXFunctionType
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
                    // InternalXScript.g:2338:3: this_XVarType_1= ruleXVarType
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
    // InternalXScript.g:2350:1: entryRuleXVarType returns [EObject current=null] : iv_ruleXVarType= ruleXVarType EOF ;
    public final EObject entryRuleXVarType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarType = null;


        try {
            // InternalXScript.g:2350:49: (iv_ruleXVarType= ruleXVarType EOF )
            // InternalXScript.g:2351:2: iv_ruleXVarType= ruleXVarType EOF
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
    // InternalXScript.g:2357:1: ruleXVarType returns [EObject current=null] : ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' ) ;
    public final EObject ruleXVarType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_exp_4_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2363:2: ( ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' ) )
            // InternalXScript.g:2364:2: ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' )
            {
            // InternalXScript.g:2364:2: ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' )
            // InternalXScript.g:2365:3: () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';'
            {
            // InternalXScript.g:2365:3: ()
            // InternalXScript.g:2366:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarTypeAccess().getXVarTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,49,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarTypeAccess().getVarKeyword_1());
            		
            // InternalXScript.g:2376:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2377:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2377:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2378:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_32); 

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

            // InternalXScript.g:2394:3: (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==16) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalXScript.g:2395:4: otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) )
                    {
                    otherlv_3=(Token)match(input,16,FOLLOW_33); 

                    				newLeafNode(otherlv_3, grammarAccess.getXVarTypeAccess().getEqualsSignKeyword_3_0());
                    			
                    // InternalXScript.g:2399:4: ( (lv_exp_4_0= ruleXExpressionType ) )
                    // InternalXScript.g:2400:5: (lv_exp_4_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:2400:5: (lv_exp_4_0= ruleXExpressionType )
                    // InternalXScript.g:2401:6: lv_exp_4_0= ruleXExpressionType
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
    // InternalXScript.g:2427:1: entryRuleXFunctionType returns [EObject current=null] : iv_ruleXFunctionType= ruleXFunctionType EOF ;
    public final EObject entryRuleXFunctionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionType = null;


        try {
            // InternalXScript.g:2427:54: (iv_ruleXFunctionType= ruleXFunctionType EOF )
            // InternalXScript.g:2428:2: iv_ruleXFunctionType= ruleXFunctionType EOF
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
    // InternalXScript.g:2434:1: ruleXFunctionType returns [EObject current=null] : ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' ) ;
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
            // InternalXScript.g:2440:2: ( ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' ) )
            // InternalXScript.g:2441:2: ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' )
            {
            // InternalXScript.g:2441:2: ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' )
            // InternalXScript.g:2442:3: () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}'
            {
            // InternalXScript.g:2442:3: ()
            // InternalXScript.g:2443:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionTypeAccess().getXFunctionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,50,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionTypeAccess().getFunctionKeyword_1());
            		
            // InternalXScript.g:2453:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2454:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2454:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2455:5: lv_name_2_0= RULE_ID
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

            otherlv_3=(Token)match(input,13,FOLLOW_34); 

            			newLeafNode(otherlv_3, grammarAccess.getXFunctionTypeAccess().getLeftParenthesisKeyword_3());
            		
            // InternalXScript.g:2475:3: ( (otherlv_4= RULE_ID ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_ID) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalXScript.g:2476:4: (otherlv_4= RULE_ID )
            	    {
            	    // InternalXScript.g:2476:4: (otherlv_4= RULE_ID )
            	    // InternalXScript.g:2477:5: otherlv_4= RULE_ID
            	    {

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getXFunctionTypeRule());
            	    					}
            	    				
            	    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_34); 

            	    					newLeafNode(otherlv_4, grammarAccess.getXFunctionTypeAccess().getParamsXVarTypeCrossReference_4_0());
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

            otherlv_5=(Token)match(input,15,FOLLOW_25); 

            			newLeafNode(otherlv_5, grammarAccess.getXFunctionTypeAccess().getRightParenthesisKeyword_5());
            		
            otherlv_6=(Token)match(input,37,FOLLOW_35); 

            			newLeafNode(otherlv_6, grammarAccess.getXFunctionTypeAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalXScript.g:2496:3: ( (lv_elements_7_0= ruleXClassFunctionElementType ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID||LA26_0==47||LA26_0==49||(LA26_0>=51 && LA26_0<=54)||(LA26_0>=57 && LA26_0<=59)||LA26_0==62||LA26_0==64) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalXScript.g:2497:4: (lv_elements_7_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:2497:4: (lv_elements_7_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:2498:5: lv_elements_7_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXFunctionTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_35);
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
            	    break loop26;
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
    // InternalXScript.g:2523:1: entryRuleXClassFunctionElementType returns [EObject current=null] : iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF ;
    public final EObject entryRuleXClassFunctionElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassFunctionElementType = null;


        try {
            // InternalXScript.g:2523:66: (iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF )
            // InternalXScript.g:2524:2: iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF
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
    // InternalXScript.g:2530:1: ruleXClassFunctionElementType returns [EObject current=null] : this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType ;
    public final EObject ruleXClassFunctionElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XFunctionCoreElementType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2536:2: (this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType )
            // InternalXScript.g:2537:2: this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType
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
    // InternalXScript.g:2548:1: entryRuleXFunctionCoreElementType returns [EObject current=null] : iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF ;
    public final EObject entryRuleXFunctionCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCoreElementType = null;


        try {
            // InternalXScript.g:2548:65: (iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF )
            // InternalXScript.g:2549:2: iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF
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
    // InternalXScript.g:2555:1: ruleXFunctionCoreElementType returns [EObject current=null] : (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType ) ;
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
            // InternalXScript.g:2561:2: ( (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType ) )
            // InternalXScript.g:2562:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )
            {
            // InternalXScript.g:2562:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )
            int alt27=15;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // InternalXScript.g:2563:3: this_XFunctionReturnType_0= ruleXFunctionReturnType
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
                    // InternalXScript.g:2572:3: this_XVarType_1= ruleXVarType
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
                    // InternalXScript.g:2581:3: this_XIfStatementType_2= ruleXIfStatementType
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
                    // InternalXScript.g:2590:3: this_XSignalListenerType_3= ruleXSignalListenerType
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
                    // InternalXScript.g:2599:3: this_XStreamVarListenerType_4= ruleXStreamVarListenerType
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
                    // InternalXScript.g:2608:3: this_XFunctionStartType_5= ruleXFunctionStartType
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
                    // InternalXScript.g:2617:3: this_XFunctionCallType_6= ruleXFunctionCallType
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
                    // InternalXScript.g:2626:3: this_XVarSetterType_7= ruleXVarSetterType
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
                    // InternalXScript.g:2635:3: this_XSignalTriggerType_8= ruleXSignalTriggerType
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
                    // InternalXScript.g:2644:3: this_XVarDecrementType_9= ruleXVarDecrementType
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
                    // InternalXScript.g:2653:3: this_XSetVarType_10= ruleXSetVarType
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
                    // InternalXScript.g:2662:3: this_XVarIncrementType_11= ruleXVarIncrementType
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
                    // InternalXScript.g:2671:3: this_XDebugType_12= ruleXDebugType
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
                    // InternalXScript.g:2680:3: this_XSleepType_13= ruleXSleepType
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
                    // InternalXScript.g:2689:3: this_XWhileType_14= ruleXWhileType
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
    // InternalXScript.g:2701:1: entryRuleXFunctionReturnType returns [EObject current=null] : iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF ;
    public final EObject entryRuleXFunctionReturnType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionReturnType = null;


        try {
            // InternalXScript.g:2701:60: (iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF )
            // InternalXScript.g:2702:2: iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF
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
    // InternalXScript.g:2708:1: ruleXFunctionReturnType returns [EObject current=null] : ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' ) ;
    public final EObject ruleXFunctionReturnType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_returnValue_2_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2714:2: ( ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' ) )
            // InternalXScript.g:2715:2: ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' )
            {
            // InternalXScript.g:2715:2: ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' )
            // InternalXScript.g:2716:3: () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';'
            {
            // InternalXScript.g:2716:3: ()
            // InternalXScript.g:2717:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionReturnTypeAccess().getXFunctionReturnTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,51,FOLLOW_36); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionReturnTypeAccess().getReturnKeyword_1());
            		
            // InternalXScript.g:2727:3: ( (lv_returnValue_2_0= ruleXExpressionType ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=RULE_ID && LA28_0<=RULE_STRING)||LA28_0==13||(LA28_0>=30 && LA28_0<=32)||LA28_0==36||LA28_0==42||(LA28_0>=65 && LA28_0<=66)||LA28_0==72||(LA28_0>=74 && LA28_0<=79)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalXScript.g:2728:4: (lv_returnValue_2_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:2728:4: (lv_returnValue_2_0= ruleXExpressionType )
                    // InternalXScript.g:2729:5: lv_returnValue_2_0= ruleXExpressionType
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
    // InternalXScript.g:2754:1: entryRuleXFunctionCallType returns [EObject current=null] : iv_ruleXFunctionCallType= ruleXFunctionCallType EOF ;
    public final EObject entryRuleXFunctionCallType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCallType = null;


        try {
            // InternalXScript.g:2754:58: (iv_ruleXFunctionCallType= ruleXFunctionCallType EOF )
            // InternalXScript.g:2755:2: iv_ruleXFunctionCallType= ruleXFunctionCallType EOF
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
    // InternalXScript.g:2761:1: ruleXFunctionCallType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' ) ;
    public final EObject ruleXFunctionCallType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_params_3_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalXScript.g:2767:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' ) )
            // InternalXScript.g:2768:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' )
            {
            // InternalXScript.g:2768:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' )
            // InternalXScript.g:2769:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';'
            {
            // InternalXScript.g:2769:3: ()
            // InternalXScript.g:2770:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionCallTypeAccess().getXFunctionCallTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:2776:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:2777:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:2777:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:2778:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionCallTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(otherlv_1, grammarAccess.getXFunctionCallTypeAccess().getFunctionXFunctionTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_37); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionCallTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2793:3: ( (lv_params_3_0= RULE_STRING ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==RULE_STRING) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalXScript.g:2794:4: (lv_params_3_0= RULE_STRING )
                    {
                    // InternalXScript.g:2794:4: (lv_params_3_0= RULE_STRING )
                    // InternalXScript.g:2795:5: lv_params_3_0= RULE_STRING
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
    // InternalXScript.g:2823:1: entryRuleXSignalListenerType returns [EObject current=null] : iv_ruleXSignalListenerType= ruleXSignalListenerType EOF ;
    public final EObject entryRuleXSignalListenerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalListenerType = null;


        try {
            // InternalXScript.g:2823:60: (iv_ruleXSignalListenerType= ruleXSignalListenerType EOF )
            // InternalXScript.g:2824:2: iv_ruleXSignalListenerType= ruleXSignalListenerType EOF
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
    // InternalXScript.g:2830:1: ruleXSignalListenerType returns [EObject current=null] : ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) ;
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
            // InternalXScript.g:2836:2: ( ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:2837:2: ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:2837:2: ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:2838:3: () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:2838:3: ()
            // InternalXScript.g:2839:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalListenerTypeAccess().getXSignalListenerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,52,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalListenerTypeAccess().getSignalListenerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalListenerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2853:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2854:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2854:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2855:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:2870:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:2871:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:2871:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:2872:5: otherlv_5= RULE_ID
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
    // InternalXScript.g:2895:1: entryRuleXStreamVarListenerType returns [EObject current=null] : iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF ;
    public final EObject entryRuleXStreamVarListenerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamVarListenerType = null;


        try {
            // InternalXScript.g:2895:63: (iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF )
            // InternalXScript.g:2896:2: iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF
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
    // InternalXScript.g:2902:1: ruleXStreamVarListenerType returns [EObject current=null] : ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) ;
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
            // InternalXScript.g:2908:2: ( ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:2909:2: ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:2909:2: ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:2910:3: () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:2910:3: ()
            // InternalXScript.g:2911:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamVarListenerTypeAccess().getXStreamVarListenerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,53,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXStreamVarListenerTypeAccess().getStreamVarListenerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXStreamVarListenerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2925:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2926:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2926:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2927:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:2942:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:2943:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:2943:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:2944:5: otherlv_5= RULE_ID
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
    // InternalXScript.g:2967:1: entryRuleXSignalTriggerType returns [EObject current=null] : iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF ;
    public final EObject entryRuleXSignalTriggerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalTriggerType = null;


        try {
            // InternalXScript.g:2967:59: (iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF )
            // InternalXScript.g:2968:2: iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF
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
    // InternalXScript.g:2974:1: ruleXSignalTriggerType returns [EObject current=null] : ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' ) ;
    public final EObject ruleXSignalTriggerType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalXScript.g:2980:2: ( ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' ) )
            // InternalXScript.g:2981:2: ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' )
            {
            // InternalXScript.g:2981:2: ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' )
            // InternalXScript.g:2982:3: () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';'
            {
            // InternalXScript.g:2982:3: ()
            // InternalXScript.g:2983:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalTriggerTypeAccess().getXSignalTriggerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,47,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalTriggerTypeAccess().getSignalKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalTriggerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2997:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2998:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2998:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2999:5: otherlv_3= RULE_ID
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
    // InternalXScript.g:3022:1: entryRuleXFunctionStartType returns [EObject current=null] : iv_ruleXFunctionStartType= ruleXFunctionStartType EOF ;
    public final EObject entryRuleXFunctionStartType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionStartType = null;


        try {
            // InternalXScript.g:3022:59: (iv_ruleXFunctionStartType= ruleXFunctionStartType EOF )
            // InternalXScript.g:3023:2: iv_ruleXFunctionStartType= ruleXFunctionStartType EOF
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
    // InternalXScript.g:3029:1: ruleXFunctionStartType returns [EObject current=null] : ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' ) ;
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
            // InternalXScript.g:3035:2: ( ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' ) )
            // InternalXScript.g:3036:2: ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' )
            {
            // InternalXScript.g:3036:2: ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' )
            // InternalXScript.g:3037:3: () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';'
            {
            // InternalXScript.g:3037:3: ()
            // InternalXScript.g:3038:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionStartTypeAccess().getXFunctionStartTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,54,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionStartTypeAccess().getFunctionRunnerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionStartTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3052:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:3053:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:3053:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:3054:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:3069:3: ( (lv_interval_5_0= RULE_INT ) )
            // InternalXScript.g:3070:4: (lv_interval_5_0= RULE_INT )
            {
            // InternalXScript.g:3070:4: (lv_interval_5_0= RULE_INT )
            // InternalXScript.g:3071:5: lv_interval_5_0= RULE_INT
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

            // InternalXScript.g:3087:3: ( (lv_time_6_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:3088:4: (lv_time_6_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:3088:4: (lv_time_6_0= ruleStreamTimeUnit )
            // InternalXScript.g:3089:5: lv_time_6_0= ruleStreamTimeUnit
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
    // InternalXScript.g:3118:1: entryRuleXVarSetterType returns [EObject current=null] : iv_ruleXVarSetterType= ruleXVarSetterType EOF ;
    public final EObject entryRuleXVarSetterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarSetterType = null;


        try {
            // InternalXScript.g:3118:55: (iv_ruleXVarSetterType= ruleXVarSetterType EOF )
            // InternalXScript.g:3119:2: iv_ruleXVarSetterType= ruleXVarSetterType EOF
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
    // InternalXScript.g:3125:1: ruleXVarSetterType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' ) ;
    public final EObject ruleXVarSetterType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_exp_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3131:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' ) )
            // InternalXScript.g:3132:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' )
            {
            // InternalXScript.g:3132:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' )
            // InternalXScript.g:3133:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';'
            {
            // InternalXScript.g:3133:3: ()
            // InternalXScript.g:3134:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarSetterTypeAccess().getXVarSetterTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3140:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3141:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3141:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3142:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarSetterTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarSetterTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_33); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarSetterTypeAccess().getEqualsSignKeyword_2());
            		
            // InternalXScript.g:3157:3: ( (lv_exp_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3158:4: (lv_exp_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3158:4: (lv_exp_3_0= ruleXExpressionType )
            // InternalXScript.g:3159:5: lv_exp_3_0= ruleXExpressionType
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
    // InternalXScript.g:3184:1: entryRuleXVarIncrementType returns [EObject current=null] : iv_ruleXVarIncrementType= ruleXVarIncrementType EOF ;
    public final EObject entryRuleXVarIncrementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarIncrementType = null;


        try {
            // InternalXScript.g:3184:58: (iv_ruleXVarIncrementType= ruleXVarIncrementType EOF )
            // InternalXScript.g:3185:2: iv_ruleXVarIncrementType= ruleXVarIncrementType EOF
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
    // InternalXScript.g:3191:1: ruleXVarIncrementType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' ) ;
    public final EObject ruleXVarIncrementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalXScript.g:3197:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' ) )
            // InternalXScript.g:3198:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' )
            {
            // InternalXScript.g:3198:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' )
            // InternalXScript.g:3199:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';'
            {
            // InternalXScript.g:3199:3: ()
            // InternalXScript.g:3200:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarIncrementTypeAccess().getXVarIncrementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3206:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3207:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3207:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3208:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarIncrementTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_38); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarIncrementTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,55,FOLLOW_12); 

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
    // InternalXScript.g:3231:1: entryRuleXVarDecrementType returns [EObject current=null] : iv_ruleXVarDecrementType= ruleXVarDecrementType EOF ;
    public final EObject entryRuleXVarDecrementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarDecrementType = null;


        try {
            // InternalXScript.g:3231:58: (iv_ruleXVarDecrementType= ruleXVarDecrementType EOF )
            // InternalXScript.g:3232:2: iv_ruleXVarDecrementType= ruleXVarDecrementType EOF
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
    // InternalXScript.g:3238:1: ruleXVarDecrementType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' ) ;
    public final EObject ruleXVarDecrementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalXScript.g:3244:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' ) )
            // InternalXScript.g:3245:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' )
            {
            // InternalXScript.g:3245:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' )
            // InternalXScript.g:3246:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';'
            {
            // InternalXScript.g:3246:3: ()
            // InternalXScript.g:3247:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarDecrementTypeAccess().getXVarDecrementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3253:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3254:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3254:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3255:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarDecrementTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_39); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarDecrementTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,56,FOLLOW_12); 

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
    // InternalXScript.g:3278:1: entryRuleXSetVarType returns [EObject current=null] : iv_ruleXSetVarType= ruleXSetVarType EOF ;
    public final EObject entryRuleXSetVarType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSetVarType = null;


        try {
            // InternalXScript.g:3278:52: (iv_ruleXSetVarType= ruleXSetVarType EOF )
            // InternalXScript.g:3279:2: iv_ruleXSetVarType= ruleXSetVarType EOF
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
    // InternalXScript.g:3285:1: ruleXSetVarType returns [EObject current=null] : ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' ) ;
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
            // InternalXScript.g:3291:2: ( ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:3292:2: ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:3292:2: ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:3293:3: () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:3293:3: ()
            // InternalXScript.g:3294:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSetVarTypeAccess().getXSetVarTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,57,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSetVarTypeAccess().getSetStreamVarKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSetVarTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3308:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:3309:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:3309:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:3310:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSetVarTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXSetVarTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_4, grammarAccess.getXSetVarTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:3325:3: ( (lv_value_5_0= ruleXExpressionType ) )
            // InternalXScript.g:3326:4: (lv_value_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:3326:4: (lv_value_5_0= ruleXExpressionType )
            // InternalXScript.g:3327:5: lv_value_5_0= ruleXExpressionType
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
    // InternalXScript.g:3356:1: entryRuleXDebugType returns [EObject current=null] : iv_ruleXDebugType= ruleXDebugType EOF ;
    public final EObject entryRuleXDebugType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXDebugType = null;


        try {
            // InternalXScript.g:3356:51: (iv_ruleXDebugType= ruleXDebugType EOF )
            // InternalXScript.g:3357:2: iv_ruleXDebugType= ruleXDebugType EOF
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
    // InternalXScript.g:3363:1: ruleXDebugType returns [EObject current=null] : ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' ) ;
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
            // InternalXScript.g:3369:2: ( ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:3370:2: ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:3370:2: ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:3371:3: () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:3371:3: ()
            // InternalXScript.g:3372:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXDebugTypeAccess().getXDebugTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,58,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXDebugTypeAccess().getDebugKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_40); 

            			newLeafNode(otherlv_2, grammarAccess.getXDebugTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3386:3: ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_ID && LA31_0<=RULE_STRING)||LA31_0==13||(LA31_0>=30 && LA31_0<=32)||LA31_0==36||LA31_0==42||(LA31_0>=65 && LA31_0<=66)||LA31_0==72||(LA31_0>=74 && LA31_0<=79)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalXScript.g:3387:4: ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )*
                    {
                    // InternalXScript.g:3387:4: ( (lv_args_3_0= ruleXExpressionType ) )
                    // InternalXScript.g:3388:5: (lv_args_3_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:3388:5: (lv_args_3_0= ruleXExpressionType )
                    // InternalXScript.g:3389:6: lv_args_3_0= ruleXExpressionType
                    {

                    						newCompositeNode(grammarAccess.getXDebugTypeAccess().getArgsXExpressionTypeParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_41);
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

                    // InternalXScript.g:3406:4: (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==14) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // InternalXScript.g:3407:5: otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) )
                    	    {
                    	    otherlv_4=(Token)match(input,14,FOLLOW_33); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getXDebugTypeAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalXScript.g:3411:5: ( (lv_args_5_0= ruleXExpressionType ) )
                    	    // InternalXScript.g:3412:6: (lv_args_5_0= ruleXExpressionType )
                    	    {
                    	    // InternalXScript.g:3412:6: (lv_args_5_0= ruleXExpressionType )
                    	    // InternalXScript.g:3413:7: lv_args_5_0= ruleXExpressionType
                    	    {

                    	    							newCompositeNode(grammarAccess.getXDebugTypeAccess().getArgsXExpressionTypeParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_41);
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
                    	    break loop30;
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
    // InternalXScript.g:3444:1: entryRuleXIfStatementType returns [EObject current=null] : iv_ruleXIfStatementType= ruleXIfStatementType EOF ;
    public final EObject entryRuleXIfStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXIfStatementType = null;


        try {
            // InternalXScript.g:3444:57: (iv_ruleXIfStatementType= ruleXIfStatementType EOF )
            // InternalXScript.g:3445:2: iv_ruleXIfStatementType= ruleXIfStatementType EOF
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
    // InternalXScript.g:3451:1: ruleXIfStatementType returns [EObject current=null] : ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? ) ;
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
            // InternalXScript.g:3457:2: ( ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? ) )
            // InternalXScript.g:3458:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? )
            {
            // InternalXScript.g:3458:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? )
            // InternalXScript.g:3459:3: () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )?
            {
            // InternalXScript.g:3459:3: ()
            // InternalXScript.g:3460:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXIfStatementTypeAccess().getXIfStatementTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,59,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXIfStatementTypeAccess().getIfKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_33); 

            			newLeafNode(otherlv_2, grammarAccess.getXIfStatementTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3474:3: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3475:4: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3475:4: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:3476:5: lv_expression_3_0= ruleXExpressionType
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
            		
            otherlv_5=(Token)match(input,37,FOLLOW_35); 

            			newLeafNode(otherlv_5, grammarAccess.getXIfStatementTypeAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalXScript.g:3501:3: ( (lv_elements_6_0= ruleXClassFunctionElementType ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==RULE_ID||LA32_0==47||LA32_0==49||(LA32_0>=51 && LA32_0<=54)||(LA32_0>=57 && LA32_0<=59)||LA32_0==62||LA32_0==64) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalXScript.g:3502:4: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:3502:4: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:3503:5: lv_elements_6_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXIfStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_35);
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
            	    break loop32;
                }
            } while (true);

            otherlv_7=(Token)match(input,38,FOLLOW_42); 

            			newLeafNode(otherlv_7, grammarAccess.getXIfStatementTypeAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalXScript.g:3524:3: ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==60) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalXScript.g:3525:4: (lv_elseIfElements_8_0= ruleXElseIfStatementType )
            	    {
            	    // InternalXScript.g:3525:4: (lv_elseIfElements_8_0= ruleXElseIfStatementType )
            	    // InternalXScript.g:3526:5: lv_elseIfElements_8_0= ruleXElseIfStatementType
            	    {

            	    					newCompositeNode(grammarAccess.getXIfStatementTypeAccess().getElseIfElementsXElseIfStatementTypeParserRuleCall_8_0());
            	    				
            	    pushFollow(FOLLOW_42);
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
            	    break loop33;
                }
            } while (true);

            // InternalXScript.g:3543:3: ( (lv_elseElement_9_0= ruleXElseStatementType ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==61) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalXScript.g:3544:4: (lv_elseElement_9_0= ruleXElseStatementType )
                    {
                    // InternalXScript.g:3544:4: (lv_elseElement_9_0= ruleXElseStatementType )
                    // InternalXScript.g:3545:5: lv_elseElement_9_0= ruleXElseStatementType
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
    // InternalXScript.g:3566:1: entryRuleXElseIfStatementType returns [EObject current=null] : iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF ;
    public final EObject entryRuleXElseIfStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXElseIfStatementType = null;


        try {
            // InternalXScript.g:3566:61: (iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF )
            // InternalXScript.g:3567:2: iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF
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
    // InternalXScript.g:3573:1: ruleXElseIfStatementType returns [EObject current=null] : ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) ) ;
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
            // InternalXScript.g:3579:2: ( ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) ) )
            // InternalXScript.g:3580:2: ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) )
            {
            // InternalXScript.g:3580:2: ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) )
            // InternalXScript.g:3581:3: () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' )
            {
            // InternalXScript.g:3581:3: ()
            // InternalXScript.g:3582:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXElseIfStatementTypeAccess().getXElseIfStatementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3588:3: (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' )
            // InternalXScript.g:3589:4: otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}'
            {
            otherlv_1=(Token)match(input,60,FOLLOW_5); 

            				newLeafNode(otherlv_1, grammarAccess.getXElseIfStatementTypeAccess().getElseifKeyword_1_0());
            			
            otherlv_2=(Token)match(input,13,FOLLOW_33); 

            				newLeafNode(otherlv_2, grammarAccess.getXElseIfStatementTypeAccess().getLeftParenthesisKeyword_1_1());
            			
            // InternalXScript.g:3597:4: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3598:5: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3598:5: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:3599:6: lv_expression_3_0= ruleXExpressionType
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
            			
            otherlv_5=(Token)match(input,37,FOLLOW_35); 

            				newLeafNode(otherlv_5, grammarAccess.getXElseIfStatementTypeAccess().getLeftCurlyBracketKeyword_1_4());
            			
            // InternalXScript.g:3624:4: ( (lv_elements_6_0= ruleXClassFunctionElementType ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_ID||LA35_0==47||LA35_0==49||(LA35_0>=51 && LA35_0<=54)||(LA35_0>=57 && LA35_0<=59)||LA35_0==62||LA35_0==64) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalXScript.g:3625:5: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:3625:5: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:3626:6: lv_elements_6_0= ruleXClassFunctionElementType
            	    {

            	    						newCompositeNode(grammarAccess.getXElseIfStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_1_5_0());
            	    					
            	    pushFollow(FOLLOW_35);
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
            	    break loop35;
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
    // InternalXScript.g:3652:1: entryRuleXElseStatementType returns [EObject current=null] : iv_ruleXElseStatementType= ruleXElseStatementType EOF ;
    public final EObject entryRuleXElseStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXElseStatementType = null;


        try {
            // InternalXScript.g:3652:59: (iv_ruleXElseStatementType= ruleXElseStatementType EOF )
            // InternalXScript.g:3653:2: iv_ruleXElseStatementType= ruleXElseStatementType EOF
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
    // InternalXScript.g:3659:1: ruleXElseStatementType returns [EObject current=null] : ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' ) ;
    public final EObject ruleXElseStatementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3665:2: ( ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' ) )
            // InternalXScript.g:3666:2: ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' )
            {
            // InternalXScript.g:3666:2: ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' )
            // InternalXScript.g:3667:3: () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}'
            {
            // InternalXScript.g:3667:3: ()
            // InternalXScript.g:3668:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXElseStatementTypeAccess().getXElseStatementTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,61,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getXElseStatementTypeAccess().getElseKeyword_1());
            		
            otherlv_2=(Token)match(input,37,FOLLOW_35); 

            			newLeafNode(otherlv_2, grammarAccess.getXElseStatementTypeAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalXScript.g:3682:3: ( (lv_elements_3_0= ruleXClassFunctionElementType ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_ID||LA36_0==47||LA36_0==49||(LA36_0>=51 && LA36_0<=54)||(LA36_0>=57 && LA36_0<=59)||LA36_0==62||LA36_0==64) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalXScript.g:3683:4: (lv_elements_3_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:3683:4: (lv_elements_3_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:3684:5: lv_elements_3_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXElseStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_35);
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
            	    break loop36;
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
    // InternalXScript.g:3709:1: entryRuleXWhileType returns [EObject current=null] : iv_ruleXWhileType= ruleXWhileType EOF ;
    public final EObject entryRuleXWhileType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileType = null;


        try {
            // InternalXScript.g:3709:51: (iv_ruleXWhileType= ruleXWhileType EOF )
            // InternalXScript.g:3710:2: iv_ruleXWhileType= ruleXWhileType EOF
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
    // InternalXScript.g:3716:1: ruleXWhileType returns [EObject current=null] : ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' ) ;
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
            // InternalXScript.g:3722:2: ( ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' ) )
            // InternalXScript.g:3723:2: ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' )
            {
            // InternalXScript.g:3723:2: ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' )
            // InternalXScript.g:3724:3: () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}'
            {
            // InternalXScript.g:3724:3: ()
            // InternalXScript.g:3725:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXWhileTypeAccess().getXWhileTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,62,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXWhileTypeAccess().getWhilstKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_33); 

            			newLeafNode(otherlv_2, grammarAccess.getXWhileTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3739:3: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3740:4: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3740:4: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:3741:5: lv_expression_3_0= ruleXExpressionType
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
            		
            otherlv_5=(Token)match(input,37,FOLLOW_43); 

            			newLeafNode(otherlv_5, grammarAccess.getXWhileTypeAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalXScript.g:3766:3: ( (lv_elements_6_0= ruleXWhileElementType ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==RULE_ID||LA37_0==47||LA37_0==49||(LA37_0>=51 && LA37_0<=54)||(LA37_0>=57 && LA37_0<=59)||(LA37_0>=62 && LA37_0<=64)) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalXScript.g:3767:4: (lv_elements_6_0= ruleXWhileElementType )
            	    {
            	    // InternalXScript.g:3767:4: (lv_elements_6_0= ruleXWhileElementType )
            	    // InternalXScript.g:3768:5: lv_elements_6_0= ruleXWhileElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXWhileTypeAccess().getElementsXWhileElementTypeParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_43);
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
            	    break loop37;
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
    // InternalXScript.g:3793:1: entryRuleXWhileElementType returns [EObject current=null] : iv_ruleXWhileElementType= ruleXWhileElementType EOF ;
    public final EObject entryRuleXWhileElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileElementType = null;


        try {
            // InternalXScript.g:3793:58: (iv_ruleXWhileElementType= ruleXWhileElementType EOF )
            // InternalXScript.g:3794:2: iv_ruleXWhileElementType= ruleXWhileElementType EOF
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
    // InternalXScript.g:3800:1: ruleXWhileElementType returns [EObject current=null] : (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType ) ;
    public final EObject ruleXWhileElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XWhileBreakType_0 = null;

        EObject this_XClassFunctionElementType_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:3806:2: ( (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType ) )
            // InternalXScript.g:3807:2: (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType )
            {
            // InternalXScript.g:3807:2: (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==63) ) {
                alt38=1;
            }
            else if ( (LA38_0==RULE_ID||LA38_0==47||LA38_0==49||(LA38_0>=51 && LA38_0<=54)||(LA38_0>=57 && LA38_0<=59)||LA38_0==62||LA38_0==64) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // InternalXScript.g:3808:3: this_XWhileBreakType_0= ruleXWhileBreakType
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
                    // InternalXScript.g:3817:3: this_XClassFunctionElementType_1= ruleXClassFunctionElementType
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
    // InternalXScript.g:3829:1: entryRuleXWhileBreakType returns [EObject current=null] : iv_ruleXWhileBreakType= ruleXWhileBreakType EOF ;
    public final EObject entryRuleXWhileBreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileBreakType = null;


        try {
            // InternalXScript.g:3829:56: (iv_ruleXWhileBreakType= ruleXWhileBreakType EOF )
            // InternalXScript.g:3830:2: iv_ruleXWhileBreakType= ruleXWhileBreakType EOF
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
    // InternalXScript.g:3836:1: ruleXWhileBreakType returns [EObject current=null] : ( () otherlv_1= 'break' otherlv_2= ';' ) ;
    public final EObject ruleXWhileBreakType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalXScript.g:3842:2: ( ( () otherlv_1= 'break' otherlv_2= ';' ) )
            // InternalXScript.g:3843:2: ( () otherlv_1= 'break' otherlv_2= ';' )
            {
            // InternalXScript.g:3843:2: ( () otherlv_1= 'break' otherlv_2= ';' )
            // InternalXScript.g:3844:3: () otherlv_1= 'break' otherlv_2= ';'
            {
            // InternalXScript.g:3844:3: ()
            // InternalXScript.g:3845:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXWhileBreakTypeAccess().getXWhileBreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,63,FOLLOW_12); 

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
    // InternalXScript.g:3863:1: entryRuleXSleepType returns [EObject current=null] : iv_ruleXSleepType= ruleXSleepType EOF ;
    public final EObject entryRuleXSleepType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSleepType = null;


        try {
            // InternalXScript.g:3863:51: (iv_ruleXSleepType= ruleXSleepType EOF )
            // InternalXScript.g:3864:2: iv_ruleXSleepType= ruleXSleepType EOF
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
    // InternalXScript.g:3870:1: ruleXSleepType returns [EObject current=null] : ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' ) ;
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
            // InternalXScript.g:3876:2: ( ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' ) )
            // InternalXScript.g:3877:2: ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' )
            {
            // InternalXScript.g:3877:2: ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' )
            // InternalXScript.g:3878:3: () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';'
            {
            // InternalXScript.g:3878:3: ()
            // InternalXScript.g:3879:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSleepTypeAccess().getXSleepTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,64,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSleepTypeAccess().getSleepKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getXSleepTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3893:3: ( (lv_interval_3_0= RULE_INT ) )
            // InternalXScript.g:3894:4: (lv_interval_3_0= RULE_INT )
            {
            // InternalXScript.g:3894:4: (lv_interval_3_0= RULE_INT )
            // InternalXScript.g:3895:5: lv_interval_3_0= RULE_INT
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

            // InternalXScript.g:3911:3: ( (lv_unit_4_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:3912:4: (lv_unit_4_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:3912:4: (lv_unit_4_0= ruleStreamTimeUnit )
            // InternalXScript.g:3913:5: lv_unit_4_0= ruleStreamTimeUnit
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
    // InternalXScript.g:3942:1: entryRuleXExpressionType returns [EObject current=null] : iv_ruleXExpressionType= ruleXExpressionType EOF ;
    public final EObject entryRuleXExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionType = null;


        try {
            // InternalXScript.g:3942:56: (iv_ruleXExpressionType= ruleXExpressionType EOF )
            // InternalXScript.g:3943:2: iv_ruleXExpressionType= ruleXExpressionType EOF
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
    // InternalXScript.g:3949:1: ruleXExpressionType returns [EObject current=null] : this_XOrType_0= ruleXOrType ;
    public final EObject ruleXExpressionType() throws RecognitionException {
        EObject current = null;

        EObject this_XOrType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3955:2: (this_XOrType_0= ruleXOrType )
            // InternalXScript.g:3956:2: this_XOrType_0= ruleXOrType
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
    // InternalXScript.g:3967:1: entryRuleXOrType returns [EObject current=null] : iv_ruleXOrType= ruleXOrType EOF ;
    public final EObject entryRuleXOrType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOrType = null;


        try {
            // InternalXScript.g:3967:48: (iv_ruleXOrType= ruleXOrType EOF )
            // InternalXScript.g:3968:2: iv_ruleXOrType= ruleXOrType EOF
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
    // InternalXScript.g:3974:1: ruleXOrType returns [EObject current=null] : (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* ) ;
    public final EObject ruleXOrType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XAndType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3980:2: ( (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* ) )
            // InternalXScript.g:3981:2: (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* )
            {
            // InternalXScript.g:3981:2: (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* )
            // InternalXScript.g:3982:3: this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXOrTypeAccess().getXAndTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_13);
            this_XAndType_0=ruleXAndType();

            state._fsp--;


            			current = this_XAndType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:3990:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==18) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalXScript.g:3991:4: () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) )
            	    {
            	    // InternalXScript.g:3991:4: ()
            	    // InternalXScript.g:3992:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXOrTypeAccess().getXOrTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,18,FOLLOW_33); 

            	    				newLeafNode(otherlv_2, grammarAccess.getXOrTypeAccess().getVerticalLineVerticalLineKeyword_1_1());
            	    			
            	    // InternalXScript.g:4002:4: ( (lv_right_3_0= ruleXAndType ) )
            	    // InternalXScript.g:4003:5: (lv_right_3_0= ruleXAndType )
            	    {
            	    // InternalXScript.g:4003:5: (lv_right_3_0= ruleXAndType )
            	    // InternalXScript.g:4004:6: lv_right_3_0= ruleXAndType
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
    // $ANTLR end "ruleXOrType"


    // $ANTLR start "entryRuleXAndType"
    // InternalXScript.g:4026:1: entryRuleXAndType returns [EObject current=null] : iv_ruleXAndType= ruleXAndType EOF ;
    public final EObject entryRuleXAndType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAndType = null;


        try {
            // InternalXScript.g:4026:49: (iv_ruleXAndType= ruleXAndType EOF )
            // InternalXScript.g:4027:2: iv_ruleXAndType= ruleXAndType EOF
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
    // InternalXScript.g:4033:1: ruleXAndType returns [EObject current=null] : (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* ) ;
    public final EObject ruleXAndType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XEqualityType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4039:2: ( (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* ) )
            // InternalXScript.g:4040:2: (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* )
            {
            // InternalXScript.g:4040:2: (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* )
            // InternalXScript.g:4041:3: this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXAndTypeAccess().getXEqualityTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_14);
            this_XEqualityType_0=ruleXEqualityType();

            state._fsp--;


            			current = this_XEqualityType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4049:3: ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==19) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalXScript.g:4050:4: () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) )
            	    {
            	    // InternalXScript.g:4050:4: ()
            	    // InternalXScript.g:4051:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXAndTypeAccess().getXAndTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,19,FOLLOW_33); 

            	    				newLeafNode(otherlv_2, grammarAccess.getXAndTypeAccess().getAmpersandAmpersandKeyword_1_1());
            	    			
            	    // InternalXScript.g:4061:4: ( (lv_right_3_0= ruleXEqualityType ) )
            	    // InternalXScript.g:4062:5: (lv_right_3_0= ruleXEqualityType )
            	    {
            	    // InternalXScript.g:4062:5: (lv_right_3_0= ruleXEqualityType )
            	    // InternalXScript.g:4063:6: lv_right_3_0= ruleXEqualityType
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
            	    break loop40;
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
    // InternalXScript.g:4085:1: entryRuleXEqualityType returns [EObject current=null] : iv_ruleXEqualityType= ruleXEqualityType EOF ;
    public final EObject entryRuleXEqualityType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXEqualityType = null;


        try {
            // InternalXScript.g:4085:54: (iv_ruleXEqualityType= ruleXEqualityType EOF )
            // InternalXScript.g:4086:2: iv_ruleXEqualityType= ruleXEqualityType EOF
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
    // InternalXScript.g:4092:1: ruleXEqualityType returns [EObject current=null] : (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* ) ;
    public final EObject ruleXEqualityType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_XComparisonType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4098:2: ( (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* ) )
            // InternalXScript.g:4099:2: (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* )
            {
            // InternalXScript.g:4099:2: (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* )
            // InternalXScript.g:4100:3: this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXEqualityTypeAccess().getXComparisonTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_15);
            this_XComparisonType_0=ruleXComparisonType();

            state._fsp--;


            			current = this_XComparisonType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4108:3: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0>=20 && LA42_0<=21)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalXScript.g:4109:4: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) )
            	    {
            	    // InternalXScript.g:4109:4: ()
            	    // InternalXScript.g:4110:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXEqualityTypeAccess().getXEqualityTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4116:4: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // InternalXScript.g:4117:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // InternalXScript.g:4117:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // InternalXScript.g:4118:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // InternalXScript.g:4118:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    int alt41=2;
            	    int LA41_0 = input.LA(1);

            	    if ( (LA41_0==20) ) {
            	        alt41=1;
            	    }
            	    else if ( (LA41_0==21) ) {
            	        alt41=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 41, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt41) {
            	        case 1 :
            	            // InternalXScript.g:4119:7: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,20,FOLLOW_33); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXEqualityTypeAccess().getOpEqualsSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXEqualityTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4130:7: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,21,FOLLOW_33); 

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

            	    // InternalXScript.g:4143:4: ( (lv_right_3_0= ruleXComparisonType ) )
            	    // InternalXScript.g:4144:5: (lv_right_3_0= ruleXComparisonType )
            	    {
            	    // InternalXScript.g:4144:5: (lv_right_3_0= ruleXComparisonType )
            	    // InternalXScript.g:4145:6: lv_right_3_0= ruleXComparisonType
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
            	    break loop42;
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
    // InternalXScript.g:4167:1: entryRuleXComparisonType returns [EObject current=null] : iv_ruleXComparisonType= ruleXComparisonType EOF ;
    public final EObject entryRuleXComparisonType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXComparisonType = null;


        try {
            // InternalXScript.g:4167:56: (iv_ruleXComparisonType= ruleXComparisonType EOF )
            // InternalXScript.g:4168:2: iv_ruleXComparisonType= ruleXComparisonType EOF
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
    // InternalXScript.g:4174:1: ruleXComparisonType returns [EObject current=null] : (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* ) ;
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
            // InternalXScript.g:4180:2: ( (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* ) )
            // InternalXScript.g:4181:2: (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* )
            {
            // InternalXScript.g:4181:2: (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* )
            // InternalXScript.g:4182:3: this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXComparisonTypeAccess().getXPlusOrMinusTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_16);
            this_XPlusOrMinusType_0=ruleXPlusOrMinusType();

            state._fsp--;


            			current = this_XPlusOrMinusType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4190:3: ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( ((LA44_0>=22 && LA44_0<=25)) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalXScript.g:4191:4: () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) )
            	    {
            	    // InternalXScript.g:4191:4: ()
            	    // InternalXScript.g:4192:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXComparisonTypeAccess().getXComparisonTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4198:4: ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) )
            	    // InternalXScript.g:4199:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    {
            	    // InternalXScript.g:4199:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    // InternalXScript.g:4200:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    {
            	    // InternalXScript.g:4200:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    int alt43=4;
            	    switch ( input.LA(1) ) {
            	    case 22:
            	        {
            	        alt43=1;
            	        }
            	        break;
            	    case 23:
            	        {
            	        alt43=2;
            	        }
            	        break;
            	    case 24:
            	        {
            	        alt43=3;
            	        }
            	        break;
            	    case 25:
            	        {
            	        alt43=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 43, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt43) {
            	        case 1 :
            	            // InternalXScript.g:4201:7: lv_op_2_1= '>='
            	            {
            	            lv_op_2_1=(Token)match(input,22,FOLLOW_33); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXComparisonTypeAccess().getOpGreaterThanSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4212:7: lv_op_2_2= '<='
            	            {
            	            lv_op_2_2=(Token)match(input,23,FOLLOW_33); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getXComparisonTypeAccess().getOpLessThanSignEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalXScript.g:4223:7: lv_op_2_3= '>'
            	            {
            	            lv_op_2_3=(Token)match(input,24,FOLLOW_33); 

            	            							newLeafNode(lv_op_2_3, grammarAccess.getXComparisonTypeAccess().getOpGreaterThanSignKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalXScript.g:4234:7: lv_op_2_4= '<'
            	            {
            	            lv_op_2_4=(Token)match(input,25,FOLLOW_33); 

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

            	    // InternalXScript.g:4247:4: ( (lv_right_3_0= ruleXPlusOrMinusType ) )
            	    // InternalXScript.g:4248:5: (lv_right_3_0= ruleXPlusOrMinusType )
            	    {
            	    // InternalXScript.g:4248:5: (lv_right_3_0= ruleXPlusOrMinusType )
            	    // InternalXScript.g:4249:6: lv_right_3_0= ruleXPlusOrMinusType
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
            	    break loop44;
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
    // InternalXScript.g:4271:1: entryRuleXPlusOrMinusType returns [EObject current=null] : iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF ;
    public final EObject entryRuleXPlusOrMinusType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPlusOrMinusType = null;


        try {
            // InternalXScript.g:4271:57: (iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF )
            // InternalXScript.g:4272:2: iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF
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
    // InternalXScript.g:4278:1: ruleXPlusOrMinusType returns [EObject current=null] : (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* ) ;
    public final EObject ruleXPlusOrMinusType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_XMulOrDivType_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4284:2: ( (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* ) )
            // InternalXScript.g:4285:2: (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* )
            {
            // InternalXScript.g:4285:2: (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* )
            // InternalXScript.g:4286:3: this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXPlusOrMinusTypeAccess().getXMulOrDivTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_17);
            this_XMulOrDivType_0=ruleXMulOrDivType();

            state._fsp--;


            			current = this_XMulOrDivType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4294:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( ((LA46_0>=26 && LA46_0<=27)) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // InternalXScript.g:4295:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) )
            	    {
            	    // InternalXScript.g:4295:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt45=2;
            	    int LA45_0 = input.LA(1);

            	    if ( (LA45_0==26) ) {
            	        alt45=1;
            	    }
            	    else if ( (LA45_0==27) ) {
            	        alt45=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 45, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt45) {
            	        case 1 :
            	            // InternalXScript.g:4296:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalXScript.g:4296:5: ( () otherlv_2= '+' )
            	            // InternalXScript.g:4297:6: () otherlv_2= '+'
            	            {
            	            // InternalXScript.g:4297:6: ()
            	            // InternalXScript.g:4298:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getXPlusOrMinusTypeAccess().getXPlusTypeLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,26,FOLLOW_33); 

            	            						newLeafNode(otherlv_2, grammarAccess.getXPlusOrMinusTypeAccess().getPlusSignKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4310:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalXScript.g:4310:5: ( () otherlv_4= '-' )
            	            // InternalXScript.g:4311:6: () otherlv_4= '-'
            	            {
            	            // InternalXScript.g:4311:6: ()
            	            // InternalXScript.g:4312:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getXPlusOrMinusTypeAccess().getXMinusTypeLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,27,FOLLOW_33); 

            	            						newLeafNode(otherlv_4, grammarAccess.getXPlusOrMinusTypeAccess().getHyphenMinusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalXScript.g:4324:4: ( (lv_right_5_0= ruleXMulOrDivType ) )
            	    // InternalXScript.g:4325:5: (lv_right_5_0= ruleXMulOrDivType )
            	    {
            	    // InternalXScript.g:4325:5: (lv_right_5_0= ruleXMulOrDivType )
            	    // InternalXScript.g:4326:6: lv_right_5_0= ruleXMulOrDivType
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
            	    break loop46;
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
    // InternalXScript.g:4348:1: entryRuleXMulOrDivType returns [EObject current=null] : iv_ruleXMulOrDivType= ruleXMulOrDivType EOF ;
    public final EObject entryRuleXMulOrDivType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXMulOrDivType = null;


        try {
            // InternalXScript.g:4348:54: (iv_ruleXMulOrDivType= ruleXMulOrDivType EOF )
            // InternalXScript.g:4349:2: iv_ruleXMulOrDivType= ruleXMulOrDivType EOF
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
    // InternalXScript.g:4355:1: ruleXMulOrDivType returns [EObject current=null] : (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* ) ;
    public final EObject ruleXMulOrDivType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_XPrimaryType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4361:2: ( (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* ) )
            // InternalXScript.g:4362:2: (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* )
            {
            // InternalXScript.g:4362:2: (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* )
            // InternalXScript.g:4363:3: this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXMulOrDivTypeAccess().getXPrimaryTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_18);
            this_XPrimaryType_0=ruleXPrimaryType();

            state._fsp--;


            			current = this_XPrimaryType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4371:3: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=28 && LA48_0<=29)) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalXScript.g:4372:4: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) )
            	    {
            	    // InternalXScript.g:4372:4: ()
            	    // InternalXScript.g:4373:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXMulOrDivTypeAccess().getXMulOrDivTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4379:4: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // InternalXScript.g:4380:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // InternalXScript.g:4380:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // InternalXScript.g:4381:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // InternalXScript.g:4381:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt47=2;
            	    int LA47_0 = input.LA(1);

            	    if ( (LA47_0==28) ) {
            	        alt47=1;
            	    }
            	    else if ( (LA47_0==29) ) {
            	        alt47=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 47, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt47) {
            	        case 1 :
            	            // InternalXScript.g:4382:7: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,28,FOLLOW_33); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXMulOrDivTypeAccess().getOpAsteriskKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXMulOrDivTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4393:7: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,29,FOLLOW_33); 

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

            	    // InternalXScript.g:4406:4: ( (lv_right_3_0= ruleXPrimaryType ) )
            	    // InternalXScript.g:4407:5: (lv_right_3_0= ruleXPrimaryType )
            	    {
            	    // InternalXScript.g:4407:5: (lv_right_3_0= ruleXPrimaryType )
            	    // InternalXScript.g:4408:6: lv_right_3_0= ruleXPrimaryType
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
            	    break loop48;
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
    // InternalXScript.g:4430:1: entryRuleXPrimaryType returns [EObject current=null] : iv_ruleXPrimaryType= ruleXPrimaryType EOF ;
    public final EObject entryRuleXPrimaryType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPrimaryType = null;


        try {
            // InternalXScript.g:4430:53: (iv_ruleXPrimaryType= ruleXPrimaryType EOF )
            // InternalXScript.g:4431:2: iv_ruleXPrimaryType= ruleXPrimaryType EOF
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
    // InternalXScript.g:4437:1: ruleXPrimaryType returns [EObject current=null] : ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType ) ;
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
            // InternalXScript.g:4443:2: ( ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType ) )
            // InternalXScript.g:4444:2: ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType )
            {
            // InternalXScript.g:4444:2: ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType )
            int alt49=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt49=1;
                }
                break;
            case 30:
                {
                alt49=2;
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
            case 65:
            case 66:
            case 72:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
                {
                alt49=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 49, 0, input);

                throw nvae;
            }

            switch (alt49) {
                case 1 :
                    // InternalXScript.g:4445:3: (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' )
                    {
                    // InternalXScript.g:4445:3: (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' )
                    // InternalXScript.g:4446:4: otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,13,FOLLOW_33); 

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
                    // InternalXScript.g:4464:3: ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) )
                    {
                    // InternalXScript.g:4464:3: ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) )
                    // InternalXScript.g:4465:4: () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) )
                    {
                    // InternalXScript.g:4465:4: ()
                    // InternalXScript.g:4466:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXPrimaryTypeAccess().getXNotTypeAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_4=(Token)match(input,30,FOLLOW_33); 

                    				newLeafNode(otherlv_4, grammarAccess.getXPrimaryTypeAccess().getExclamationMarkKeyword_1_1());
                    			
                    // InternalXScript.g:4476:4: ( (lv_expression_5_0= ruleXPrimaryType ) )
                    // InternalXScript.g:4477:5: (lv_expression_5_0= ruleXPrimaryType )
                    {
                    // InternalXScript.g:4477:5: (lv_expression_5_0= ruleXPrimaryType )
                    // InternalXScript.g:4478:6: lv_expression_5_0= ruleXPrimaryType
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
                    // InternalXScript.g:4497:3: this_XAtomicType_6= ruleXAtomicType
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
    // InternalXScript.g:4509:1: entryRuleXAtomicType returns [EObject current=null] : iv_ruleXAtomicType= ruleXAtomicType EOF ;
    public final EObject entryRuleXAtomicType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAtomicType = null;


        try {
            // InternalXScript.g:4509:52: (iv_ruleXAtomicType= ruleXAtomicType EOF )
            // InternalXScript.g:4510:2: iv_ruleXAtomicType= ruleXAtomicType EOF
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
    // InternalXScript.g:4516:1: ruleXAtomicType returns [EObject current=null] : this_XAtomicBaseType_0= ruleXAtomicBaseType ;
    public final EObject ruleXAtomicType() throws RecognitionException {
        EObject current = null;

        EObject this_XAtomicBaseType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4522:2: (this_XAtomicBaseType_0= ruleXAtomicBaseType )
            // InternalXScript.g:4523:2: this_XAtomicBaseType_0= ruleXAtomicBaseType
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
    // InternalXScript.g:4534:1: entryRuleXAtomicBaseType returns [EObject current=null] : iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF ;
    public final EObject entryRuleXAtomicBaseType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAtomicBaseType = null;


        try {
            // InternalXScript.g:4534:56: (iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF )
            // InternalXScript.g:4535:2: iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF
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
    // InternalXScript.g:4541:1: ruleXAtomicBaseType returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType ) ;
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
            // InternalXScript.g:4547:2: ( ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType ) )
            // InternalXScript.g:4548:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )
            {
            // InternalXScript.g:4548:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )
            int alt51=18;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // InternalXScript.g:4549:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    {
                    // InternalXScript.g:4549:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    // InternalXScript.g:4550:4: () ( (lv_value_1_0= RULE_DOUBLE ) )
                    {
                    // InternalXScript.g:4550:4: ()
                    // InternalXScript.g:4551:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXDoubleConstantTypeAction_0_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:4557:4: ( (lv_value_1_0= RULE_DOUBLE ) )
                    // InternalXScript.g:4558:5: (lv_value_1_0= RULE_DOUBLE )
                    {
                    // InternalXScript.g:4558:5: (lv_value_1_0= RULE_DOUBLE )
                    // InternalXScript.g:4559:6: lv_value_1_0= RULE_DOUBLE
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
                    // InternalXScript.g:4577:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    {
                    // InternalXScript.g:4577:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    // InternalXScript.g:4578:4: () ( (lv_value_3_0= RULE_INT ) )
                    {
                    // InternalXScript.g:4578:4: ()
                    // InternalXScript.g:4579:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXIntConstantTypeAction_1_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:4585:4: ( (lv_value_3_0= RULE_INT ) )
                    // InternalXScript.g:4586:5: (lv_value_3_0= RULE_INT )
                    {
                    // InternalXScript.g:4586:5: (lv_value_3_0= RULE_INT )
                    // InternalXScript.g:4587:6: lv_value_3_0= RULE_INT
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
                    // InternalXScript.g:4605:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    {
                    // InternalXScript.g:4605:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    // InternalXScript.g:4606:4: () ( (lv_value_5_0= RULE_STRING ) )
                    {
                    // InternalXScript.g:4606:4: ()
                    // InternalXScript.g:4607:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXStringConstantTypeAction_2_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:4613:4: ( (lv_value_5_0= RULE_STRING ) )
                    // InternalXScript.g:4614:5: (lv_value_5_0= RULE_STRING )
                    {
                    // InternalXScript.g:4614:5: (lv_value_5_0= RULE_STRING )
                    // InternalXScript.g:4615:6: lv_value_5_0= RULE_STRING
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
                    // InternalXScript.g:4633:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    {
                    // InternalXScript.g:4633:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    // InternalXScript.g:4634:4: () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    {
                    // InternalXScript.g:4634:4: ()
                    // InternalXScript.g:4635:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXBoolConstantTypeAction_3_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:4641:4: ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    // InternalXScript.g:4642:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    {
                    // InternalXScript.g:4642:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    // InternalXScript.g:4643:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    {
                    // InternalXScript.g:4643:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==31) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==32) ) {
                        alt50=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // InternalXScript.g:4644:7: lv_value_7_1= 'true'
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
                            // InternalXScript.g:4655:7: lv_value_7_2= 'false'
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
                    // InternalXScript.g:4670:3: this_XPercentChangeExpType_8= ruleXPercentChangeExpType
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
                    // InternalXScript.g:4679:3: this_XSubExpType_9= ruleXSubExpType
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
                    // InternalXScript.g:4688:3: this_XVarExpType_10= ruleXVarExpType
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
                    // InternalXScript.g:4697:3: this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType
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
                    // InternalXScript.g:4706:3: this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType
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
                    // InternalXScript.g:4715:3: this_XFunctionCallExpType_13= ruleXFunctionCallExpType
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
                    // InternalXScript.g:4724:3: this_XVarStreakType_14= ruleXVarStreakType
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
                    // InternalXScript.g:4733:3: this_XVarCompareStreakType_15= ruleXVarCompareStreakType
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
                    // InternalXScript.g:4742:3: this_XSlrAvgExpType_16= ruleXSlrAvgExpType
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
                    // InternalXScript.g:4751:3: this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType
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
                    // InternalXScript.g:4760:3: this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType
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
                    // InternalXScript.g:4769:3: this_XVarianceAverageType_19= ruleXVarianceAverageType
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
                    // InternalXScript.g:4778:3: this_XRocExpType_20= ruleXRocExpType
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
                    // InternalXScript.g:4787:3: this_XVarianceMaxType_21= ruleXVarianceMaxType
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
    // InternalXScript.g:4799:1: entryRuleXPercentChangeExpType returns [EObject current=null] : iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF ;
    public final EObject entryRuleXPercentChangeExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPercentChangeExpType = null;


        try {
            // InternalXScript.g:4799:62: (iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF )
            // InternalXScript.g:4800:2: iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF
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
    // InternalXScript.g:4806:1: ruleXPercentChangeExpType returns [EObject current=null] : ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:4812:2: ( ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:4813:2: ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:4813:2: ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:4814:3: () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:4814:3: ()
            // InternalXScript.g:4815:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXPercentChangeExpTypeAccess().getXPercentChangeExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,65,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXPercentChangeExpTypeAccess().getPercentChangeKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_33); 

            			newLeafNode(otherlv_2, grammarAccess.getXPercentChangeExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:4829:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:4830:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:4830:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:4831:5: lv_value1_3_0= ruleXExpressionType
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

            otherlv_4=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_4, grammarAccess.getXPercentChangeExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:4852:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:4853:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:4853:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:4854:5: lv_value2_5_0= ruleXExpressionType
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
    // InternalXScript.g:4879:1: entryRuleXSubExpType returns [EObject current=null] : iv_ruleXSubExpType= ruleXSubExpType EOF ;
    public final EObject entryRuleXSubExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSubExpType = null;


        try {
            // InternalXScript.g:4879:52: (iv_ruleXSubExpType= ruleXSubExpType EOF )
            // InternalXScript.g:4880:2: iv_ruleXSubExpType= ruleXSubExpType EOF
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
    // InternalXScript.g:4886:1: ruleXSubExpType returns [EObject current=null] : ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:4892:2: ( ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:4893:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:4893:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:4894:3: () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:4894:3: ()
            // InternalXScript.g:4895:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSubExpTypeAccess().getXSubExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,42,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSubExpTypeAccess().getSubKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_33); 

            			newLeafNode(otherlv_2, grammarAccess.getXSubExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:4909:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:4910:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:4910:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:4911:5: lv_value1_3_0= ruleXExpressionType
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

            otherlv_4=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_4, grammarAccess.getXSubExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:4932:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:4933:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:4933:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:4934:5: lv_value2_5_0= ruleXExpressionType
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
    // InternalXScript.g:4959:1: entryRuleXVarExpType returns [EObject current=null] : iv_ruleXVarExpType= ruleXVarExpType EOF ;
    public final EObject entryRuleXVarExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarExpType = null;


        try {
            // InternalXScript.g:4959:52: (iv_ruleXVarExpType= ruleXVarExpType EOF )
            // InternalXScript.g:4960:2: iv_ruleXVarExpType= ruleXVarExpType EOF
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
    // InternalXScript.g:4966:1: ruleXVarExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleXVarExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalXScript.g:4972:2: ( ( () ( (otherlv_1= RULE_ID ) ) ) )
            // InternalXScript.g:4973:2: ( () ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalXScript.g:4973:2: ( () ( (otherlv_1= RULE_ID ) ) )
            // InternalXScript.g:4974:3: () ( (otherlv_1= RULE_ID ) )
            {
            // InternalXScript.g:4974:3: ()
            // InternalXScript.g:4975:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarExpTypeAccess().getXVarExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:4981:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:4982:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:4982:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:4983:5: otherlv_1= RULE_ID
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
    // InternalXScript.g:4998:1: entryRuleXStreamWrapperExpType returns [EObject current=null] : iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF ;
    public final EObject entryRuleXStreamWrapperExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamWrapperExpType = null;


        try {
            // InternalXScript.g:4998:62: (iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF )
            // InternalXScript.g:4999:2: iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF
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
    // InternalXScript.g:5005:1: ruleXStreamWrapperExpType returns [EObject current=null] : ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' ) ;
    public final EObject ruleXStreamWrapperExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_wrapperExp_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:5011:2: ( ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' ) )
            // InternalXScript.g:5012:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:5012:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            // InternalXScript.g:5013:3: () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')'
            {
            // InternalXScript.g:5013:3: ()
            // InternalXScript.g:5014:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamWrapperExpTypeAccess().getXStreamWrapperExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,36,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXStreamWrapperExpTypeAccess().getExpKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getXStreamWrapperExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5028:3: ( (lv_wrapperExp_3_0= ruleExpressionType ) )
            // InternalXScript.g:5029:4: (lv_wrapperExp_3_0= ruleExpressionType )
            {
            // InternalXScript.g:5029:4: (lv_wrapperExp_3_0= ruleExpressionType )
            // InternalXScript.g:5030:5: lv_wrapperExp_3_0= ruleExpressionType
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
    // InternalXScript.g:5055:1: entryRuleXStreamVarValueExpType returns [EObject current=null] : iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF ;
    public final EObject entryRuleXStreamVarValueExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamVarValueExpType = null;


        try {
            // InternalXScript.g:5055:63: (iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF )
            // InternalXScript.g:5056:2: iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF
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
    // InternalXScript.g:5062:1: ruleXStreamVarValueExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' ) ;
    public final EObject ruleXStreamVarValueExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expressionValue_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:5068:2: ( ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' ) )
            // InternalXScript.g:5069:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' )
            {
            // InternalXScript.g:5069:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' )
            // InternalXScript.g:5070:3: () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']'
            {
            // InternalXScript.g:5070:3: ()
            // InternalXScript.g:5071:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamVarValueExpTypeAccess().getXStreamVarValueExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5077:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:5078:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:5078:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:5079:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXStreamVarValueExpTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(otherlv_1, grammarAccess.getXStreamVarValueExpTypeAccess().getVarVarTypeCrossReference_1_0());
            				

            }


            }

            // InternalXScript.g:5090:3: (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) )
            // InternalXScript.g:5091:4: otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) )
            {
            otherlv_2=(Token)match(input,34,FOLLOW_33); 

            				newLeafNode(otherlv_2, grammarAccess.getXStreamVarValueExpTypeAccess().getLeftSquareBracketKeyword_2_0());
            			
            // InternalXScript.g:5095:4: ( (lv_expressionValue_3_0= ruleXExpressionType ) )
            // InternalXScript.g:5096:5: (lv_expressionValue_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:5096:5: (lv_expressionValue_3_0= ruleXExpressionType )
            // InternalXScript.g:5097:6: lv_expressionValue_3_0= ruleXExpressionType
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
    // InternalXScript.g:5123:1: entryRuleXFunctionCallExpType returns [EObject current=null] : iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF ;
    public final EObject entryRuleXFunctionCallExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCallExpType = null;


        try {
            // InternalXScript.g:5123:61: (iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF )
            // InternalXScript.g:5124:2: iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF
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
    // InternalXScript.g:5130:1: ruleXFunctionCallExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' ) ;
    public final EObject ruleXFunctionCallExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_parms_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalXScript.g:5136:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' ) )
            // InternalXScript.g:5137:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' )
            {
            // InternalXScript.g:5137:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' )
            // InternalXScript.g:5138:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')'
            {
            // InternalXScript.g:5138:3: ()
            // InternalXScript.g:5139:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionCallExpTypeAccess().getXFunctionCallExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5145:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:5146:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:5146:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:5147:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionCallExpTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(otherlv_1, grammarAccess.getXFunctionCallExpTypeAccess().getFunctionXFunctionTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_37); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionCallExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5162:3: ( (lv_parms_3_0= RULE_STRING ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==RULE_STRING) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalXScript.g:5163:4: (lv_parms_3_0= RULE_STRING )
                    {
                    // InternalXScript.g:5163:4: (lv_parms_3_0= RULE_STRING )
                    // InternalXScript.g:5164:5: lv_parms_3_0= RULE_STRING
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
    // InternalXScript.g:5188:1: entryRuleXVarStreakType returns [EObject current=null] : iv_ruleXVarStreakType= ruleXVarStreakType EOF ;
    public final EObject entryRuleXVarStreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakType = null;


        try {
            // InternalXScript.g:5188:55: (iv_ruleXVarStreakType= ruleXVarStreakType EOF )
            // InternalXScript.g:5189:2: iv_ruleXVarStreakType= ruleXVarStreakType EOF
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
    // InternalXScript.g:5195:1: ruleXVarStreakType returns [EObject current=null] : ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' ) ;
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
            // InternalXScript.g:5201:2: ( ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' ) )
            // InternalXScript.g:5202:2: ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' )
            {
            // InternalXScript.g:5202:2: ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' )
            // InternalXScript.g:5203:3: () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')'
            {
            // InternalXScript.g:5203:3: ()
            // InternalXScript.g:5204:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakTypeAccess().getXVarStreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,66,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarStreakTypeAccess().getColumnStrkKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarStreakTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5218:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5219:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5219:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5220:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarStreakTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXVarStreakTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_44); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5235:3: ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) )
            // InternalXScript.g:5236:4: ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) )
            {
            // InternalXScript.g:5236:4: ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) )
            // InternalXScript.g:5237:5: (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' )
            {
            // InternalXScript.g:5237:5: (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==67) ) {
                alt53=1;
            }
            else if ( (LA53_0==68) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // InternalXScript.g:5238:6: lv_direction_5_1= 'bwd'
                    {
                    lv_direction_5_1=(Token)match(input,67,FOLLOW_7); 

                    						newLeafNode(lv_direction_5_1, grammarAccess.getXVarStreakTypeAccess().getDirectionBwdKeyword_5_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "direction", lv_direction_5_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5249:6: lv_direction_5_2= 'fwd'
                    {
                    lv_direction_5_2=(Token)match(input,68,FOLLOW_7); 

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

            otherlv_6=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:5266:3: ( (lv_startIndexExp_7_0= ruleXExpressionType ) )
            // InternalXScript.g:5267:4: (lv_startIndexExp_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:5267:4: (lv_startIndexExp_7_0= ruleXExpressionType )
            // InternalXScript.g:5268:5: lv_startIndexExp_7_0= ruleXExpressionType
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

            otherlv_8=(Token)match(input,14,FOLLOW_45); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:5289:3: ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( ((LA54_0>=69 && LA54_0<=70)) ) {
                alt54=1;
            }
            else if ( (LA54_0==71) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // InternalXScript.g:5290:4: ( (lv_compare_9_0= ruleXVarStreakCompareType ) )
                    {
                    // InternalXScript.g:5290:4: ( (lv_compare_9_0= ruleXVarStreakCompareType ) )
                    // InternalXScript.g:5291:5: (lv_compare_9_0= ruleXVarStreakCompareType )
                    {
                    // InternalXScript.g:5291:5: (lv_compare_9_0= ruleXVarStreakCompareType )
                    // InternalXScript.g:5292:6: lv_compare_9_0= ruleXVarStreakCompareType
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
                    // InternalXScript.g:5310:4: ( (lv_value_10_0= ruleXVarStreakValueType ) )
                    {
                    // InternalXScript.g:5310:4: ( (lv_value_10_0= ruleXVarStreakValueType ) )
                    // InternalXScript.g:5311:5: (lv_value_10_0= ruleXVarStreakValueType )
                    {
                    // InternalXScript.g:5311:5: (lv_value_10_0= ruleXVarStreakValueType )
                    // InternalXScript.g:5312:6: lv_value_10_0= ruleXVarStreakValueType
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
    // InternalXScript.g:5338:1: entryRuleXVarStreakCompareType returns [EObject current=null] : iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF ;
    public final EObject entryRuleXVarStreakCompareType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakCompareType = null;


        try {
            // InternalXScript.g:5338:62: (iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF )
            // InternalXScript.g:5339:2: iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF
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
    // InternalXScript.g:5345:1: ruleXVarStreakCompareType returns [EObject current=null] : ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' ) ;
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
            // InternalXScript.g:5351:2: ( ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' ) )
            // InternalXScript.g:5352:2: ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' )
            {
            // InternalXScript.g:5352:2: ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' )
            // InternalXScript.g:5353:3: () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')'
            {
            // InternalXScript.g:5353:3: ()
            // InternalXScript.g:5354:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakCompareTypeAccess().getXVarStreakCompareTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5360:3: ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) )
            // InternalXScript.g:5361:4: ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) )
            {
            // InternalXScript.g:5361:4: ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) )
            // InternalXScript.g:5362:5: (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' )
            {
            // InternalXScript.g:5362:5: (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==69) ) {
                alt55=1;
            }
            else if ( (LA55_0==70) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // InternalXScript.g:5363:6: lv_function_1_1= 'sum'
                    {
                    lv_function_1_1=(Token)match(input,69,FOLLOW_5); 

                    						newLeafNode(lv_function_1_1, grammarAccess.getXVarStreakCompareTypeAccess().getFunctionSumKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5374:6: lv_function_1_2= 'diff'
                    {
                    lv_function_1_2=(Token)match(input,70,FOLLOW_5); 

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
            		
            // InternalXScript.g:5391:3: ( (lv_offset_3_0= RULE_INT ) )
            // InternalXScript.g:5392:4: (lv_offset_3_0= RULE_INT )
            {
            // InternalXScript.g:5392:4: (lv_offset_3_0= RULE_INT )
            // InternalXScript.g:5393:5: lv_offset_3_0= RULE_INT
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
            		
            // InternalXScript.g:5413:3: ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) )
            // InternalXScript.g:5414:4: ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) )
            {
            // InternalXScript.g:5414:4: ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) )
            // InternalXScript.g:5415:5: (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' )
            {
            // InternalXScript.g:5415:5: (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' )
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
                    // InternalXScript.g:5416:6: lv_op_5_1= '<'
                    {
                    lv_op_5_1=(Token)match(input,25,FOLLOW_46); 

                    						newLeafNode(lv_op_5_1, grammarAccess.getXVarStreakCompareTypeAccess().getOpLessThanSignKeyword_5_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_5_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5427:6: lv_op_5_2= '>'
                    {
                    lv_op_5_2=(Token)match(input,24,FOLLOW_46); 

                    						newLeafNode(lv_op_5_2, grammarAccess.getXVarStreakCompareTypeAccess().getOpGreaterThanSignKeyword_5_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_5_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5438:6: lv_op_5_3= '='
                    {
                    lv_op_5_3=(Token)match(input,16,FOLLOW_46); 

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

            // InternalXScript.g:5451:3: ( (lv_test_6_0= RULE_DOUBLE ) )
            // InternalXScript.g:5452:4: (lv_test_6_0= RULE_DOUBLE )
            {
            // InternalXScript.g:5452:4: (lv_test_6_0= RULE_DOUBLE )
            // InternalXScript.g:5453:5: lv_test_6_0= RULE_DOUBLE
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
    // InternalXScript.g:5477:1: entryRuleXVarStreakValueType returns [EObject current=null] : iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF ;
    public final EObject entryRuleXVarStreakValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakValueType = null;


        try {
            // InternalXScript.g:5477:60: (iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF )
            // InternalXScript.g:5478:2: iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF
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
    // InternalXScript.g:5484:1: ruleXVarStreakValueType returns [EObject current=null] : ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleXVarStreakValueType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        Token lv_test_3_0=null;


        	enterRule();

        try {
            // InternalXScript.g:5490:2: ( ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) ) )
            // InternalXScript.g:5491:2: ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) )
            {
            // InternalXScript.g:5491:2: ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) )
            // InternalXScript.g:5492:3: () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) )
            {
            // InternalXScript.g:5492:3: ()
            // InternalXScript.g:5493:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakValueTypeAccess().getXVarStreakValueTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,71,FOLLOW_26); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarStreakValueTypeAccess().getValueKeyword_1());
            		
            // InternalXScript.g:5503:3: ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) )
            // InternalXScript.g:5504:4: ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) )
            {
            // InternalXScript.g:5504:4: ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) )
            // InternalXScript.g:5505:5: (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' )
            {
            // InternalXScript.g:5505:5: (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' )
            int alt57=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt57=1;
                }
                break;
            case 24:
                {
                alt57=2;
                }
                break;
            case 16:
                {
                alt57=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 57, 0, input);

                throw nvae;
            }

            switch (alt57) {
                case 1 :
                    // InternalXScript.g:5506:6: lv_op_2_1= '<'
                    {
                    lv_op_2_1=(Token)match(input,25,FOLLOW_46); 

                    						newLeafNode(lv_op_2_1, grammarAccess.getXVarStreakValueTypeAccess().getOpLessThanSignKeyword_2_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakValueTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_2_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5517:6: lv_op_2_2= '>'
                    {
                    lv_op_2_2=(Token)match(input,24,FOLLOW_46); 

                    						newLeafNode(lv_op_2_2, grammarAccess.getXVarStreakValueTypeAccess().getOpGreaterThanSignKeyword_2_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakValueTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_2_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5528:6: lv_op_2_3= '='
                    {
                    lv_op_2_3=(Token)match(input,16,FOLLOW_46); 

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

            // InternalXScript.g:5541:3: ( (lv_test_3_0= RULE_DOUBLE ) )
            // InternalXScript.g:5542:4: (lv_test_3_0= RULE_DOUBLE )
            {
            // InternalXScript.g:5542:4: (lv_test_3_0= RULE_DOUBLE )
            // InternalXScript.g:5543:5: lv_test_3_0= RULE_DOUBLE
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
    // InternalXScript.g:5563:1: entryRuleXVarCompareStreakType returns [EObject current=null] : iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF ;
    public final EObject entryRuleXVarCompareStreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarCompareStreakType = null;


        try {
            // InternalXScript.g:5563:62: (iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF )
            // InternalXScript.g:5564:2: iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF
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
    // InternalXScript.g:5570:1: ruleXVarCompareStreakType returns [EObject current=null] : ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' ) ;
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
            // InternalXScript.g:5576:2: ( ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' ) )
            // InternalXScript.g:5577:2: ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' )
            {
            // InternalXScript.g:5577:2: ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' )
            // InternalXScript.g:5578:3: () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')'
            {
            // InternalXScript.g:5578:3: ()
            // InternalXScript.g:5579:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarCompareStreakTypeAccess().getXVarCompareStreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,72,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarCompareStreakTypeAccess().getColumnPairStrkKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarCompareStreakTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5593:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5594:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5594:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5595:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:5610:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:5611:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:5611:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:5612:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarCompareStreakTypeAccess().getCompareVarVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_44); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:5627:3: ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) )
            // InternalXScript.g:5628:4: ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) )
            {
            // InternalXScript.g:5628:4: ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) )
            // InternalXScript.g:5629:5: (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' )
            {
            // InternalXScript.g:5629:5: (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==67) ) {
                alt58=1;
            }
            else if ( (LA58_0==68) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // InternalXScript.g:5630:6: lv_direction_7_1= 'bwd'
                    {
                    lv_direction_7_1=(Token)match(input,67,FOLLOW_7); 

                    						newLeafNode(lv_direction_7_1, grammarAccess.getXVarCompareStreakTypeAccess().getDirectionBwdKeyword_7_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "direction", lv_direction_7_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5641:6: lv_direction_7_2= 'fwd'
                    {
                    lv_direction_7_2=(Token)match(input,68,FOLLOW_7); 

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

            otherlv_8=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:5658:3: ( (lv_startIndexExp_9_0= ruleXExpressionType ) )
            // InternalXScript.g:5659:4: (lv_startIndexExp_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:5659:4: (lv_startIndexExp_9_0= ruleXExpressionType )
            // InternalXScript.g:5660:5: lv_startIndexExp_9_0= ruleXExpressionType
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

            otherlv_10=(Token)match(input,14,FOLLOW_47); 

            			newLeafNode(otherlv_10, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_10());
            		
            // InternalXScript.g:5681:3: ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) )
            // InternalXScript.g:5682:4: ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) )
            {
            // InternalXScript.g:5682:4: ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) )
            // InternalXScript.g:5683:5: (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' )
            {
            // InternalXScript.g:5683:5: (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' )
            int alt59=4;
            switch ( input.LA(1) ) {
            case 69:
                {
                alt59=1;
                }
                break;
            case 70:
                {
                alt59=2;
                }
                break;
            case 71:
                {
                alt59=3;
                }
                break;
            case 73:
                {
                alt59=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 59, 0, input);

                throw nvae;
            }

            switch (alt59) {
                case 1 :
                    // InternalXScript.g:5684:6: lv_function_11_1= 'sum'
                    {
                    lv_function_11_1=(Token)match(input,69,FOLLOW_26); 

                    						newLeafNode(lv_function_11_1, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionSumKeyword_11_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5695:6: lv_function_11_2= 'diff'
                    {
                    lv_function_11_2=(Token)match(input,70,FOLLOW_26); 

                    						newLeafNode(lv_function_11_2, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionDiffKeyword_11_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5706:6: lv_function_11_3= 'value'
                    {
                    lv_function_11_3=(Token)match(input,71,FOLLOW_26); 

                    						newLeafNode(lv_function_11_3, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionValueKeyword_11_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalXScript.g:5717:6: lv_function_11_4= 'variance'
                    {
                    lv_function_11_4=(Token)match(input,73,FOLLOW_26); 

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

            // InternalXScript.g:5730:3: ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) )
            // InternalXScript.g:5731:4: ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) )
            {
            // InternalXScript.g:5731:4: ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) )
            // InternalXScript.g:5732:5: (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' )
            {
            // InternalXScript.g:5732:5: (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' )
            int alt60=3;
            switch ( input.LA(1) ) {
            case 25:
                {
                alt60=1;
                }
                break;
            case 24:
                {
                alt60=2;
                }
                break;
            case 16:
                {
                alt60=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 60, 0, input);

                throw nvae;
            }

            switch (alt60) {
                case 1 :
                    // InternalXScript.g:5733:6: lv_op_12_1= '<'
                    {
                    lv_op_12_1=(Token)match(input,25,FOLLOW_46); 

                    						newLeafNode(lv_op_12_1, grammarAccess.getXVarCompareStreakTypeAccess().getOpLessThanSignKeyword_12_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_12_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5744:6: lv_op_12_2= '>'
                    {
                    lv_op_12_2=(Token)match(input,24,FOLLOW_46); 

                    						newLeafNode(lv_op_12_2, grammarAccess.getXVarCompareStreakTypeAccess().getOpGreaterThanSignKeyword_12_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_12_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5755:6: lv_op_12_3= '='
                    {
                    lv_op_12_3=(Token)match(input,16,FOLLOW_46); 

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

            // InternalXScript.g:5768:3: ( (lv_test_13_0= RULE_DOUBLE ) )
            // InternalXScript.g:5769:4: (lv_test_13_0= RULE_DOUBLE )
            {
            // InternalXScript.g:5769:4: (lv_test_13_0= RULE_DOUBLE )
            // InternalXScript.g:5770:5: lv_test_13_0= RULE_DOUBLE
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
    // InternalXScript.g:5794:1: entryRuleXSlrAvgExpType returns [EObject current=null] : iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF ;
    public final EObject entryRuleXSlrAvgExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSlrAvgExpType = null;


        try {
            // InternalXScript.g:5794:55: (iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF )
            // InternalXScript.g:5795:2: iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF
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
    // InternalXScript.g:5801:1: ruleXSlrAvgExpType returns [EObject current=null] : ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' ) ;
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
            // InternalXScript.g:5807:2: ( ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' ) )
            // InternalXScript.g:5808:2: ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:5808:2: ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' )
            // InternalXScript.g:5809:3: () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')'
            {
            // InternalXScript.g:5809:3: ()
            // InternalXScript.g:5810:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSlrAvgExpTypeAccess().getXSlrAvgExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,74,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSlrAvgExpTypeAccess().getSlrAvgKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSlrAvgExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5824:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5825:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5825:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5826:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSlrAvgExpTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXSlrAvgExpTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_4, grammarAccess.getXSlrAvgExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5841:3: ( (lv_startValue_5_0= ruleXExpressionType ) )
            // InternalXScript.g:5842:4: (lv_startValue_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:5842:4: (lv_startValue_5_0= ruleXExpressionType )
            // InternalXScript.g:5843:5: lv_startValue_5_0= ruleXExpressionType
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

            otherlv_6=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_6, grammarAccess.getXSlrAvgExpTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:5864:3: ( (lv_endValue_7_0= ruleXExpressionType ) )
            // InternalXScript.g:5865:4: (lv_endValue_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:5865:4: (lv_endValue_7_0= ruleXExpressionType )
            // InternalXScript.g:5866:5: lv_endValue_7_0= ruleXExpressionType
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
    // InternalXScript.g:5891:1: entryRuleXLastSignalTriggerType returns [EObject current=null] : iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF ;
    public final EObject entryRuleXLastSignalTriggerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXLastSignalTriggerType = null;


        try {
            // InternalXScript.g:5891:63: (iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF )
            // InternalXScript.g:5892:2: iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF
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
    // InternalXScript.g:5898:1: ruleXLastSignalTriggerType returns [EObject current=null] : ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' ) ;
    public final EObject ruleXLastSignalTriggerType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalXScript.g:5904:2: ( ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' ) )
            // InternalXScript.g:5905:2: ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:5905:2: ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' )
            // InternalXScript.g:5906:3: () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')'
            {
            // InternalXScript.g:5906:3: ()
            // InternalXScript.g:5907:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXLastSignalTriggerTypeAccess().getXLastSignalTriggerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,75,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXLastSignalTriggerTypeAccess().getLstKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXLastSignalTriggerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5921:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5922:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5922:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5923:5: otherlv_3= RULE_ID
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
    // InternalXScript.g:5942:1: entryRuleXSignalTriggerCountType returns [EObject current=null] : iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF ;
    public final EObject entryRuleXSignalTriggerCountType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalTriggerCountType = null;


        try {
            // InternalXScript.g:5942:64: (iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF )
            // InternalXScript.g:5943:2: iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF
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
    // InternalXScript.g:5949:1: ruleXSignalTriggerCountType returns [EObject current=null] : ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:5955:2: ( ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' ) )
            // InternalXScript.g:5956:2: ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:5956:2: ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' )
            // InternalXScript.g:5957:3: () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')'
            {
            // InternalXScript.g:5957:3: ()
            // InternalXScript.g:5958:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalTriggerCountTypeAccess().getXSignalTriggerCountTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,76,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalTriggerCountTypeAccess().getStcKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalTriggerCountTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5972:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5973:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5973:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5974:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:5989:3: ( (lv_lookback_5_0= RULE_INT ) )
            // InternalXScript.g:5990:4: (lv_lookback_5_0= RULE_INT )
            {
            // InternalXScript.g:5990:4: (lv_lookback_5_0= RULE_INT )
            // InternalXScript.g:5991:5: lv_lookback_5_0= RULE_INT
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
    // InternalXScript.g:6015:1: entryRuleXVarianceAverageType returns [EObject current=null] : iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF ;
    public final EObject entryRuleXVarianceAverageType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarianceAverageType = null;


        try {
            // InternalXScript.g:6015:61: (iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF )
            // InternalXScript.g:6016:2: iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF
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
    // InternalXScript.g:6022:1: ruleXVarianceAverageType returns [EObject current=null] : ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) ;
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
            // InternalXScript.g:6028:2: ( ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) )
            // InternalXScript.g:6029:2: ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            {
            // InternalXScript.g:6029:2: ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            // InternalXScript.g:6030:3: () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')'
            {
            // InternalXScript.g:6030:3: ()
            // InternalXScript.g:6031:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarianceAverageTypeAccess().getXVarianceAverageTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,77,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarianceAverageTypeAccess().getVarAvgKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarianceAverageTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6045:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6046:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6046:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6047:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:6062:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:6063:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:6063:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:6064:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarianceAverageTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarianceAverageTypeAccess().getCompareVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarianceAverageTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6079:3: ( (lv_start_7_0= ruleXExpressionType ) )
            // InternalXScript.g:6080:4: (lv_start_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:6080:4: (lv_start_7_0= ruleXExpressionType )
            // InternalXScript.g:6081:5: lv_start_7_0= ruleXExpressionType
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

            otherlv_8=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarianceAverageTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:6102:3: ( (lv_end_9_0= ruleXExpressionType ) )
            // InternalXScript.g:6103:4: (lv_end_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:6103:4: (lv_end_9_0= ruleXExpressionType )
            // InternalXScript.g:6104:5: lv_end_9_0= ruleXExpressionType
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
    // InternalXScript.g:6129:1: entryRuleXVarianceMaxType returns [EObject current=null] : iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF ;
    public final EObject entryRuleXVarianceMaxType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarianceMaxType = null;


        try {
            // InternalXScript.g:6129:57: (iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF )
            // InternalXScript.g:6130:2: iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF
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
    // InternalXScript.g:6136:1: ruleXVarianceMaxType returns [EObject current=null] : ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) ;
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
            // InternalXScript.g:6142:2: ( ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) )
            // InternalXScript.g:6143:2: ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            {
            // InternalXScript.g:6143:2: ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            // InternalXScript.g:6144:3: () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')'
            {
            // InternalXScript.g:6144:3: ()
            // InternalXScript.g:6145:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarianceMaxTypeAccess().getXVarianceMaxTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,78,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarianceMaxTypeAccess().getVarMaxKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarianceMaxTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6159:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6160:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6160:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6161:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:6176:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:6177:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:6177:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:6178:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarianceMaxTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarianceMaxTypeAccess().getCompareVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarianceMaxTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6193:3: ( (lv_start_7_0= ruleXExpressionType ) )
            // InternalXScript.g:6194:4: (lv_start_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:6194:4: (lv_start_7_0= ruleXExpressionType )
            // InternalXScript.g:6195:5: lv_start_7_0= ruleXExpressionType
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

            otherlv_8=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarianceMaxTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:6216:3: ( (lv_end_9_0= ruleXExpressionType ) )
            // InternalXScript.g:6217:4: (lv_end_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:6217:4: (lv_end_9_0= ruleXExpressionType )
            // InternalXScript.g:6218:5: lv_end_9_0= ruleXExpressionType
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
    // InternalXScript.g:6243:1: entryRuleXRocExpType returns [EObject current=null] : iv_ruleXRocExpType= ruleXRocExpType EOF ;
    public final EObject entryRuleXRocExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXRocExpType = null;


        try {
            // InternalXScript.g:6243:52: (iv_ruleXRocExpType= ruleXRocExpType EOF )
            // InternalXScript.g:6244:2: iv_ruleXRocExpType= ruleXRocExpType EOF
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
    // InternalXScript.g:6250:1: ruleXRocExpType returns [EObject current=null] : ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:6256:2: ( ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:6257:2: ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:6257:2: ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:6258:3: () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:6258:3: ()
            // InternalXScript.g:6259:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXRocExpTypeAccess().getXRocExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,79,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXRocExpTypeAccess().getRoxKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_33); 

            			newLeafNode(otherlv_2, grammarAccess.getXRocExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6273:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:6274:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:6274:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:6275:5: lv_value1_3_0= ruleXExpressionType
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

            otherlv_4=(Token)match(input,14,FOLLOW_33); 

            			newLeafNode(otherlv_4, grammarAccess.getXRocExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6296:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:6297:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:6297:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:6298:5: lv_value2_5_0= ruleXExpressionType
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


    // $ANTLR start "ruleStreamTimeUnit"
    // InternalXScript.g:6323:1: ruleStreamTimeUnit returns [Enumerator current=null] : ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) ) ;
    public final Enumerator ruleStreamTimeUnit() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalXScript.g:6329:2: ( ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) ) )
            // InternalXScript.g:6330:2: ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) )
            {
            // InternalXScript.g:6330:2: ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) )
            int alt61=3;
            switch ( input.LA(1) ) {
            case 80:
                {
                alt61=1;
                }
                break;
            case 81:
                {
                alt61=2;
                }
                break;
            case 82:
                {
                alt61=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }

            switch (alt61) {
                case 1 :
                    // InternalXScript.g:6331:3: (enumLiteral_0= 'SEC' )
                    {
                    // InternalXScript.g:6331:3: (enumLiteral_0= 'SEC' )
                    // InternalXScript.g:6332:4: enumLiteral_0= 'SEC'
                    {
                    enumLiteral_0=(Token)match(input,80,FOLLOW_2); 

                    				current = grammarAccess.getStreamTimeUnitAccess().getSecondEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getStreamTimeUnitAccess().getSecondEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:6339:3: (enumLiteral_1= 'MIN' )
                    {
                    // InternalXScript.g:6339:3: (enumLiteral_1= 'MIN' )
                    // InternalXScript.g:6340:4: enumLiteral_1= 'MIN'
                    {
                    enumLiteral_1=(Token)match(input,81,FOLLOW_2); 

                    				current = grammarAccess.getStreamTimeUnitAccess().getMinuteEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getStreamTimeUnitAccess().getMinuteEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:6347:3: (enumLiteral_2= 'HOUR' )
                    {
                    // InternalXScript.g:6347:3: (enumLiteral_2= 'HOUR' )
                    // InternalXScript.g:6348:4: enumLiteral_2= 'HOUR'
                    {
                    enumLiteral_2=(Token)match(input,82,FOLLOW_2); 

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
    // InternalXScript.g:6358:1: ruleDataType returns [Enumerator current=null] : ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) ) ;
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
            // InternalXScript.g:6364:2: ( ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) ) )
            // InternalXScript.g:6365:2: ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) )
            {
            // InternalXScript.g:6365:2: ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) )
            int alt62=8;
            switch ( input.LA(1) ) {
            case 83:
                {
                alt62=1;
                }
                break;
            case 84:
                {
                alt62=2;
                }
                break;
            case 85:
                {
                alt62=3;
                }
                break;
            case 86:
                {
                alt62=4;
                }
                break;
            case 87:
                {
                alt62=5;
                }
                break;
            case 88:
                {
                alt62=6;
                }
                break;
            case 89:
                {
                alt62=7;
                }
                break;
            case 90:
                {
                alt62=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }

            switch (alt62) {
                case 1 :
                    // InternalXScript.g:6366:3: (enumLiteral_0= 'STR' )
                    {
                    // InternalXScript.g:6366:3: (enumLiteral_0= 'STR' )
                    // InternalXScript.g:6367:4: enumLiteral_0= 'STR'
                    {
                    enumLiteral_0=(Token)match(input,83,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getSTREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getDataTypeAccess().getSTREnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:6374:3: (enumLiteral_1= 'INT' )
                    {
                    // InternalXScript.g:6374:3: (enumLiteral_1= 'INT' )
                    // InternalXScript.g:6375:4: enumLiteral_1= 'INT'
                    {
                    enumLiteral_1=(Token)match(input,84,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getINTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getDataTypeAccess().getINTEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:6382:3: (enumLiteral_2= 'BOOl' )
                    {
                    // InternalXScript.g:6382:3: (enumLiteral_2= 'BOOl' )
                    // InternalXScript.g:6383:4: enumLiteral_2= 'BOOl'
                    {
                    enumLiteral_2=(Token)match(input,85,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getBOOlEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getDataTypeAccess().getBOOlEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalXScript.g:6390:3: (enumLiteral_3= 'T' )
                    {
                    // InternalXScript.g:6390:3: (enumLiteral_3= 'T' )
                    // InternalXScript.g:6391:4: enumLiteral_3= 'T'
                    {
                    enumLiteral_3=(Token)match(input,86,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getDataTypeAccess().getTEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalXScript.g:6398:3: (enumLiteral_4= 'DT' )
                    {
                    // InternalXScript.g:6398:3: (enumLiteral_4= 'DT' )
                    // InternalXScript.g:6399:4: enumLiteral_4= 'DT'
                    {
                    enumLiteral_4=(Token)match(input,87,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getDataTypeAccess().getDTEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalXScript.g:6406:3: (enumLiteral_5= 'DATE' )
                    {
                    // InternalXScript.g:6406:3: (enumLiteral_5= 'DATE' )
                    // InternalXScript.g:6407:4: enumLiteral_5= 'DATE'
                    {
                    enumLiteral_5=(Token)match(input,88,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDATEEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getDataTypeAccess().getDATEEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalXScript.g:6414:3: (enumLiteral_6= 'DUB' )
                    {
                    // InternalXScript.g:6414:3: (enumLiteral_6= 'DUB' )
                    // InternalXScript.g:6415:4: enumLiteral_6= 'DUB'
                    {
                    enumLiteral_6=(Token)match(input,89,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDUBEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getDataTypeAccess().getDUBEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalXScript.g:6422:3: (enumLiteral_7= 'LONG' )
                    {
                    // InternalXScript.g:6422:3: (enumLiteral_7= 'LONG' )
                    // InternalXScript.g:6423:4: enumLiteral_7= 'LONG'
                    {
                    enumLiteral_7=(Token)match(input,90,FOLLOW_2); 

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

    // Delegated rules


    protected DFA15 dfa15 = new DFA15(this);
    protected DFA27 dfa27 = new DFA27(this);
    protected DFA51 dfa51 = new DFA51(this);
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
    static final String dfa_9s = "\1\100\6\uffff\1\70\11\uffff";
    static final String dfa_10s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\1\13\1\15\1\16\1\17\1\10\1\7\1\14\1\12";
    static final String dfa_11s = "\21\uffff}>";
    static final String[] dfa_12s = {
            "\1\7\52\uffff\1\10\1\uffff\1\2\1\uffff\1\1\1\4\1\5\1\6\2\uffff\1\11\1\12\1\3\2\uffff\1\14\1\uffff\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\16\2\uffff\1\15\46\uffff\1\17\1\20",
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

    class DFA27 extends DFA {

        public DFA27(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 27;
            this.eot = dfa_7;
            this.eof = dfa_7;
            this.min = dfa_8;
            this.max = dfa_9;
            this.accept = dfa_10;
            this.special = dfa_11;
            this.transition = dfa_12;
        }
        public String getDescription() {
            return "2562:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )";
        }
    }
    static final String dfa_13s = "\24\uffff";
    static final String dfa_14s = "\7\uffff\1\21\14\uffff";
    static final String dfa_15s = "\1\4\6\uffff\1\15\14\uffff";
    static final String dfa_16s = "\1\117\6\uffff\1\43\14\uffff";
    static final String dfa_17s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\10\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\7\1\11\1\12";
    static final String dfa_18s = "\24\uffff}>";
    static final String[] dfa_19s = {
            "\1\7\1\2\1\1\1\3\27\uffff\2\4\3\uffff\1\10\5\uffff\1\6\26\uffff\1\5\1\11\5\uffff\1\12\1\uffff\1\13\1\14\1\15\1\16\1\20\1\17",
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

    class DFA51 extends DFA {

        public DFA51(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 51;
            this.eot = dfa_13;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "4548:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0001A00000001002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000000L,0x0000000007F80000L});
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
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000000L,0x0000000000070000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000003010000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000400000020000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000002000002000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0006004000000000L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x00000411C00020F0L,0x000000000000FD06L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x4E7E804000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x00000411C00220F0L,0x000000000000FD06L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000000008080L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0080000000000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x00000411C000A0F0L,0x000000000000FD06L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x3000000000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0xCE7E804000000010L,0x0000000000000001L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000018L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x00000000000000E0L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000000000000L,0x00000000000002E0L});

}