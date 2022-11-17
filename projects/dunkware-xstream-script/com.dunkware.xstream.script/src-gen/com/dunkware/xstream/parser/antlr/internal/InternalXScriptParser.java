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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_DOUBLE", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'svar'", "'('", "','", "')'", "'='", "';'", "'tvar'", "'||'", "'&&'", "'=='", "'!='", "'>='", "'<='", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", "'!'", "'true'", "'false'", "'tick'", "'['", "']'", "'exp'", "'{'", "'}'", "'snapshot'", "'roc'", "'avg'", "'sub'", "'relativeDays'", "'relativeTime'", "'today'", "'varAggSession'", "'varAggHistory'", "'sigCountSession'", "'sigCountHistory'", "'signal'", "'class'", "'var'", "'function'", "'return'", "'signalListener'", "'streamVarListener'", "'functionRunner'", "'++'", "'--'", "'setStreamVar'", "'debug'", "'if'", "'elseif'", "'else'", "'whilst'", "'break'", "'sleep'", "'percentChange'", "'columnStrk'", "'bwd'", "'fwd'", "'sum'", "'diff'", "'value'", "'columnPairStrk'", "'variance'", "'slrAvg'", "'lst'", "'stc'", "'varAvg'", "'varMax'", "'rox'", "'HIGH'", "'LOW'", "'SEC'", "'MIN'", "'HOUR'", "'STR'", "'INT'", "'BOOl'", "'T'", "'DT'", "'DATE'", "'DUB'", "'LONG'"
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
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__90=90;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int T__95=95;
    public static final int T__96=96;
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
    public static final int T__85=85;
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

                if ( (LA1_0==12||LA1_0==18||(LA1_0>=51 && LA1_0<=52)) ) {
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
    // InternalXScript.g:133:1: ruleCoreAbstractElement returns [EObject current=null] : (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType ) ;
    public final EObject ruleCoreAbstractElement() throws RecognitionException {
        EObject current = null;

        EObject this_VarType_0 = null;

        EObject this_XClassType_1 = null;

        EObject this_SignalType_2 = null;



        	enterRule();

        try {
            // InternalXScript.g:139:2: ( (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType ) )
            // InternalXScript.g:140:2: (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType )
            {
            // InternalXScript.g:140:2: (this_VarType_0= ruleVarType | this_XClassType_1= ruleXClassType | this_SignalType_2= ruleSignalType )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 12:
            case 18:
                {
                alt2=1;
                }
                break;
            case 52:
                {
                alt2=2;
                }
                break;
            case 51:
                {
                alt2=3;
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
    // InternalXScript.g:171:1: entryRuleVarType returns [EObject current=null] : iv_ruleVarType= ruleVarType EOF ;
    public final EObject entryRuleVarType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarType = null;


        try {
            // InternalXScript.g:171:48: (iv_ruleVarType= ruleVarType EOF )
            // InternalXScript.g:172:2: iv_ruleVarType= ruleVarType EOF
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
    // InternalXScript.g:178:1: ruleVarType returns [EObject current=null] : (this_VarStoreType_0= ruleVarStoreType | this_VarTransType_1= ruleVarTransType ) ;
    public final EObject ruleVarType() throws RecognitionException {
        EObject current = null;

        EObject this_VarStoreType_0 = null;

        EObject this_VarTransType_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:184:2: ( (this_VarStoreType_0= ruleVarStoreType | this_VarTransType_1= ruleVarTransType ) )
            // InternalXScript.g:185:2: (this_VarStoreType_0= ruleVarStoreType | this_VarTransType_1= ruleVarTransType )
            {
            // InternalXScript.g:185:2: (this_VarStoreType_0= ruleVarStoreType | this_VarTransType_1= ruleVarTransType )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==12) ) {
                alt3=1;
            }
            else if ( (LA3_0==18) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalXScript.g:186:3: this_VarStoreType_0= ruleVarStoreType
                    {

                    			newCompositeNode(grammarAccess.getVarTypeAccess().getVarStoreTypeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarStoreType_0=ruleVarStoreType();

                    state._fsp--;


                    			current = this_VarStoreType_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalXScript.g:195:3: this_VarTransType_1= ruleVarTransType
                    {

                    			newCompositeNode(grammarAccess.getVarTypeAccess().getVarTransTypeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarTransType_1=ruleVarTransType();

                    state._fsp--;


                    			current = this_VarTransType_1;
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
    // $ANTLR end "ruleVarType"


    // $ANTLR start "entryRuleVarStoreType"
    // InternalXScript.g:207:1: entryRuleVarStoreType returns [EObject current=null] : iv_ruleVarStoreType= ruleVarStoreType EOF ;
    public final EObject entryRuleVarStoreType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarStoreType = null;


        try {
            // InternalXScript.g:207:53: (iv_ruleVarStoreType= ruleVarStoreType EOF )
            // InternalXScript.g:208:2: iv_ruleVarStoreType= ruleVarStoreType EOF
            {
             newCompositeNode(grammarAccess.getVarStoreTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarStoreType=ruleVarStoreType();

            state._fsp--;

             current =iv_ruleVarStoreType; 
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
    // $ANTLR end "entryRuleVarStoreType"


    // $ANTLR start "ruleVarStoreType"
    // InternalXScript.g:214:1: ruleVarStoreType returns [EObject current=null] : (otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' ) ;
    public final EObject ruleVarStoreType() throws RecognitionException {
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
            // InternalXScript.g:220:2: ( (otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' ) )
            // InternalXScript.g:221:2: (otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' )
            {
            // InternalXScript.g:221:2: (otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' )
            // InternalXScript.g:222:3: otherlv_0= 'svar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getVarStoreTypeAccess().getSvarKeyword_0());
            		
            // InternalXScript.g:226:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalXScript.g:227:4: (lv_name_1_0= RULE_ID )
            {
            // InternalXScript.g:227:4: (lv_name_1_0= RULE_ID )
            // InternalXScript.g:228:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_1_0, grammarAccess.getVarStoreTypeAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVarStoreTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getVarStoreTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:248:3: ( (lv_code_3_0= RULE_INT ) )
            // InternalXScript.g:249:4: (lv_code_3_0= RULE_INT )
            {
            // InternalXScript.g:249:4: (lv_code_3_0= RULE_INT )
            // InternalXScript.g:250:5: lv_code_3_0= RULE_INT
            {
            lv_code_3_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            					newLeafNode(lv_code_3_0, grammarAccess.getVarStoreTypeAccess().getCodeINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVarStoreTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"code",
            						lv_code_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_8); 

            			newLeafNode(otherlv_4, grammarAccess.getVarStoreTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:270:3: ( (lv_type_5_0= ruleDataType ) )
            // InternalXScript.g:271:4: (lv_type_5_0= ruleDataType )
            {
            // InternalXScript.g:271:4: (lv_type_5_0= ruleDataType )
            // InternalXScript.g:272:5: lv_type_5_0= ruleDataType
            {

            					newCompositeNode(grammarAccess.getVarStoreTypeAccess().getTypeDataTypeEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_type_5_0=ruleDataType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarStoreTypeRule());
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

            			newLeafNode(otherlv_6, grammarAccess.getVarStoreTypeAccess().getRightParenthesisKeyword_6());
            		
            otherlv_7=(Token)match(input,16,FOLLOW_11); 

            			newLeafNode(otherlv_7, grammarAccess.getVarStoreTypeAccess().getEqualsSignKeyword_7());
            		
            // InternalXScript.g:297:3: ( (lv_expression_8_0= ruleExpressionType ) )
            // InternalXScript.g:298:4: (lv_expression_8_0= ruleExpressionType )
            {
            // InternalXScript.g:298:4: (lv_expression_8_0= ruleExpressionType )
            // InternalXScript.g:299:5: lv_expression_8_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getVarStoreTypeAccess().getExpressionExpressionTypeParserRuleCall_8_0());
            				
            pushFollow(FOLLOW_12);
            lv_expression_8_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarStoreTypeRule());
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

            			newLeafNode(otherlv_9, grammarAccess.getVarStoreTypeAccess().getSemicolonKeyword_9());
            		

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
    // $ANTLR end "ruleVarStoreType"


    // $ANTLR start "entryRuleVarTransType"
    // InternalXScript.g:324:1: entryRuleVarTransType returns [EObject current=null] : iv_ruleVarTransType= ruleVarTransType EOF ;
    public final EObject entryRuleVarTransType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarTransType = null;


        try {
            // InternalXScript.g:324:53: (iv_ruleVarTransType= ruleVarTransType EOF )
            // InternalXScript.g:325:2: iv_ruleVarTransType= ruleVarTransType EOF
            {
             newCompositeNode(grammarAccess.getVarTransTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarTransType=ruleVarTransType();

            state._fsp--;

             current =iv_ruleVarTransType; 
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
    // $ANTLR end "entryRuleVarTransType"


    // $ANTLR start "ruleVarTransType"
    // InternalXScript.g:331:1: ruleVarTransType returns [EObject current=null] : (otherlv_0= 'tvar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' ) ;
    public final EObject ruleVarTransType() throws RecognitionException {
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
            // InternalXScript.g:337:2: ( (otherlv_0= 'tvar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' ) )
            // InternalXScript.g:338:2: (otherlv_0= 'tvar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' )
            {
            // InternalXScript.g:338:2: (otherlv_0= 'tvar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';' )
            // InternalXScript.g:339:3: otherlv_0= 'tvar' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '(' ( (lv_code_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_type_5_0= ruleDataType ) ) otherlv_6= ')' otherlv_7= '=' ( (lv_expression_8_0= ruleExpressionType ) ) otherlv_9= ';'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getVarTransTypeAccess().getTvarKeyword_0());
            		
            // InternalXScript.g:343:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalXScript.g:344:4: (lv_name_1_0= RULE_ID )
            {
            // InternalXScript.g:344:4: (lv_name_1_0= RULE_ID )
            // InternalXScript.g:345:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(lv_name_1_0, grammarAccess.getVarTransTypeAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVarTransTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getVarTransTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:365:3: ( (lv_code_3_0= RULE_INT ) )
            // InternalXScript.g:366:4: (lv_code_3_0= RULE_INT )
            {
            // InternalXScript.g:366:4: (lv_code_3_0= RULE_INT )
            // InternalXScript.g:367:5: lv_code_3_0= RULE_INT
            {
            lv_code_3_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            					newLeafNode(lv_code_3_0, grammarAccess.getVarTransTypeAccess().getCodeINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVarTransTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"code",
            						lv_code_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_8); 

            			newLeafNode(otherlv_4, grammarAccess.getVarTransTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:387:3: ( (lv_type_5_0= ruleDataType ) )
            // InternalXScript.g:388:4: (lv_type_5_0= ruleDataType )
            {
            // InternalXScript.g:388:4: (lv_type_5_0= ruleDataType )
            // InternalXScript.g:389:5: lv_type_5_0= ruleDataType
            {

            					newCompositeNode(grammarAccess.getVarTransTypeAccess().getTypeDataTypeEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_type_5_0=ruleDataType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarTransTypeRule());
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

            			newLeafNode(otherlv_6, grammarAccess.getVarTransTypeAccess().getRightParenthesisKeyword_6());
            		
            otherlv_7=(Token)match(input,16,FOLLOW_11); 

            			newLeafNode(otherlv_7, grammarAccess.getVarTransTypeAccess().getEqualsSignKeyword_7());
            		
            // InternalXScript.g:414:3: ( (lv_expression_8_0= ruleExpressionType ) )
            // InternalXScript.g:415:4: (lv_expression_8_0= ruleExpressionType )
            {
            // InternalXScript.g:415:4: (lv_expression_8_0= ruleExpressionType )
            // InternalXScript.g:416:5: lv_expression_8_0= ruleExpressionType
            {

            					newCompositeNode(grammarAccess.getVarTransTypeAccess().getExpressionExpressionTypeParserRuleCall_8_0());
            				
            pushFollow(FOLLOW_12);
            lv_expression_8_0=ruleExpressionType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarTransTypeRule());
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

            			newLeafNode(otherlv_9, grammarAccess.getVarTransTypeAccess().getSemicolonKeyword_9());
            		

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
    // $ANTLR end "ruleVarTransType"


    // $ANTLR start "entryRuleExpressionType"
    // InternalXScript.g:441:1: entryRuleExpressionType returns [EObject current=null] : iv_ruleExpressionType= ruleExpressionType EOF ;
    public final EObject entryRuleExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionType = null;


        try {
            // InternalXScript.g:441:55: (iv_ruleExpressionType= ruleExpressionType EOF )
            // InternalXScript.g:442:2: iv_ruleExpressionType= ruleExpressionType EOF
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
    // InternalXScript.g:448:1: ruleExpressionType returns [EObject current=null] : this_OrType_0= ruleOrType ;
    public final EObject ruleExpressionType() throws RecognitionException {
        EObject current = null;

        EObject this_OrType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:454:2: (this_OrType_0= ruleOrType )
            // InternalXScript.g:455:2: this_OrType_0= ruleOrType
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
    // InternalXScript.g:466:1: entryRuleOrType returns [EObject current=null] : iv_ruleOrType= ruleOrType EOF ;
    public final EObject entryRuleOrType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOrType = null;


        try {
            // InternalXScript.g:466:47: (iv_ruleOrType= ruleOrType EOF )
            // InternalXScript.g:467:2: iv_ruleOrType= ruleOrType EOF
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
    // InternalXScript.g:473:1: ruleOrType returns [EObject current=null] : (this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )* ) ;
    public final EObject ruleOrType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_AndType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:479:2: ( (this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )* ) )
            // InternalXScript.g:480:2: (this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )* )
            {
            // InternalXScript.g:480:2: (this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )* )
            // InternalXScript.g:481:3: this_AndType_0= ruleAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )*
            {

            			newCompositeNode(grammarAccess.getOrTypeAccess().getAndTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_13);
            this_AndType_0=ruleAndType();

            state._fsp--;


            			current = this_AndType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:489:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==19) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalXScript.g:490:4: () otherlv_2= '||' ( (lv_right_3_0= ruleAndType ) )
            	    {
            	    // InternalXScript.g:490:4: ()
            	    // InternalXScript.g:491:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getOrTypeAccess().getOrTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,19,FOLLOW_11); 

            	    				newLeafNode(otherlv_2, grammarAccess.getOrTypeAccess().getVerticalLineVerticalLineKeyword_1_1());
            	    			
            	    // InternalXScript.g:501:4: ( (lv_right_3_0= ruleAndType ) )
            	    // InternalXScript.g:502:5: (lv_right_3_0= ruleAndType )
            	    {
            	    // InternalXScript.g:502:5: (lv_right_3_0= ruleAndType )
            	    // InternalXScript.g:503:6: lv_right_3_0= ruleAndType
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
    // $ANTLR end "ruleOrType"


    // $ANTLR start "entryRuleAndType"
    // InternalXScript.g:525:1: entryRuleAndType returns [EObject current=null] : iv_ruleAndType= ruleAndType EOF ;
    public final EObject entryRuleAndType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndType = null;


        try {
            // InternalXScript.g:525:48: (iv_ruleAndType= ruleAndType EOF )
            // InternalXScript.g:526:2: iv_ruleAndType= ruleAndType EOF
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
    // InternalXScript.g:532:1: ruleAndType returns [EObject current=null] : (this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )* ) ;
    public final EObject ruleAndType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_EqualityType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:538:2: ( (this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )* ) )
            // InternalXScript.g:539:2: (this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )* )
            {
            // InternalXScript.g:539:2: (this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )* )
            // InternalXScript.g:540:3: this_EqualityType_0= ruleEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )*
            {

            			newCompositeNode(grammarAccess.getAndTypeAccess().getEqualityTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_14);
            this_EqualityType_0=ruleEqualityType();

            state._fsp--;


            			current = this_EqualityType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:548:3: ( () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==20) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalXScript.g:549:4: () otherlv_2= '&&' ( (lv_right_3_0= ruleEqualityType ) )
            	    {
            	    // InternalXScript.g:549:4: ()
            	    // InternalXScript.g:550:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getAndTypeAccess().getAndTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,20,FOLLOW_11); 

            	    				newLeafNode(otherlv_2, grammarAccess.getAndTypeAccess().getAmpersandAmpersandKeyword_1_1());
            	    			
            	    // InternalXScript.g:560:4: ( (lv_right_3_0= ruleEqualityType ) )
            	    // InternalXScript.g:561:5: (lv_right_3_0= ruleEqualityType )
            	    {
            	    // InternalXScript.g:561:5: (lv_right_3_0= ruleEqualityType )
            	    // InternalXScript.g:562:6: lv_right_3_0= ruleEqualityType
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
            	    break loop5;
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
    // InternalXScript.g:584:1: entryRuleEqualityType returns [EObject current=null] : iv_ruleEqualityType= ruleEqualityType EOF ;
    public final EObject entryRuleEqualityType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEqualityType = null;


        try {
            // InternalXScript.g:584:53: (iv_ruleEqualityType= ruleEqualityType EOF )
            // InternalXScript.g:585:2: iv_ruleEqualityType= ruleEqualityType EOF
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
    // InternalXScript.g:591:1: ruleEqualityType returns [EObject current=null] : (this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )* ) ;
    public final EObject ruleEqualityType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_ComparisonType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:597:2: ( (this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )* ) )
            // InternalXScript.g:598:2: (this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )* )
            {
            // InternalXScript.g:598:2: (this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )* )
            // InternalXScript.g:599:3: this_ComparisonType_0= ruleComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )*
            {

            			newCompositeNode(grammarAccess.getEqualityTypeAccess().getComparisonTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_15);
            this_ComparisonType_0=ruleComparisonType();

            state._fsp--;


            			current = this_ComparisonType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:607:3: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=21 && LA7_0<=22)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalXScript.g:608:4: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleComparisonType ) )
            	    {
            	    // InternalXScript.g:608:4: ()
            	    // InternalXScript.g:609:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getEqualityTypeAccess().getEqualityTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:615:4: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // InternalXScript.g:616:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // InternalXScript.g:616:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // InternalXScript.g:617:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // InternalXScript.g:617:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    int alt6=2;
            	    int LA6_0 = input.LA(1);

            	    if ( (LA6_0==21) ) {
            	        alt6=1;
            	    }
            	    else if ( (LA6_0==22) ) {
            	        alt6=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 6, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt6) {
            	        case 1 :
            	            // InternalXScript.g:618:7: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,21,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getEqualityTypeAccess().getOpEqualsSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getEqualityTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:629:7: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,22,FOLLOW_11); 

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

            	    // InternalXScript.g:642:4: ( (lv_right_3_0= ruleComparisonType ) )
            	    // InternalXScript.g:643:5: (lv_right_3_0= ruleComparisonType )
            	    {
            	    // InternalXScript.g:643:5: (lv_right_3_0= ruleComparisonType )
            	    // InternalXScript.g:644:6: lv_right_3_0= ruleComparisonType
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
            	    break loop7;
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
    // InternalXScript.g:666:1: entryRuleComparisonType returns [EObject current=null] : iv_ruleComparisonType= ruleComparisonType EOF ;
    public final EObject entryRuleComparisonType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComparisonType = null;


        try {
            // InternalXScript.g:666:55: (iv_ruleComparisonType= ruleComparisonType EOF )
            // InternalXScript.g:667:2: iv_ruleComparisonType= ruleComparisonType EOF
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
    // InternalXScript.g:673:1: ruleComparisonType returns [EObject current=null] : (this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )* ) ;
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
            // InternalXScript.g:679:2: ( (this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )* ) )
            // InternalXScript.g:680:2: (this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )* )
            {
            // InternalXScript.g:680:2: (this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )* )
            // InternalXScript.g:681:3: this_PlusOrMinusType_0= rulePlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )*
            {

            			newCompositeNode(grammarAccess.getComparisonTypeAccess().getPlusOrMinusTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_16);
            this_PlusOrMinusType_0=rulePlusOrMinusType();

            state._fsp--;


            			current = this_PlusOrMinusType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:689:3: ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=23 && LA9_0<=26)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalXScript.g:690:4: () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= rulePlusOrMinusType ) )
            	    {
            	    // InternalXScript.g:690:4: ()
            	    // InternalXScript.g:691:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getComparisonTypeAccess().getComparisonTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:697:4: ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) )
            	    // InternalXScript.g:698:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    {
            	    // InternalXScript.g:698:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    // InternalXScript.g:699:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    {
            	    // InternalXScript.g:699:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    int alt8=4;
            	    switch ( input.LA(1) ) {
            	    case 23:
            	        {
            	        alt8=1;
            	        }
            	        break;
            	    case 24:
            	        {
            	        alt8=2;
            	        }
            	        break;
            	    case 25:
            	        {
            	        alt8=3;
            	        }
            	        break;
            	    case 26:
            	        {
            	        alt8=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 8, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt8) {
            	        case 1 :
            	            // InternalXScript.g:700:7: lv_op_2_1= '>='
            	            {
            	            lv_op_2_1=(Token)match(input,23,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getComparisonTypeAccess().getOpGreaterThanSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:711:7: lv_op_2_2= '<='
            	            {
            	            lv_op_2_2=(Token)match(input,24,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getComparisonTypeAccess().getOpLessThanSignEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalXScript.g:722:7: lv_op_2_3= '>'
            	            {
            	            lv_op_2_3=(Token)match(input,25,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_3, grammarAccess.getComparisonTypeAccess().getOpGreaterThanSignKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalXScript.g:733:7: lv_op_2_4= '<'
            	            {
            	            lv_op_2_4=(Token)match(input,26,FOLLOW_11); 

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

            	    // InternalXScript.g:746:4: ( (lv_right_3_0= rulePlusOrMinusType ) )
            	    // InternalXScript.g:747:5: (lv_right_3_0= rulePlusOrMinusType )
            	    {
            	    // InternalXScript.g:747:5: (lv_right_3_0= rulePlusOrMinusType )
            	    // InternalXScript.g:748:6: lv_right_3_0= rulePlusOrMinusType
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
            	    break loop9;
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
    // InternalXScript.g:770:1: entryRulePlusOrMinusType returns [EObject current=null] : iv_rulePlusOrMinusType= rulePlusOrMinusType EOF ;
    public final EObject entryRulePlusOrMinusType() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePlusOrMinusType = null;


        try {
            // InternalXScript.g:770:56: (iv_rulePlusOrMinusType= rulePlusOrMinusType EOF )
            // InternalXScript.g:771:2: iv_rulePlusOrMinusType= rulePlusOrMinusType EOF
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
    // InternalXScript.g:777:1: rulePlusOrMinusType returns [EObject current=null] : (this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )* ) ;
    public final EObject rulePlusOrMinusType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_MulOrDivType_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:783:2: ( (this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )* ) )
            // InternalXScript.g:784:2: (this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )* )
            {
            // InternalXScript.g:784:2: (this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )* )
            // InternalXScript.g:785:3: this_MulOrDivType_0= ruleMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )*
            {

            			newCompositeNode(grammarAccess.getPlusOrMinusTypeAccess().getMulOrDivTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_17);
            this_MulOrDivType_0=ruleMulOrDivType();

            state._fsp--;


            			current = this_MulOrDivType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:793:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=27 && LA11_0<=28)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalXScript.g:794:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleMulOrDivType ) )
            	    {
            	    // InternalXScript.g:794:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==27) ) {
            	        alt10=1;
            	    }
            	    else if ( (LA10_0==28) ) {
            	        alt10=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // InternalXScript.g:795:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalXScript.g:795:5: ( () otherlv_2= '+' )
            	            // InternalXScript.g:796:6: () otherlv_2= '+'
            	            {
            	            // InternalXScript.g:796:6: ()
            	            // InternalXScript.g:797:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getPlusOrMinusTypeAccess().getPlusTypeLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,27,FOLLOW_11); 

            	            						newLeafNode(otherlv_2, grammarAccess.getPlusOrMinusTypeAccess().getPlusSignKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:809:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalXScript.g:809:5: ( () otherlv_4= '-' )
            	            // InternalXScript.g:810:6: () otherlv_4= '-'
            	            {
            	            // InternalXScript.g:810:6: ()
            	            // InternalXScript.g:811:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getPlusOrMinusTypeAccess().getMinusTypeLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,28,FOLLOW_11); 

            	            						newLeafNode(otherlv_4, grammarAccess.getPlusOrMinusTypeAccess().getHyphenMinusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalXScript.g:823:4: ( (lv_right_5_0= ruleMulOrDivType ) )
            	    // InternalXScript.g:824:5: (lv_right_5_0= ruleMulOrDivType )
            	    {
            	    // InternalXScript.g:824:5: (lv_right_5_0= ruleMulOrDivType )
            	    // InternalXScript.g:825:6: lv_right_5_0= ruleMulOrDivType
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
            	    break loop11;
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
    // InternalXScript.g:847:1: entryRuleMulOrDivType returns [EObject current=null] : iv_ruleMulOrDivType= ruleMulOrDivType EOF ;
    public final EObject entryRuleMulOrDivType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMulOrDivType = null;


        try {
            // InternalXScript.g:847:53: (iv_ruleMulOrDivType= ruleMulOrDivType EOF )
            // InternalXScript.g:848:2: iv_ruleMulOrDivType= ruleMulOrDivType EOF
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
    // InternalXScript.g:854:1: ruleMulOrDivType returns [EObject current=null] : (this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )* ) ;
    public final EObject ruleMulOrDivType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_PrimaryType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:860:2: ( (this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )* ) )
            // InternalXScript.g:861:2: (this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )* )
            {
            // InternalXScript.g:861:2: (this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )* )
            // InternalXScript.g:862:3: this_PrimaryType_0= rulePrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )*
            {

            			newCompositeNode(grammarAccess.getMulOrDivTypeAccess().getPrimaryTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_18);
            this_PrimaryType_0=rulePrimaryType();

            state._fsp--;


            			current = this_PrimaryType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:870:3: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>=29 && LA13_0<=30)) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalXScript.g:871:4: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= rulePrimaryType ) )
            	    {
            	    // InternalXScript.g:871:4: ()
            	    // InternalXScript.g:872:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getMulOrDivTypeAccess().getMulOrDivTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:878:4: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // InternalXScript.g:879:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // InternalXScript.g:879:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // InternalXScript.g:880:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // InternalXScript.g:880:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt12=2;
            	    int LA12_0 = input.LA(1);

            	    if ( (LA12_0==29) ) {
            	        alt12=1;
            	    }
            	    else if ( (LA12_0==30) ) {
            	        alt12=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 12, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt12) {
            	        case 1 :
            	            // InternalXScript.g:881:7: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,29,FOLLOW_11); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getMulOrDivTypeAccess().getOpAsteriskKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getMulOrDivTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:892:7: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,30,FOLLOW_11); 

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

            	    // InternalXScript.g:905:4: ( (lv_right_3_0= rulePrimaryType ) )
            	    // InternalXScript.g:906:5: (lv_right_3_0= rulePrimaryType )
            	    {
            	    // InternalXScript.g:906:5: (lv_right_3_0= rulePrimaryType )
            	    // InternalXScript.g:907:6: lv_right_3_0= rulePrimaryType
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
            	    break loop13;
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
    // InternalXScript.g:929:1: entryRulePrimaryType returns [EObject current=null] : iv_rulePrimaryType= rulePrimaryType EOF ;
    public final EObject entryRulePrimaryType() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryType = null;


        try {
            // InternalXScript.g:929:52: (iv_rulePrimaryType= rulePrimaryType EOF )
            // InternalXScript.g:930:2: iv_rulePrimaryType= rulePrimaryType EOF
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
    // InternalXScript.g:936:1: rulePrimaryType returns [EObject current=null] : ( (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) ) | this_AtomicType_6= ruleAtomicType ) ;
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
            // InternalXScript.g:942:2: ( ( (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) ) | this_AtomicType_6= ruleAtomicType ) )
            // InternalXScript.g:943:2: ( (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) ) | this_AtomicType_6= ruleAtomicType )
            {
            // InternalXScript.g:943:2: ( (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) ) | this_AtomicType_6= ruleAtomicType )
            int alt14=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt14=1;
                }
                break;
            case 31:
                {
                alt14=2;
                }
                break;
            case RULE_ID:
            case RULE_INT:
            case RULE_DOUBLE:
            case RULE_STRING:
            case 32:
            case 33:
            case 34:
            case 37:
            case 38:
            case 40:
            case 41:
            case 42:
            case 43:
            case 47:
            case 48:
            case 49:
            case 50:
                {
                alt14=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalXScript.g:944:3: (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' )
                    {
                    // InternalXScript.g:944:3: (otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')' )
                    // InternalXScript.g:945:4: otherlv_0= '(' this_ExpressionType_1= ruleExpressionType otherlv_2= ')'
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
                    // InternalXScript.g:963:3: ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) )
                    {
                    // InternalXScript.g:963:3: ( () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) ) )
                    // InternalXScript.g:964:4: () otherlv_4= '!' ( (lv_expression_5_0= rulePrimaryType ) )
                    {
                    // InternalXScript.g:964:4: ()
                    // InternalXScript.g:965:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getPrimaryTypeAccess().getNotTypeAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_4=(Token)match(input,31,FOLLOW_11); 

                    				newLeafNode(otherlv_4, grammarAccess.getPrimaryTypeAccess().getExclamationMarkKeyword_1_1());
                    			
                    // InternalXScript.g:975:4: ( (lv_expression_5_0= rulePrimaryType ) )
                    // InternalXScript.g:976:5: (lv_expression_5_0= rulePrimaryType )
                    {
                    // InternalXScript.g:976:5: (lv_expression_5_0= rulePrimaryType )
                    // InternalXScript.g:977:6: lv_expression_5_0= rulePrimaryType
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
                    // InternalXScript.g:996:3: this_AtomicType_6= ruleAtomicType
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
    // InternalXScript.g:1008:1: entryRuleAtomicType returns [EObject current=null] : iv_ruleAtomicType= ruleAtomicType EOF ;
    public final EObject entryRuleAtomicType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicType = null;


        try {
            // InternalXScript.g:1008:51: (iv_ruleAtomicType= ruleAtomicType EOF )
            // InternalXScript.g:1009:2: iv_ruleAtomicType= ruleAtomicType EOF
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
    // InternalXScript.g:1015:1: ruleAtomicType returns [EObject current=null] : this_AtomicBaseType_0= ruleAtomicBaseType ;
    public final EObject ruleAtomicType() throws RecognitionException {
        EObject current = null;

        EObject this_AtomicBaseType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1021:2: (this_AtomicBaseType_0= ruleAtomicBaseType )
            // InternalXScript.g:1022:2: this_AtomicBaseType_0= ruleAtomicBaseType
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
    // InternalXScript.g:1033:1: entryRuleAtomicBaseType returns [EObject current=null] : iv_ruleAtomicBaseType= ruleAtomicBaseType EOF ;
    public final EObject entryRuleAtomicBaseType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicBaseType = null;


        try {
            // InternalXScript.g:1033:55: (iv_ruleAtomicBaseType= ruleAtomicBaseType EOF )
            // InternalXScript.g:1034:2: iv_ruleAtomicBaseType= ruleAtomicBaseType EOF
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
    // InternalXScript.g:1040:1: ruleAtomicBaseType returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_VarAggHistoryType_17= ruleVarAggHistoryType | this_VarAggSessionType_18= ruleVarAggSessionType | this_SignalCountSession_19= ruleSignalCountSession | this_SignalCountHistory_20= ruleSignalCountHistory ) ;
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

        EObject this_VarAggHistoryType_17 = null;

        EObject this_VarAggSessionType_18 = null;

        EObject this_SignalCountSession_19 = null;

        EObject this_SignalCountHistory_20 = null;



        	enterRule();

        try {
            // InternalXScript.g:1046:2: ( ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_VarAggHistoryType_17= ruleVarAggHistoryType | this_VarAggSessionType_18= ruleVarAggSessionType | this_SignalCountSession_19= ruleSignalCountSession | this_SignalCountHistory_20= ruleSignalCountHistory ) )
            // InternalXScript.g:1047:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_VarAggHistoryType_17= ruleVarAggHistoryType | this_VarAggSessionType_18= ruleVarAggSessionType | this_SignalCountSession_19= ruleSignalCountSession | this_SignalCountHistory_20= ruleSignalCountHistory )
            {
            // InternalXScript.g:1047:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_VarAggHistoryType_17= ruleVarAggHistoryType | this_VarAggSessionType_18= ruleVarAggSessionType | this_SignalCountSession_19= ruleSignalCountSession | this_SignalCountHistory_20= ruleSignalCountHistory )
            int alt16=17;
            alt16 = dfa16.predict(input);
            switch (alt16) {
                case 1 :
                    // InternalXScript.g:1048:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    {
                    // InternalXScript.g:1048:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    // InternalXScript.g:1049:4: () ( (lv_value_1_0= RULE_DOUBLE ) )
                    {
                    // InternalXScript.g:1049:4: ()
                    // InternalXScript.g:1050:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicBaseTypeAccess().getDoubleConstantTypeAction_0_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:1056:4: ( (lv_value_1_0= RULE_DOUBLE ) )
                    // InternalXScript.g:1057:5: (lv_value_1_0= RULE_DOUBLE )
                    {
                    // InternalXScript.g:1057:5: (lv_value_1_0= RULE_DOUBLE )
                    // InternalXScript.g:1058:6: lv_value_1_0= RULE_DOUBLE
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
                    // InternalXScript.g:1076:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    {
                    // InternalXScript.g:1076:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    // InternalXScript.g:1077:4: () ( (lv_value_3_0= RULE_INT ) )
                    {
                    // InternalXScript.g:1077:4: ()
                    // InternalXScript.g:1078:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicBaseTypeAccess().getIntConstantTypeAction_1_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:1084:4: ( (lv_value_3_0= RULE_INT ) )
                    // InternalXScript.g:1085:5: (lv_value_3_0= RULE_INT )
                    {
                    // InternalXScript.g:1085:5: (lv_value_3_0= RULE_INT )
                    // InternalXScript.g:1086:6: lv_value_3_0= RULE_INT
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
                    // InternalXScript.g:1104:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    {
                    // InternalXScript.g:1104:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    // InternalXScript.g:1105:4: () ( (lv_value_5_0= RULE_STRING ) )
                    {
                    // InternalXScript.g:1105:4: ()
                    // InternalXScript.g:1106:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicBaseTypeAccess().getStringConstantTypeAction_2_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:1112:4: ( (lv_value_5_0= RULE_STRING ) )
                    // InternalXScript.g:1113:5: (lv_value_5_0= RULE_STRING )
                    {
                    // InternalXScript.g:1113:5: (lv_value_5_0= RULE_STRING )
                    // InternalXScript.g:1114:6: lv_value_5_0= RULE_STRING
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
                    // InternalXScript.g:1132:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    {
                    // InternalXScript.g:1132:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    // InternalXScript.g:1133:4: () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    {
                    // InternalXScript.g:1133:4: ()
                    // InternalXScript.g:1134:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicBaseTypeAccess().getBoolConstantTypeAction_3_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:1140:4: ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    // InternalXScript.g:1141:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    {
                    // InternalXScript.g:1141:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    // InternalXScript.g:1142:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    {
                    // InternalXScript.g:1142:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==32) ) {
                        alt15=1;
                    }
                    else if ( (LA15_0==33) ) {
                        alt15=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 15, 0, input);

                        throw nvae;
                    }
                    switch (alt15) {
                        case 1 :
                            // InternalXScript.g:1143:7: lv_value_7_1= 'true'
                            {
                            lv_value_7_1=(Token)match(input,32,FOLLOW_2); 

                            							newLeafNode(lv_value_7_1, grammarAccess.getAtomicBaseTypeAccess().getValueTrueKeyword_3_1_0_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getAtomicBaseTypeRule());
                            							}
                            							setWithLastConsumed(current, "value", lv_value_7_1, null);
                            						

                            }
                            break;
                        case 2 :
                            // InternalXScript.g:1154:7: lv_value_7_2= 'false'
                            {
                            lv_value_7_2=(Token)match(input,33,FOLLOW_2); 

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
                    // InternalXScript.g:1169:3: this_TickExpressionType_8= ruleTickExpressionType
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
                    // InternalXScript.g:1178:3: this_SnapshotExpressionType_9= ruleSnapshotExpressionType
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
                    // InternalXScript.g:1187:3: this_SetExpressionType_10= ruleSetExpressionType
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
                    // InternalXScript.g:1196:3: this_RocExpressionType_11= ruleRocExpressionType
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
                    // InternalXScript.g:1205:3: this_AvgExpressionType_12= ruleAvgExpressionType
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
                    // InternalXScript.g:1214:3: this_VariableValueExpType_13= ruleVariableValueExpType
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
                    // InternalXScript.g:1223:3: this_VariableValueRangeType_14= ruleVariableValueRangeType
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
                    // InternalXScript.g:1232:3: this_VariableValueType_15= ruleVariableValueType
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
                    // InternalXScript.g:1241:3: this_SubExpressionType_16= ruleSubExpressionType
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
                    // InternalXScript.g:1250:3: this_VarAggHistoryType_17= ruleVarAggHistoryType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getVarAggHistoryTypeParserRuleCall_13());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarAggHistoryType_17=ruleVarAggHistoryType();

                    state._fsp--;


                    			current = this_VarAggHistoryType_17;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 15 :
                    // InternalXScript.g:1259:3: this_VarAggSessionType_18= ruleVarAggSessionType
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getVarAggSessionTypeParserRuleCall_14());
                    		
                    pushFollow(FOLLOW_2);
                    this_VarAggSessionType_18=ruleVarAggSessionType();

                    state._fsp--;


                    			current = this_VarAggSessionType_18;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 16 :
                    // InternalXScript.g:1268:3: this_SignalCountSession_19= ruleSignalCountSession
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getSignalCountSessionParserRuleCall_15());
                    		
                    pushFollow(FOLLOW_2);
                    this_SignalCountSession_19=ruleSignalCountSession();

                    state._fsp--;


                    			current = this_SignalCountSession_19;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 17 :
                    // InternalXScript.g:1277:3: this_SignalCountHistory_20= ruleSignalCountHistory
                    {

                    			newCompositeNode(grammarAccess.getAtomicBaseTypeAccess().getSignalCountHistoryParserRuleCall_16());
                    		
                    pushFollow(FOLLOW_2);
                    this_SignalCountHistory_20=ruleSignalCountHistory();

                    state._fsp--;


                    			current = this_SignalCountHistory_20;
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
    // InternalXScript.g:1289:1: entryRuleTickExpressionType returns [EObject current=null] : iv_ruleTickExpressionType= ruleTickExpressionType EOF ;
    public final EObject entryRuleTickExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTickExpressionType = null;


        try {
            // InternalXScript.g:1289:59: (iv_ruleTickExpressionType= ruleTickExpressionType EOF )
            // InternalXScript.g:1290:2: iv_ruleTickExpressionType= ruleTickExpressionType EOF
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
    // InternalXScript.g:1296:1: ruleTickExpressionType returns [EObject current=null] : ( () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')' ) ;
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
            // InternalXScript.g:1302:2: ( ( () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')' ) )
            // InternalXScript.g:1303:2: ( () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:1303:2: ( () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')' )
            // InternalXScript.g:1304:3: () otherlv_1= 'tick' otherlv_2= '(' ( (lv_type_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_field_5_0= RULE_INT ) ) otherlv_6= ',' ( (lv_dataType_7_0= ruleDataType ) ) otherlv_8= ')'
            {
            // InternalXScript.g:1304:3: ()
            // InternalXScript.g:1305:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTickExpressionTypeAccess().getTickExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,34,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getTickExpressionTypeAccess().getTickKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getTickExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1319:3: ( (lv_type_3_0= RULE_INT ) )
            // InternalXScript.g:1320:4: (lv_type_3_0= RULE_INT )
            {
            // InternalXScript.g:1320:4: (lv_type_3_0= RULE_INT )
            // InternalXScript.g:1321:5: lv_type_3_0= RULE_INT
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
            		
            // InternalXScript.g:1341:3: ( (lv_field_5_0= RULE_INT ) )
            // InternalXScript.g:1342:4: (lv_field_5_0= RULE_INT )
            {
            // InternalXScript.g:1342:4: (lv_field_5_0= RULE_INT )
            // InternalXScript.g:1343:5: lv_field_5_0= RULE_INT
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
            		
            // InternalXScript.g:1363:3: ( (lv_dataType_7_0= ruleDataType ) )
            // InternalXScript.g:1364:4: (lv_dataType_7_0= ruleDataType )
            {
            // InternalXScript.g:1364:4: (lv_dataType_7_0= ruleDataType )
            // InternalXScript.g:1365:5: lv_dataType_7_0= ruleDataType
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
    // InternalXScript.g:1390:1: entryRuleVariableValueRangeType returns [EObject current=null] : iv_ruleVariableValueRangeType= ruleVariableValueRangeType EOF ;
    public final EObject entryRuleVariableValueRangeType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableValueRangeType = null;


        try {
            // InternalXScript.g:1390:63: (iv_ruleVariableValueRangeType= ruleVariableValueRangeType EOF )
            // InternalXScript.g:1391:2: iv_ruleVariableValueRangeType= ruleVariableValueRangeType EOF
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
    // InternalXScript.g:1397:1: ruleVariableValueRangeType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']' ) ;
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
            // InternalXScript.g:1403:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']' ) )
            // InternalXScript.g:1404:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']' )
            {
            // InternalXScript.g:1404:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']' )
            // InternalXScript.g:1405:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '[' ( (lv_startIndex_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_endIndex_5_0= RULE_INT ) ) otherlv_6= ']'
            {
            // InternalXScript.g:1405:3: ()
            // InternalXScript.g:1406:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableValueRangeTypeAccess().getVariableValueRangeTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:1412:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:1413:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:1413:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:1414:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableValueRangeTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(otherlv_1, grammarAccess.getVariableValueRangeTypeAccess().getTargetVarVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,35,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getVariableValueRangeTypeAccess().getLeftSquareBracketKeyword_2());
            		
            // InternalXScript.g:1429:3: ( (lv_startIndex_3_0= RULE_INT ) )
            // InternalXScript.g:1430:4: (lv_startIndex_3_0= RULE_INT )
            {
            // InternalXScript.g:1430:4: (lv_startIndex_3_0= RULE_INT )
            // InternalXScript.g:1431:5: lv_startIndex_3_0= RULE_INT
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
            		
            // InternalXScript.g:1451:3: ( (lv_endIndex_5_0= RULE_INT ) )
            // InternalXScript.g:1452:4: (lv_endIndex_5_0= RULE_INT )
            {
            // InternalXScript.g:1452:4: (lv_endIndex_5_0= RULE_INT )
            // InternalXScript.g:1453:5: lv_endIndex_5_0= RULE_INT
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

            otherlv_6=(Token)match(input,36,FOLLOW_2); 

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
    // InternalXScript.g:1477:1: entryRuleVariableValueType returns [EObject current=null] : iv_ruleVariableValueType= ruleVariableValueType EOF ;
    public final EObject entryRuleVariableValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableValueType = null;


        try {
            // InternalXScript.g:1477:58: (iv_ruleVariableValueType= ruleVariableValueType EOF )
            // InternalXScript.g:1478:2: iv_ruleVariableValueType= ruleVariableValueType EOF
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
    // InternalXScript.g:1484:1: ruleVariableValueType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' ) ) ;
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
            // InternalXScript.g:1490:2: ( ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' ) ) )
            // InternalXScript.g:1491:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' ) )
            {
            // InternalXScript.g:1491:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' ) )
            // InternalXScript.g:1492:3: () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' )
            {
            // InternalXScript.g:1492:3: ()
            // InternalXScript.g:1493:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableValueTypeAccess().getVariableValueTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:1499:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:1500:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:1500:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:1501:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVariableValueTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(otherlv_1, grammarAccess.getVariableValueTypeAccess().getVariableVarTypeCrossReference_1_0());
            				

            }


            }

            // InternalXScript.g:1512:3: (otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']' )
            // InternalXScript.g:1513:4: otherlv_2= '[' ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) ) otherlv_8= ']'
            {
            otherlv_2=(Token)match(input,35,FOLLOW_21); 

            				newLeafNode(otherlv_2, grammarAccess.getVariableValueTypeAccess().getLeftSquareBracketKeyword_2_0());
            			
            // InternalXScript.g:1517:4: ( ( (lv_indexInt_3_0= RULE_INT ) ) | (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' ) )
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==RULE_INT) ) {
                alt17=1;
            }
            else if ( (LA17_0==37) ) {
                alt17=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }
            switch (alt17) {
                case 1 :
                    // InternalXScript.g:1518:5: ( (lv_indexInt_3_0= RULE_INT ) )
                    {
                    // InternalXScript.g:1518:5: ( (lv_indexInt_3_0= RULE_INT ) )
                    // InternalXScript.g:1519:6: (lv_indexInt_3_0= RULE_INT )
                    {
                    // InternalXScript.g:1519:6: (lv_indexInt_3_0= RULE_INT )
                    // InternalXScript.g:1520:7: lv_indexInt_3_0= RULE_INT
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
                    // InternalXScript.g:1537:5: (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' )
                    {
                    // InternalXScript.g:1537:5: (otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')' )
                    // InternalXScript.g:1538:6: otherlv_4= 'exp' otherlv_5= '(' ( (lv_expType_6_0= ruleExpressionType ) ) otherlv_7= ')'
                    {
                    otherlv_4=(Token)match(input,37,FOLLOW_5); 

                    						newLeafNode(otherlv_4, grammarAccess.getVariableValueTypeAccess().getExpKeyword_2_1_1_0());
                    					
                    otherlv_5=(Token)match(input,13,FOLLOW_11); 

                    						newLeafNode(otherlv_5, grammarAccess.getVariableValueTypeAccess().getLeftParenthesisKeyword_2_1_1_1());
                    					
                    // InternalXScript.g:1546:6: ( (lv_expType_6_0= ruleExpressionType ) )
                    // InternalXScript.g:1547:7: (lv_expType_6_0= ruleExpressionType )
                    {
                    // InternalXScript.g:1547:7: (lv_expType_6_0= ruleExpressionType )
                    // InternalXScript.g:1548:8: lv_expType_6_0= ruleExpressionType
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

            otherlv_8=(Token)match(input,36,FOLLOW_2); 

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
    // InternalXScript.g:1580:1: entryRuleVariableValueExpType returns [EObject current=null] : iv_ruleVariableValueExpType= ruleVariableValueExpType EOF ;
    public final EObject entryRuleVariableValueExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableValueExpType = null;


        try {
            // InternalXScript.g:1580:61: (iv_ruleVariableValueExpType= ruleVariableValueExpType EOF )
            // InternalXScript.g:1581:2: iv_ruleVariableValueExpType= ruleVariableValueExpType EOF
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
    // InternalXScript.g:1587:1: ruleVariableValueExpType returns [EObject current=null] : ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')' ) ;
    public final EObject ruleVariableValueExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expType_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1593:2: ( ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')' ) )
            // InternalXScript.g:1594:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:1594:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            // InternalXScript.g:1595:3: () otherlv_1= 'exp' otherlv_2= '(' ( (lv_expType_3_0= ruleExpressionType ) ) otherlv_4= ')'
            {
            // InternalXScript.g:1595:3: ()
            // InternalXScript.g:1596:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableValueExpTypeAccess().getVariableValueExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,37,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getVariableValueExpTypeAccess().getExpKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getVariableValueExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1610:3: ( (lv_expType_3_0= ruleExpressionType ) )
            // InternalXScript.g:1611:4: (lv_expType_3_0= ruleExpressionType )
            {
            // InternalXScript.g:1611:4: (lv_expType_3_0= ruleExpressionType )
            // InternalXScript.g:1612:5: lv_expType_3_0= ruleExpressionType
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
    // InternalXScript.g:1637:1: entryRuleSetExpressionType returns [EObject current=null] : iv_ruleSetExpressionType= ruleSetExpressionType EOF ;
    public final EObject entryRuleSetExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSetExpressionType = null;


        try {
            // InternalXScript.g:1637:58: (iv_ruleSetExpressionType= ruleSetExpressionType EOF )
            // InternalXScript.g:1638:2: iv_ruleSetExpressionType= ruleSetExpressionType EOF
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
    // InternalXScript.g:1644:1: ruleSetExpressionType returns [EObject current=null] : ( () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}' ) ;
    public final EObject ruleSetExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_args_2_0 = null;

        EObject lv_args_4_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1650:2: ( ( () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}' ) )
            // InternalXScript.g:1651:2: ( () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}' )
            {
            // InternalXScript.g:1651:2: ( () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}' )
            // InternalXScript.g:1652:3: () otherlv_1= '{' ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )? otherlv_5= '}'
            {
            // InternalXScript.g:1652:3: ()
            // InternalXScript.g:1653:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSetExpressionTypeAccess().getSetExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,38,FOLLOW_22); 

            			newLeafNode(otherlv_1, grammarAccess.getSetExpressionTypeAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalXScript.g:1663:3: ( ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )* )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( ((LA19_0>=RULE_ID && LA19_0<=RULE_STRING)||LA19_0==13||(LA19_0>=31 && LA19_0<=34)||(LA19_0>=37 && LA19_0<=38)||(LA19_0>=40 && LA19_0<=43)||(LA19_0>=47 && LA19_0<=50)) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalXScript.g:1664:4: ( (lv_args_2_0= ruleExpressionType ) ) (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )*
                    {
                    // InternalXScript.g:1664:4: ( (lv_args_2_0= ruleExpressionType ) )
                    // InternalXScript.g:1665:5: (lv_args_2_0= ruleExpressionType )
                    {
                    // InternalXScript.g:1665:5: (lv_args_2_0= ruleExpressionType )
                    // InternalXScript.g:1666:6: lv_args_2_0= ruleExpressionType
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

                    // InternalXScript.g:1683:4: (otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==14) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalXScript.g:1684:5: otherlv_3= ',' ( (lv_args_4_0= ruleExpressionType ) )
                    	    {
                    	    otherlv_3=(Token)match(input,14,FOLLOW_11); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getSetExpressionTypeAccess().getCommaKeyword_2_1_0());
                    	    				
                    	    // InternalXScript.g:1688:5: ( (lv_args_4_0= ruleExpressionType ) )
                    	    // InternalXScript.g:1689:6: (lv_args_4_0= ruleExpressionType )
                    	    {
                    	    // InternalXScript.g:1689:6: (lv_args_4_0= ruleExpressionType )
                    	    // InternalXScript.g:1690:7: lv_args_4_0= ruleExpressionType
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
                    	    break loop18;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,39,FOLLOW_2); 

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
    // InternalXScript.g:1717:1: entryRuleSnapshotExpressionType returns [EObject current=null] : iv_ruleSnapshotExpressionType= ruleSnapshotExpressionType EOF ;
    public final EObject entryRuleSnapshotExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSnapshotExpressionType = null;


        try {
            // InternalXScript.g:1717:63: (iv_ruleSnapshotExpressionType= ruleSnapshotExpressionType EOF )
            // InternalXScript.g:1718:2: iv_ruleSnapshotExpressionType= ruleSnapshotExpressionType EOF
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
    // InternalXScript.g:1724:1: ruleSnapshotExpressionType returns [EObject current=null] : ( () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) ) ;
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
            // InternalXScript.g:1730:2: ( ( () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) ) )
            // InternalXScript.g:1731:2: ( () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) )
            {
            // InternalXScript.g:1731:2: ( () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) )
            // InternalXScript.g:1732:3: () otherlv_1= 'snapshot' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ')' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) )
            {
            // InternalXScript.g:1732:3: ()
            // InternalXScript.g:1733:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSnapshotExpressionTypeAccess().getSnapshotExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,40,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getSnapshotExpressionTypeAccess().getSnapshotKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getSnapshotExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1747:3: ( (lv_target_3_0= ruleExpressionType ) )
            // InternalXScript.g:1748:4: (lv_target_3_0= ruleExpressionType )
            {
            // InternalXScript.g:1748:4: (lv_target_3_0= ruleExpressionType )
            // InternalXScript.g:1749:5: lv_target_3_0= ruleExpressionType
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
            		
            // InternalXScript.g:1770:3: ( (lv_interval_5_0= RULE_INT ) )
            // InternalXScript.g:1771:4: (lv_interval_5_0= RULE_INT )
            {
            // InternalXScript.g:1771:4: (lv_interval_5_0= RULE_INT )
            // InternalXScript.g:1772:5: lv_interval_5_0= RULE_INT
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

            // InternalXScript.g:1788:3: ( (lv_time_6_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:1789:4: (lv_time_6_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:1789:4: (lv_time_6_0= ruleStreamTimeUnit )
            // InternalXScript.g:1790:5: lv_time_6_0= ruleStreamTimeUnit
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
    // InternalXScript.g:1811:1: entryRuleRocExpressionType returns [EObject current=null] : iv_ruleRocExpressionType= ruleRocExpressionType EOF ;
    public final EObject entryRuleRocExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRocExpressionType = null;


        try {
            // InternalXScript.g:1811:58: (iv_ruleRocExpressionType= ruleRocExpressionType EOF )
            // InternalXScript.g:1812:2: iv_ruleRocExpressionType= ruleRocExpressionType EOF
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
    // InternalXScript.g:1818:1: ruleRocExpressionType returns [EObject current=null] : ( () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:1824:2: ( ( () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:1825:2: ( () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:1825:2: ( () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:1826:3: () otherlv_1= 'roc' otherlv_2= '(' ( (lv_target_3_0= ruleExpressionType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:1826:3: ()
            // InternalXScript.g:1827:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getRocExpressionTypeAccess().getRocExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,41,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getRocExpressionTypeAccess().getRocKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getRocExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1841:3: ( (lv_target_3_0= ruleExpressionType ) )
            // InternalXScript.g:1842:4: (lv_target_3_0= ruleExpressionType )
            {
            // InternalXScript.g:1842:4: (lv_target_3_0= ruleExpressionType )
            // InternalXScript.g:1843:5: lv_target_3_0= ruleExpressionType
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
            		
            // InternalXScript.g:1864:3: ( (lv_compare_5_0= ruleExpressionType ) )
            // InternalXScript.g:1865:4: (lv_compare_5_0= ruleExpressionType )
            {
            // InternalXScript.g:1865:4: (lv_compare_5_0= ruleExpressionType )
            // InternalXScript.g:1866:5: lv_compare_5_0= ruleExpressionType
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
    // InternalXScript.g:1891:1: entryRuleAvgExpressionType returns [EObject current=null] : iv_ruleAvgExpressionType= ruleAvgExpressionType EOF ;
    public final EObject entryRuleAvgExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAvgExpressionType = null;


        try {
            // InternalXScript.g:1891:58: (iv_ruleAvgExpressionType= ruleAvgExpressionType EOF )
            // InternalXScript.g:1892:2: iv_ruleAvgExpressionType= ruleAvgExpressionType EOF
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
    // InternalXScript.g:1898:1: ruleAvgExpressionType returns [EObject current=null] : ( () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')' ) ;
    public final EObject ruleAvgExpressionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_target_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:1904:2: ( ( () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')' ) )
            // InternalXScript.g:1905:2: ( () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:1905:2: ( () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')' )
            // InternalXScript.g:1906:3: () otherlv_1= 'avg' otherlv_2= '(' ( (lv_target_3_0= ruleSetExpressionType ) ) otherlv_4= ')'
            {
            // InternalXScript.g:1906:3: ()
            // InternalXScript.g:1907:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getAvgExpressionTypeAccess().getAvgExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,42,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getAvgExpressionTypeAccess().getAvgKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_25); 

            			newLeafNode(otherlv_2, grammarAccess.getAvgExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1921:3: ( (lv_target_3_0= ruleSetExpressionType ) )
            // InternalXScript.g:1922:4: (lv_target_3_0= ruleSetExpressionType )
            {
            // InternalXScript.g:1922:4: (lv_target_3_0= ruleSetExpressionType )
            // InternalXScript.g:1923:5: lv_target_3_0= ruleSetExpressionType
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
    // InternalXScript.g:1948:1: entryRuleSubExpressionType returns [EObject current=null] : iv_ruleSubExpressionType= ruleSubExpressionType EOF ;
    public final EObject entryRuleSubExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSubExpressionType = null;


        try {
            // InternalXScript.g:1948:58: (iv_ruleSubExpressionType= ruleSubExpressionType EOF )
            // InternalXScript.g:1949:2: iv_ruleSubExpressionType= ruleSubExpressionType EOF
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
    // InternalXScript.g:1955:1: ruleSubExpressionType returns [EObject current=null] : ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:1961:2: ( ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:1962:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:1962:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')' )
            // InternalXScript.g:1963:3: () otherlv_1= 'sub' otherlv_2= '(' ( (lv_target_3_0= ruleVariableValueType ) ) otherlv_4= ',' ( (lv_compare_5_0= ruleVariableValueType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:1963:3: ()
            // InternalXScript.g:1964:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSubExpressionTypeAccess().getSubExpressionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,43,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getSubExpressionTypeAccess().getSubKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getSubExpressionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:1978:3: ( (lv_target_3_0= ruleVariableValueType ) )
            // InternalXScript.g:1979:4: (lv_target_3_0= ruleVariableValueType )
            {
            // InternalXScript.g:1979:4: (lv_target_3_0= ruleVariableValueType )
            // InternalXScript.g:1980:5: lv_target_3_0= ruleVariableValueType
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
            		
            // InternalXScript.g:2001:3: ( (lv_compare_5_0= ruleVariableValueType ) )
            // InternalXScript.g:2002:4: (lv_compare_5_0= ruleVariableValueType )
            {
            // InternalXScript.g:2002:4: (lv_compare_5_0= ruleVariableValueType )
            // InternalXScript.g:2003:5: lv_compare_5_0= ruleVariableValueType
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


    // $ANTLR start "entryRuleHistoryTimeRange"
    // InternalXScript.g:2028:1: entryRuleHistoryTimeRange returns [EObject current=null] : iv_ruleHistoryTimeRange= ruleHistoryTimeRange EOF ;
    public final EObject entryRuleHistoryTimeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHistoryTimeRange = null;


        try {
            // InternalXScript.g:2028:57: (iv_ruleHistoryTimeRange= ruleHistoryTimeRange EOF )
            // InternalXScript.g:2029:2: iv_ruleHistoryTimeRange= ruleHistoryTimeRange EOF
            {
             newCompositeNode(grammarAccess.getHistoryTimeRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHistoryTimeRange=ruleHistoryTimeRange();

            state._fsp--;

             current =iv_ruleHistoryTimeRange; 
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
    // $ANTLR end "entryRuleHistoryTimeRange"


    // $ANTLR start "ruleHistoryTimeRange"
    // InternalXScript.g:2035:1: ruleHistoryTimeRange returns [EObject current=null] : ( () otherlv_1= 'relativeDays' otherlv_2= '(' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= ')' ) ;
    public final EObject ruleHistoryTimeRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_value_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalXScript.g:2041:2: ( ( () otherlv_1= 'relativeDays' otherlv_2= '(' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= ')' ) )
            // InternalXScript.g:2042:2: ( () otherlv_1= 'relativeDays' otherlv_2= '(' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:2042:2: ( () otherlv_1= 'relativeDays' otherlv_2= '(' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= ')' )
            // InternalXScript.g:2043:3: () otherlv_1= 'relativeDays' otherlv_2= '(' ( (lv_value_3_0= RULE_INT ) ) otherlv_4= ')'
            {
            // InternalXScript.g:2043:3: ()
            // InternalXScript.g:2044:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getHistoryTimeRangeAccess().getHistoryTimeRangeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,44,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getHistoryTimeRangeAccess().getRelativeDaysKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getHistoryTimeRangeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2058:3: ( (lv_value_3_0= RULE_INT ) )
            // InternalXScript.g:2059:4: (lv_value_3_0= RULE_INT )
            {
            // InternalXScript.g:2059:4: (lv_value_3_0= RULE_INT )
            // InternalXScript.g:2060:5: lv_value_3_0= RULE_INT
            {
            lv_value_3_0=(Token)match(input,RULE_INT,FOLLOW_9); 

            					newLeafNode(lv_value_3_0, grammarAccess.getHistoryTimeRangeAccess().getValueINTTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getHistoryTimeRangeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"value",
            						lv_value_3_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getHistoryTimeRangeAccess().getRightParenthesisKeyword_4());
            		

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
    // $ANTLR end "ruleHistoryTimeRange"


    // $ANTLR start "entryRuleSessionTimeRange"
    // InternalXScript.g:2084:1: entryRuleSessionTimeRange returns [EObject current=null] : iv_ruleSessionTimeRange= ruleSessionTimeRange EOF ;
    public final EObject entryRuleSessionTimeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSessionTimeRange = null;


        try {
            // InternalXScript.g:2084:57: (iv_ruleSessionTimeRange= ruleSessionTimeRange EOF )
            // InternalXScript.g:2085:2: iv_ruleSessionTimeRange= ruleSessionTimeRange EOF
            {
             newCompositeNode(grammarAccess.getSessionTimeRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSessionTimeRange=ruleSessionTimeRange();

            state._fsp--;

             current =iv_ruleSessionTimeRange; 
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
    // $ANTLR end "entryRuleSessionTimeRange"


    // $ANTLR start "ruleSessionTimeRange"
    // InternalXScript.g:2091:1: ruleSessionTimeRange returns [EObject current=null] : (this_RelativeSessionTimeRange_0= ruleRelativeSessionTimeRange | this_TodaySessionTimeRange_1= ruleTodaySessionTimeRange ) ;
    public final EObject ruleSessionTimeRange() throws RecognitionException {
        EObject current = null;

        EObject this_RelativeSessionTimeRange_0 = null;

        EObject this_TodaySessionTimeRange_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:2097:2: ( (this_RelativeSessionTimeRange_0= ruleRelativeSessionTimeRange | this_TodaySessionTimeRange_1= ruleTodaySessionTimeRange ) )
            // InternalXScript.g:2098:2: (this_RelativeSessionTimeRange_0= ruleRelativeSessionTimeRange | this_TodaySessionTimeRange_1= ruleTodaySessionTimeRange )
            {
            // InternalXScript.g:2098:2: (this_RelativeSessionTimeRange_0= ruleRelativeSessionTimeRange | this_TodaySessionTimeRange_1= ruleTodaySessionTimeRange )
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==45) ) {
                alt20=1;
            }
            else if ( (LA20_0==46) ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // InternalXScript.g:2099:3: this_RelativeSessionTimeRange_0= ruleRelativeSessionTimeRange
                    {

                    			newCompositeNode(grammarAccess.getSessionTimeRangeAccess().getRelativeSessionTimeRangeParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_RelativeSessionTimeRange_0=ruleRelativeSessionTimeRange();

                    state._fsp--;


                    			current = this_RelativeSessionTimeRange_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalXScript.g:2108:3: this_TodaySessionTimeRange_1= ruleTodaySessionTimeRange
                    {

                    			newCompositeNode(grammarAccess.getSessionTimeRangeAccess().getTodaySessionTimeRangeParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_TodaySessionTimeRange_1=ruleTodaySessionTimeRange();

                    state._fsp--;


                    			current = this_TodaySessionTimeRange_1;
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
    // $ANTLR end "ruleSessionTimeRange"


    // $ANTLR start "entryRuleRelativeSessionTimeRange"
    // InternalXScript.g:2120:1: entryRuleRelativeSessionTimeRange returns [EObject current=null] : iv_ruleRelativeSessionTimeRange= ruleRelativeSessionTimeRange EOF ;
    public final EObject entryRuleRelativeSessionTimeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelativeSessionTimeRange = null;


        try {
            // InternalXScript.g:2120:65: (iv_ruleRelativeSessionTimeRange= ruleRelativeSessionTimeRange EOF )
            // InternalXScript.g:2121:2: iv_ruleRelativeSessionTimeRange= ruleRelativeSessionTimeRange EOF
            {
             newCompositeNode(grammarAccess.getRelativeSessionTimeRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelativeSessionTimeRange=ruleRelativeSessionTimeRange();

            state._fsp--;

             current =iv_ruleRelativeSessionTimeRange; 
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
    // $ANTLR end "entryRuleRelativeSessionTimeRange"


    // $ANTLR start "ruleRelativeSessionTimeRange"
    // InternalXScript.g:2127:1: ruleRelativeSessionTimeRange returns [EObject current=null] : ( () (otherlv_1= 'relativeTime' otherlv_2= '(' ( (lv_relativeVale_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_timeUnit_5_0= ruleSessionTimeUnit ) ) otherlv_6= ')' ) ) ;
    public final EObject ruleRelativeSessionTimeRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_relativeVale_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Enumerator lv_timeUnit_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2133:2: ( ( () (otherlv_1= 'relativeTime' otherlv_2= '(' ( (lv_relativeVale_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_timeUnit_5_0= ruleSessionTimeUnit ) ) otherlv_6= ')' ) ) )
            // InternalXScript.g:2134:2: ( () (otherlv_1= 'relativeTime' otherlv_2= '(' ( (lv_relativeVale_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_timeUnit_5_0= ruleSessionTimeUnit ) ) otherlv_6= ')' ) )
            {
            // InternalXScript.g:2134:2: ( () (otherlv_1= 'relativeTime' otherlv_2= '(' ( (lv_relativeVale_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_timeUnit_5_0= ruleSessionTimeUnit ) ) otherlv_6= ')' ) )
            // InternalXScript.g:2135:3: () (otherlv_1= 'relativeTime' otherlv_2= '(' ( (lv_relativeVale_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_timeUnit_5_0= ruleSessionTimeUnit ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:2135:3: ()
            // InternalXScript.g:2136:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getRelativeSessionTimeRangeAccess().getRelativeSessionTimeRangeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:2142:3: (otherlv_1= 'relativeTime' otherlv_2= '(' ( (lv_relativeVale_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_timeUnit_5_0= ruleSessionTimeUnit ) ) otherlv_6= ')' )
            // InternalXScript.g:2143:4: otherlv_1= 'relativeTime' otherlv_2= '(' ( (lv_relativeVale_3_0= RULE_INT ) ) otherlv_4= ',' ( (lv_timeUnit_5_0= ruleSessionTimeUnit ) ) otherlv_6= ')'
            {
            otherlv_1=(Token)match(input,45,FOLLOW_5); 

            				newLeafNode(otherlv_1, grammarAccess.getRelativeSessionTimeRangeAccess().getRelativeTimeKeyword_1_0());
            			
            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            				newLeafNode(otherlv_2, grammarAccess.getRelativeSessionTimeRangeAccess().getLeftParenthesisKeyword_1_1());
            			
            // InternalXScript.g:2151:4: ( (lv_relativeVale_3_0= RULE_INT ) )
            // InternalXScript.g:2152:5: (lv_relativeVale_3_0= RULE_INT )
            {
            // InternalXScript.g:2152:5: (lv_relativeVale_3_0= RULE_INT )
            // InternalXScript.g:2153:6: lv_relativeVale_3_0= RULE_INT
            {
            lv_relativeVale_3_0=(Token)match(input,RULE_INT,FOLLOW_7); 

            						newLeafNode(lv_relativeVale_3_0, grammarAccess.getRelativeSessionTimeRangeAccess().getRelativeValeINTTerminalRuleCall_1_2_0());
            					

            						if (current==null) {
            							current = createModelElement(grammarAccess.getRelativeSessionTimeRangeRule());
            						}
            						setWithLastConsumed(
            							current,
            							"relativeVale",
            							lv_relativeVale_3_0,
            							"org.eclipse.xtext.common.Terminals.INT");
            					

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_26); 

            				newLeafNode(otherlv_4, grammarAccess.getRelativeSessionTimeRangeAccess().getCommaKeyword_1_3());
            			
            // InternalXScript.g:2173:4: ( (lv_timeUnit_5_0= ruleSessionTimeUnit ) )
            // InternalXScript.g:2174:5: (lv_timeUnit_5_0= ruleSessionTimeUnit )
            {
            // InternalXScript.g:2174:5: (lv_timeUnit_5_0= ruleSessionTimeUnit )
            // InternalXScript.g:2175:6: lv_timeUnit_5_0= ruleSessionTimeUnit
            {

            						newCompositeNode(grammarAccess.getRelativeSessionTimeRangeAccess().getTimeUnitSessionTimeUnitEnumRuleCall_1_4_0());
            					
            pushFollow(FOLLOW_9);
            lv_timeUnit_5_0=ruleSessionTimeUnit();

            state._fsp--;


            						if (current==null) {
            							current = createModelElementForParent(grammarAccess.getRelativeSessionTimeRangeRule());
            						}
            						set(
            							current,
            							"timeUnit",
            							lv_timeUnit_5_0,
            							"com.dunkware.xstream.XScript.SessionTimeUnit");
            						afterParserOrEnumRuleCall();
            					

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            				newLeafNode(otherlv_6, grammarAccess.getRelativeSessionTimeRangeAccess().getRightParenthesisKeyword_1_5());
            			

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
    // $ANTLR end "ruleRelativeSessionTimeRange"


    // $ANTLR start "entryRuleTodaySessionTimeRange"
    // InternalXScript.g:2201:1: entryRuleTodaySessionTimeRange returns [EObject current=null] : iv_ruleTodaySessionTimeRange= ruleTodaySessionTimeRange EOF ;
    public final EObject entryRuleTodaySessionTimeRange() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTodaySessionTimeRange = null;


        try {
            // InternalXScript.g:2201:62: (iv_ruleTodaySessionTimeRange= ruleTodaySessionTimeRange EOF )
            // InternalXScript.g:2202:2: iv_ruleTodaySessionTimeRange= ruleTodaySessionTimeRange EOF
            {
             newCompositeNode(grammarAccess.getTodaySessionTimeRangeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTodaySessionTimeRange=ruleTodaySessionTimeRange();

            state._fsp--;

             current =iv_ruleTodaySessionTimeRange; 
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
    // $ANTLR end "entryRuleTodaySessionTimeRange"


    // $ANTLR start "ruleTodaySessionTimeRange"
    // InternalXScript.g:2208:1: ruleTodaySessionTimeRange returns [EObject current=null] : ( () otherlv_1= 'today' ) ;
    public final EObject ruleTodaySessionTimeRange() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalXScript.g:2214:2: ( ( () otherlv_1= 'today' ) )
            // InternalXScript.g:2215:2: ( () otherlv_1= 'today' )
            {
            // InternalXScript.g:2215:2: ( () otherlv_1= 'today' )
            // InternalXScript.g:2216:3: () otherlv_1= 'today'
            {
            // InternalXScript.g:2216:3: ()
            // InternalXScript.g:2217:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTodaySessionTimeRangeAccess().getTodaySessionTimeRangeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,46,FOLLOW_2); 

            			newLeafNode(otherlv_1, grammarAccess.getTodaySessionTimeRangeAccess().getTodayKeyword_1());
            		

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
    // $ANTLR end "ruleTodaySessionTimeRange"


    // $ANTLR start "entryRuleVarAggSessionType"
    // InternalXScript.g:2231:1: entryRuleVarAggSessionType returns [EObject current=null] : iv_ruleVarAggSessionType= ruleVarAggSessionType EOF ;
    public final EObject entryRuleVarAggSessionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarAggSessionType = null;


        try {
            // InternalXScript.g:2231:58: (iv_ruleVarAggSessionType= ruleVarAggSessionType EOF )
            // InternalXScript.g:2232:2: iv_ruleVarAggSessionType= ruleVarAggSessionType EOF
            {
             newCompositeNode(grammarAccess.getVarAggSessionTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarAggSessionType=ruleVarAggSessionType();

            state._fsp--;

             current =iv_ruleVarAggSessionType; 
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
    // $ANTLR end "entryRuleVarAggSessionType"


    // $ANTLR start "ruleVarAggSessionType"
    // InternalXScript.g:2238:1: ruleVarAggSessionType returns [EObject current=null] : ( () otherlv_1= 'varAggSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleSessionAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleSessionTimeRange ) ) otherlv_8= ')' ) ;
    public final EObject ruleVarAggSessionType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_function_5_0 = null;

        EObject lv_timeRange_7_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2244:2: ( ( () otherlv_1= 'varAggSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleSessionAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleSessionTimeRange ) ) otherlv_8= ')' ) )
            // InternalXScript.g:2245:2: ( () otherlv_1= 'varAggSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleSessionAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleSessionTimeRange ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:2245:2: ( () otherlv_1= 'varAggSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleSessionAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleSessionTimeRange ) ) otherlv_8= ')' )
            // InternalXScript.g:2246:3: () otherlv_1= 'varAggSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleSessionAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleSessionTimeRange ) ) otherlv_8= ')'
            {
            // InternalXScript.g:2246:3: ()
            // InternalXScript.g:2247:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVarAggSessionTypeAccess().getVarAggSessionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,47,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getVarAggSessionTypeAccess().getVarAggSessionKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getVarAggSessionTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2261:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2262:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2262:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2263:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVarAggSessionTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getVarAggSessionTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_27); 

            			newLeafNode(otherlv_4, grammarAccess.getVarAggSessionTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:2278:3: ( (lv_function_5_0= ruleSessionAggFunc ) )
            // InternalXScript.g:2279:4: (lv_function_5_0= ruleSessionAggFunc )
            {
            // InternalXScript.g:2279:4: (lv_function_5_0= ruleSessionAggFunc )
            // InternalXScript.g:2280:5: lv_function_5_0= ruleSessionAggFunc
            {

            					newCompositeNode(grammarAccess.getVarAggSessionTypeAccess().getFunctionSessionAggFuncEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_7);
            lv_function_5_0=ruleSessionAggFunc();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarAggSessionTypeRule());
            					}
            					set(
            						current,
            						"function",
            						lv_function_5_0,
            						"com.dunkware.xstream.XScript.SessionAggFunc");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_28); 

            			newLeafNode(otherlv_6, grammarAccess.getVarAggSessionTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:2301:3: ( (lv_timeRange_7_0= ruleSessionTimeRange ) )
            // InternalXScript.g:2302:4: (lv_timeRange_7_0= ruleSessionTimeRange )
            {
            // InternalXScript.g:2302:4: (lv_timeRange_7_0= ruleSessionTimeRange )
            // InternalXScript.g:2303:5: lv_timeRange_7_0= ruleSessionTimeRange
            {

            					newCompositeNode(grammarAccess.getVarAggSessionTypeAccess().getTimeRangeSessionTimeRangeParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_9);
            lv_timeRange_7_0=ruleSessionTimeRange();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarAggSessionTypeRule());
            					}
            					set(
            						current,
            						"timeRange",
            						lv_timeRange_7_0,
            						"com.dunkware.xstream.XScript.SessionTimeRange");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getVarAggSessionTypeAccess().getRightParenthesisKeyword_8());
            		

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
    // $ANTLR end "ruleVarAggSessionType"


    // $ANTLR start "entryRuleVarAggHistoryType"
    // InternalXScript.g:2328:1: entryRuleVarAggHistoryType returns [EObject current=null] : iv_ruleVarAggHistoryType= ruleVarAggHistoryType EOF ;
    public final EObject entryRuleVarAggHistoryType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarAggHistoryType = null;


        try {
            // InternalXScript.g:2328:58: (iv_ruleVarAggHistoryType= ruleVarAggHistoryType EOF )
            // InternalXScript.g:2329:2: iv_ruleVarAggHistoryType= ruleVarAggHistoryType EOF
            {
             newCompositeNode(grammarAccess.getVarAggHistoryTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVarAggHistoryType=ruleVarAggHistoryType();

            state._fsp--;

             current =iv_ruleVarAggHistoryType; 
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
    // $ANTLR end "entryRuleVarAggHistoryType"


    // $ANTLR start "ruleVarAggHistoryType"
    // InternalXScript.g:2335:1: ruleVarAggHistoryType returns [EObject current=null] : ( () otherlv_1= 'varAggHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleHistoricalAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleHistoryTimeRange ) ) otherlv_8= ')' ) ;
    public final EObject ruleVarAggHistoryType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Enumerator lv_function_5_0 = null;

        EObject lv_timeRange_7_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2341:2: ( ( () otherlv_1= 'varAggHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleHistoricalAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleHistoryTimeRange ) ) otherlv_8= ')' ) )
            // InternalXScript.g:2342:2: ( () otherlv_1= 'varAggHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleHistoricalAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleHistoryTimeRange ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:2342:2: ( () otherlv_1= 'varAggHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleHistoricalAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleHistoryTimeRange ) ) otherlv_8= ')' )
            // InternalXScript.g:2343:3: () otherlv_1= 'varAggHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_function_5_0= ruleHistoricalAggFunc ) ) otherlv_6= ',' ( (lv_timeRange_7_0= ruleHistoryTimeRange ) ) otherlv_8= ')'
            {
            // InternalXScript.g:2343:3: ()
            // InternalXScript.g:2344:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVarAggHistoryTypeAccess().getVarAggHistoryTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,48,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getVarAggHistoryTypeAccess().getVarAggHistoryKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getVarAggHistoryTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2358:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2359:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2359:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2360:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getVarAggHistoryTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getVarAggHistoryTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_27); 

            			newLeafNode(otherlv_4, grammarAccess.getVarAggHistoryTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:2375:3: ( (lv_function_5_0= ruleHistoricalAggFunc ) )
            // InternalXScript.g:2376:4: (lv_function_5_0= ruleHistoricalAggFunc )
            {
            // InternalXScript.g:2376:4: (lv_function_5_0= ruleHistoricalAggFunc )
            // InternalXScript.g:2377:5: lv_function_5_0= ruleHistoricalAggFunc
            {

            					newCompositeNode(grammarAccess.getVarAggHistoryTypeAccess().getFunctionHistoricalAggFuncEnumRuleCall_5_0());
            				
            pushFollow(FOLLOW_7);
            lv_function_5_0=ruleHistoricalAggFunc();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarAggHistoryTypeRule());
            					}
            					set(
            						current,
            						"function",
            						lv_function_5_0,
            						"com.dunkware.xstream.XScript.HistoricalAggFunc");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_6, grammarAccess.getVarAggHistoryTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:2398:3: ( (lv_timeRange_7_0= ruleHistoryTimeRange ) )
            // InternalXScript.g:2399:4: (lv_timeRange_7_0= ruleHistoryTimeRange )
            {
            // InternalXScript.g:2399:4: (lv_timeRange_7_0= ruleHistoryTimeRange )
            // InternalXScript.g:2400:5: lv_timeRange_7_0= ruleHistoryTimeRange
            {

            					newCompositeNode(grammarAccess.getVarAggHistoryTypeAccess().getTimeRangeHistoryTimeRangeParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_9);
            lv_timeRange_7_0=ruleHistoryTimeRange();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVarAggHistoryTypeRule());
            					}
            					set(
            						current,
            						"timeRange",
            						lv_timeRange_7_0,
            						"com.dunkware.xstream.XScript.HistoryTimeRange");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getVarAggHistoryTypeAccess().getRightParenthesisKeyword_8());
            		

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
    // $ANTLR end "ruleVarAggHistoryType"


    // $ANTLR start "entryRuleSignalCountSession"
    // InternalXScript.g:2425:1: entryRuleSignalCountSession returns [EObject current=null] : iv_ruleSignalCountSession= ruleSignalCountSession EOF ;
    public final EObject entryRuleSignalCountSession() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignalCountSession = null;


        try {
            // InternalXScript.g:2425:59: (iv_ruleSignalCountSession= ruleSignalCountSession EOF )
            // InternalXScript.g:2426:2: iv_ruleSignalCountSession= ruleSignalCountSession EOF
            {
             newCompositeNode(grammarAccess.getSignalCountSessionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSignalCountSession=ruleSignalCountSession();

            state._fsp--;

             current =iv_ruleSignalCountSession; 
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
    // $ANTLR end "entryRuleSignalCountSession"


    // $ANTLR start "ruleSignalCountSession"
    // InternalXScript.g:2432:1: ruleSignalCountSession returns [EObject current=null] : ( () otherlv_1= 'sigCountSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleSessionTimeRange ) ) otherlv_6= ')' ) ;
    public final EObject ruleSignalCountSession() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_timeRange_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2438:2: ( ( () otherlv_1= 'sigCountSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleSessionTimeRange ) ) otherlv_6= ')' ) )
            // InternalXScript.g:2439:2: ( () otherlv_1= 'sigCountSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleSessionTimeRange ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:2439:2: ( () otherlv_1= 'sigCountSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleSessionTimeRange ) ) otherlv_6= ')' )
            // InternalXScript.g:2440:3: () otherlv_1= 'sigCountSession' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleSessionTimeRange ) ) otherlv_6= ')'
            {
            // InternalXScript.g:2440:3: ()
            // InternalXScript.g:2441:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSignalCountSessionAccess().getSignalCountSessionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,49,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getSignalCountSessionAccess().getSigCountSessionKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getSignalCountSessionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2455:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2456:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2456:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2457:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSignalCountSessionRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getSignalCountSessionAccess().getSignalSignalTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_28); 

            			newLeafNode(otherlv_4, grammarAccess.getSignalCountSessionAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:2472:3: ( (lv_timeRange_5_0= ruleSessionTimeRange ) )
            // InternalXScript.g:2473:4: (lv_timeRange_5_0= ruleSessionTimeRange )
            {
            // InternalXScript.g:2473:4: (lv_timeRange_5_0= ruleSessionTimeRange )
            // InternalXScript.g:2474:5: lv_timeRange_5_0= ruleSessionTimeRange
            {

            					newCompositeNode(grammarAccess.getSignalCountSessionAccess().getTimeRangeSessionTimeRangeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_timeRange_5_0=ruleSessionTimeRange();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSignalCountSessionRule());
            					}
            					set(
            						current,
            						"timeRange",
            						lv_timeRange_5_0,
            						"com.dunkware.xstream.XScript.SessionTimeRange");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getSignalCountSessionAccess().getRightParenthesisKeyword_6());
            		

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
    // $ANTLR end "ruleSignalCountSession"


    // $ANTLR start "entryRuleSignalCountHistory"
    // InternalXScript.g:2499:1: entryRuleSignalCountHistory returns [EObject current=null] : iv_ruleSignalCountHistory= ruleSignalCountHistory EOF ;
    public final EObject entryRuleSignalCountHistory() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignalCountHistory = null;


        try {
            // InternalXScript.g:2499:59: (iv_ruleSignalCountHistory= ruleSignalCountHistory EOF )
            // InternalXScript.g:2500:2: iv_ruleSignalCountHistory= ruleSignalCountHistory EOF
            {
             newCompositeNode(grammarAccess.getSignalCountHistoryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSignalCountHistory=ruleSignalCountHistory();

            state._fsp--;

             current =iv_ruleSignalCountHistory; 
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
    // $ANTLR end "entryRuleSignalCountHistory"


    // $ANTLR start "ruleSignalCountHistory"
    // InternalXScript.g:2506:1: ruleSignalCountHistory returns [EObject current=null] : ( () otherlv_1= 'sigCountHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleHistoryTimeRange ) ) otherlv_6= ')' ) ;
    public final EObject ruleSignalCountHistory() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_timeRange_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2512:2: ( ( () otherlv_1= 'sigCountHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleHistoryTimeRange ) ) otherlv_6= ')' ) )
            // InternalXScript.g:2513:2: ( () otherlv_1= 'sigCountHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleHistoryTimeRange ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:2513:2: ( () otherlv_1= 'sigCountHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleHistoryTimeRange ) ) otherlv_6= ')' )
            // InternalXScript.g:2514:3: () otherlv_1= 'sigCountHistory' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_timeRange_5_0= ruleHistoryTimeRange ) ) otherlv_6= ')'
            {
            // InternalXScript.g:2514:3: ()
            // InternalXScript.g:2515:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSignalCountHistoryAccess().getSignalCountHistoryAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,50,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getSignalCountHistoryAccess().getSigCountHistoryKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getSignalCountHistoryAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:2529:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:2530:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:2530:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:2531:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getSignalCountHistoryRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getSignalCountHistoryAccess().getSignalSignalTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_29); 

            			newLeafNode(otherlv_4, grammarAccess.getSignalCountHistoryAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:2546:3: ( (lv_timeRange_5_0= ruleHistoryTimeRange ) )
            // InternalXScript.g:2547:4: (lv_timeRange_5_0= ruleHistoryTimeRange )
            {
            // InternalXScript.g:2547:4: (lv_timeRange_5_0= ruleHistoryTimeRange )
            // InternalXScript.g:2548:5: lv_timeRange_5_0= ruleHistoryTimeRange
            {

            					newCompositeNode(grammarAccess.getSignalCountHistoryAccess().getTimeRangeHistoryTimeRangeParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_9);
            lv_timeRange_5_0=ruleHistoryTimeRange();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getSignalCountHistoryRule());
            					}
            					set(
            						current,
            						"timeRange",
            						lv_timeRange_5_0,
            						"com.dunkware.xstream.XScript.HistoryTimeRange");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getSignalCountHistoryAccess().getRightParenthesisKeyword_6());
            		

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
    // $ANTLR end "ruleSignalCountHistory"


    // $ANTLR start "entryRuleSignalType"
    // InternalXScript.g:2573:1: entryRuleSignalType returns [EObject current=null] : iv_ruleSignalType= ruleSignalType EOF ;
    public final EObject entryRuleSignalType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSignalType = null;


        try {
            // InternalXScript.g:2573:51: (iv_ruleSignalType= ruleSignalType EOF )
            // InternalXScript.g:2574:2: iv_ruleSignalType= ruleSignalType EOF
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
    // InternalXScript.g:2580:1: ruleSignalType returns [EObject current=null] : ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' ) ;
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
            // InternalXScript.g:2586:2: ( ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' ) )
            // InternalXScript.g:2587:2: ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' )
            {
            // InternalXScript.g:2587:2: ( () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';' )
            // InternalXScript.g:2588:3: () otherlv_1= 'signal' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (lv_id_4_0= RULE_INT ) ) otherlv_5= ')' otherlv_6= ';'
            {
            // InternalXScript.g:2588:3: ()
            // InternalXScript.g:2589:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getSignalTypeAccess().getSignalTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,51,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getSignalTypeAccess().getSignalKeyword_1());
            		
            // InternalXScript.g:2599:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2600:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2600:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2601:5: lv_name_2_0= RULE_ID
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
            		
            // InternalXScript.g:2621:3: ( (lv_id_4_0= RULE_INT ) )
            // InternalXScript.g:2622:4: (lv_id_4_0= RULE_INT )
            {
            // InternalXScript.g:2622:4: (lv_id_4_0= RULE_INT )
            // InternalXScript.g:2623:5: lv_id_4_0= RULE_INT
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
    // InternalXScript.g:2651:1: entryRuleXClassType returns [EObject current=null] : iv_ruleXClassType= ruleXClassType EOF ;
    public final EObject entryRuleXClassType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassType = null;


        try {
            // InternalXScript.g:2651:51: (iv_ruleXClassType= ruleXClassType EOF )
            // InternalXScript.g:2652:2: iv_ruleXClassType= ruleXClassType EOF
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
    // InternalXScript.g:2658:1: ruleXClassType returns [EObject current=null] : ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' ) ;
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
            // InternalXScript.g:2664:2: ( ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' ) )
            // InternalXScript.g:2665:2: ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' )
            {
            // InternalXScript.g:2665:2: ( () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}' )
            // InternalXScript.g:2666:3: () otherlv_1= 'class' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )? otherlv_6= '{' ( (lv_elements_7_0= ruleXClassElementType ) )* otherlv_8= '}'
            {
            // InternalXScript.g:2666:3: ()
            // InternalXScript.g:2667:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXClassTypeAccess().getXClassTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,52,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXClassTypeAccess().getClassKeyword_1());
            		
            // InternalXScript.g:2677:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2678:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2678:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2679:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_30); 

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

            // InternalXScript.g:2695:3: (otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==13) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // InternalXScript.g:2696:4: otherlv_3= '(' ( (lv_symbolFilter_4_0= RULE_STRING ) ) otherlv_5= ')'
                    {
                    otherlv_3=(Token)match(input,13,FOLLOW_31); 

                    				newLeafNode(otherlv_3, grammarAccess.getXClassTypeAccess().getLeftParenthesisKeyword_3_0());
                    			
                    // InternalXScript.g:2700:4: ( (lv_symbolFilter_4_0= RULE_STRING ) )
                    // InternalXScript.g:2701:5: (lv_symbolFilter_4_0= RULE_STRING )
                    {
                    // InternalXScript.g:2701:5: (lv_symbolFilter_4_0= RULE_STRING )
                    // InternalXScript.g:2702:6: lv_symbolFilter_4_0= RULE_STRING
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

            otherlv_6=(Token)match(input,38,FOLLOW_32); 

            			newLeafNode(otherlv_6, grammarAccess.getXClassTypeAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalXScript.g:2727:3: ( (lv_elements_7_0= ruleXClassElementType ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>=53 && LA22_0<=54)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalXScript.g:2728:4: (lv_elements_7_0= ruleXClassElementType )
            	    {
            	    // InternalXScript.g:2728:4: (lv_elements_7_0= ruleXClassElementType )
            	    // InternalXScript.g:2729:5: lv_elements_7_0= ruleXClassElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXClassTypeAccess().getElementsXClassElementTypeParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_32);
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

            otherlv_8=(Token)match(input,39,FOLLOW_2); 

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
    // InternalXScript.g:2754:1: entryRuleXClassElementType returns [EObject current=null] : iv_ruleXClassElementType= ruleXClassElementType EOF ;
    public final EObject entryRuleXClassElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassElementType = null;


        try {
            // InternalXScript.g:2754:58: (iv_ruleXClassElementType= ruleXClassElementType EOF )
            // InternalXScript.g:2755:2: iv_ruleXClassElementType= ruleXClassElementType EOF
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
    // InternalXScript.g:2761:1: ruleXClassElementType returns [EObject current=null] : this_XClassCoreElementType_0= ruleXClassCoreElementType ;
    public final EObject ruleXClassElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XClassCoreElementType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2767:2: (this_XClassCoreElementType_0= ruleXClassCoreElementType )
            // InternalXScript.g:2768:2: this_XClassCoreElementType_0= ruleXClassCoreElementType
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
    // InternalXScript.g:2779:1: entryRuleXClassCoreElementType returns [EObject current=null] : iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF ;
    public final EObject entryRuleXClassCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassCoreElementType = null;


        try {
            // InternalXScript.g:2779:62: (iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF )
            // InternalXScript.g:2780:2: iv_ruleXClassCoreElementType= ruleXClassCoreElementType EOF
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
    // InternalXScript.g:2786:1: ruleXClassCoreElementType returns [EObject current=null] : (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType ) ;
    public final EObject ruleXClassCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XFunctionType_0 = null;

        EObject this_XVarType_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:2792:2: ( (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType ) )
            // InternalXScript.g:2793:2: (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType )
            {
            // InternalXScript.g:2793:2: (this_XFunctionType_0= ruleXFunctionType | this_XVarType_1= ruleXVarType )
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==54) ) {
                alt23=1;
            }
            else if ( (LA23_0==53) ) {
                alt23=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 23, 0, input);

                throw nvae;
            }
            switch (alt23) {
                case 1 :
                    // InternalXScript.g:2794:3: this_XFunctionType_0= ruleXFunctionType
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
                    // InternalXScript.g:2803:3: this_XVarType_1= ruleXVarType
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
    // InternalXScript.g:2815:1: entryRuleXVarType returns [EObject current=null] : iv_ruleXVarType= ruleXVarType EOF ;
    public final EObject entryRuleXVarType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarType = null;


        try {
            // InternalXScript.g:2815:49: (iv_ruleXVarType= ruleXVarType EOF )
            // InternalXScript.g:2816:2: iv_ruleXVarType= ruleXVarType EOF
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
    // InternalXScript.g:2822:1: ruleXVarType returns [EObject current=null] : ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' ) ;
    public final EObject ruleXVarType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_exp_4_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:2828:2: ( ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' ) )
            // InternalXScript.g:2829:2: ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' )
            {
            // InternalXScript.g:2829:2: ( () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';' )
            // InternalXScript.g:2830:3: () otherlv_1= 'var' ( (lv_name_2_0= RULE_ID ) ) (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )? otherlv_5= ';'
            {
            // InternalXScript.g:2830:3: ()
            // InternalXScript.g:2831:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarTypeAccess().getXVarTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,53,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarTypeAccess().getVarKeyword_1());
            		
            // InternalXScript.g:2841:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2842:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2842:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2843:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_33); 

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

            // InternalXScript.g:2859:3: (otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==16) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalXScript.g:2860:4: otherlv_3= '=' ( (lv_exp_4_0= ruleXExpressionType ) )
                    {
                    otherlv_3=(Token)match(input,16,FOLLOW_34); 

                    				newLeafNode(otherlv_3, grammarAccess.getXVarTypeAccess().getEqualsSignKeyword_3_0());
                    			
                    // InternalXScript.g:2864:4: ( (lv_exp_4_0= ruleXExpressionType ) )
                    // InternalXScript.g:2865:5: (lv_exp_4_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:2865:5: (lv_exp_4_0= ruleXExpressionType )
                    // InternalXScript.g:2866:6: lv_exp_4_0= ruleXExpressionType
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
    // InternalXScript.g:2892:1: entryRuleXFunctionType returns [EObject current=null] : iv_ruleXFunctionType= ruleXFunctionType EOF ;
    public final EObject entryRuleXFunctionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionType = null;


        try {
            // InternalXScript.g:2892:54: (iv_ruleXFunctionType= ruleXFunctionType EOF )
            // InternalXScript.g:2893:2: iv_ruleXFunctionType= ruleXFunctionType EOF
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
    // InternalXScript.g:2899:1: ruleXFunctionType returns [EObject current=null] : ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' ) ;
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
            // InternalXScript.g:2905:2: ( ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' ) )
            // InternalXScript.g:2906:2: ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' )
            {
            // InternalXScript.g:2906:2: ( () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}' )
            // InternalXScript.g:2907:3: () otherlv_1= 'function' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '(' ( (otherlv_4= RULE_ID ) )* otherlv_5= ')' otherlv_6= '{' ( (lv_elements_7_0= ruleXClassFunctionElementType ) )* otherlv_8= '}'
            {
            // InternalXScript.g:2907:3: ()
            // InternalXScript.g:2908:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionTypeAccess().getXFunctionTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,54,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionTypeAccess().getFunctionKeyword_1());
            		
            // InternalXScript.g:2918:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalXScript.g:2919:4: (lv_name_2_0= RULE_ID )
            {
            // InternalXScript.g:2919:4: (lv_name_2_0= RULE_ID )
            // InternalXScript.g:2920:5: lv_name_2_0= RULE_ID
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

            otherlv_3=(Token)match(input,13,FOLLOW_35); 

            			newLeafNode(otherlv_3, grammarAccess.getXFunctionTypeAccess().getLeftParenthesisKeyword_3());
            		
            // InternalXScript.g:2940:3: ( (otherlv_4= RULE_ID ) )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==RULE_ID) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalXScript.g:2941:4: (otherlv_4= RULE_ID )
            	    {
            	    // InternalXScript.g:2941:4: (otherlv_4= RULE_ID )
            	    // InternalXScript.g:2942:5: otherlv_4= RULE_ID
            	    {

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getXFunctionTypeRule());
            	    					}
            	    				
            	    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_35); 

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
            		
            otherlv_6=(Token)match(input,38,FOLLOW_36); 

            			newLeafNode(otherlv_6, grammarAccess.getXFunctionTypeAccess().getLeftCurlyBracketKeyword_6());
            		
            // InternalXScript.g:2961:3: ( (lv_elements_7_0= ruleXClassFunctionElementType ) )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID||LA26_0==51||LA26_0==53||(LA26_0>=55 && LA26_0<=58)||(LA26_0>=61 && LA26_0<=63)||LA26_0==66||LA26_0==68) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalXScript.g:2962:4: (lv_elements_7_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:2962:4: (lv_elements_7_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:2963:5: lv_elements_7_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXFunctionTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_7_0());
            	    				
            	    pushFollow(FOLLOW_36);
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

            otherlv_8=(Token)match(input,39,FOLLOW_2); 

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
    // InternalXScript.g:2988:1: entryRuleXClassFunctionElementType returns [EObject current=null] : iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF ;
    public final EObject entryRuleXClassFunctionElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXClassFunctionElementType = null;


        try {
            // InternalXScript.g:2988:66: (iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF )
            // InternalXScript.g:2989:2: iv_ruleXClassFunctionElementType= ruleXClassFunctionElementType EOF
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
    // InternalXScript.g:2995:1: ruleXClassFunctionElementType returns [EObject current=null] : this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType ;
    public final EObject ruleXClassFunctionElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XFunctionCoreElementType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3001:2: (this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType )
            // InternalXScript.g:3002:2: this_XFunctionCoreElementType_0= ruleXFunctionCoreElementType
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
    // InternalXScript.g:3013:1: entryRuleXFunctionCoreElementType returns [EObject current=null] : iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF ;
    public final EObject entryRuleXFunctionCoreElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCoreElementType = null;


        try {
            // InternalXScript.g:3013:65: (iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF )
            // InternalXScript.g:3014:2: iv_ruleXFunctionCoreElementType= ruleXFunctionCoreElementType EOF
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
    // InternalXScript.g:3020:1: ruleXFunctionCoreElementType returns [EObject current=null] : (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType ) ;
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
            // InternalXScript.g:3026:2: ( (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType ) )
            // InternalXScript.g:3027:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )
            {
            // InternalXScript.g:3027:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )
            int alt27=15;
            alt27 = dfa27.predict(input);
            switch (alt27) {
                case 1 :
                    // InternalXScript.g:3028:3: this_XFunctionReturnType_0= ruleXFunctionReturnType
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
                    // InternalXScript.g:3037:3: this_XVarType_1= ruleXVarType
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
                    // InternalXScript.g:3046:3: this_XIfStatementType_2= ruleXIfStatementType
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
                    // InternalXScript.g:3055:3: this_XSignalListenerType_3= ruleXSignalListenerType
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
                    // InternalXScript.g:3064:3: this_XStreamVarListenerType_4= ruleXStreamVarListenerType
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
                    // InternalXScript.g:3073:3: this_XFunctionStartType_5= ruleXFunctionStartType
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
                    // InternalXScript.g:3082:3: this_XFunctionCallType_6= ruleXFunctionCallType
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
                    // InternalXScript.g:3091:3: this_XVarSetterType_7= ruleXVarSetterType
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
                    // InternalXScript.g:3100:3: this_XSignalTriggerType_8= ruleXSignalTriggerType
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
                    // InternalXScript.g:3109:3: this_XVarDecrementType_9= ruleXVarDecrementType
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
                    // InternalXScript.g:3118:3: this_XSetVarType_10= ruleXSetVarType
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
                    // InternalXScript.g:3127:3: this_XVarIncrementType_11= ruleXVarIncrementType
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
                    // InternalXScript.g:3136:3: this_XDebugType_12= ruleXDebugType
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
                    // InternalXScript.g:3145:3: this_XSleepType_13= ruleXSleepType
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
                    // InternalXScript.g:3154:3: this_XWhileType_14= ruleXWhileType
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
    // InternalXScript.g:3166:1: entryRuleXFunctionReturnType returns [EObject current=null] : iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF ;
    public final EObject entryRuleXFunctionReturnType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionReturnType = null;


        try {
            // InternalXScript.g:3166:60: (iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF )
            // InternalXScript.g:3167:2: iv_ruleXFunctionReturnType= ruleXFunctionReturnType EOF
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
    // InternalXScript.g:3173:1: ruleXFunctionReturnType returns [EObject current=null] : ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' ) ;
    public final EObject ruleXFunctionReturnType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_returnValue_2_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3179:2: ( ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' ) )
            // InternalXScript.g:3180:2: ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' )
            {
            // InternalXScript.g:3180:2: ( () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';' )
            // InternalXScript.g:3181:3: () otherlv_1= 'return' ( (lv_returnValue_2_0= ruleXExpressionType ) )? otherlv_3= ';'
            {
            // InternalXScript.g:3181:3: ()
            // InternalXScript.g:3182:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionReturnTypeAccess().getXFunctionReturnTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,55,FOLLOW_37); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionReturnTypeAccess().getReturnKeyword_1());
            		
            // InternalXScript.g:3192:3: ( (lv_returnValue_2_0= ruleXExpressionType ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=RULE_ID && LA28_0<=RULE_STRING)||LA28_0==13||(LA28_0>=31 && LA28_0<=33)||LA28_0==37||LA28_0==43||(LA28_0>=69 && LA28_0<=70)||LA28_0==76||(LA28_0>=78 && LA28_0<=83)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalXScript.g:3193:4: (lv_returnValue_2_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:3193:4: (lv_returnValue_2_0= ruleXExpressionType )
                    // InternalXScript.g:3194:5: lv_returnValue_2_0= ruleXExpressionType
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
    // InternalXScript.g:3219:1: entryRuleXFunctionCallType returns [EObject current=null] : iv_ruleXFunctionCallType= ruleXFunctionCallType EOF ;
    public final EObject entryRuleXFunctionCallType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCallType = null;


        try {
            // InternalXScript.g:3219:58: (iv_ruleXFunctionCallType= ruleXFunctionCallType EOF )
            // InternalXScript.g:3220:2: iv_ruleXFunctionCallType= ruleXFunctionCallType EOF
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
    // InternalXScript.g:3226:1: ruleXFunctionCallType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' ) ;
    public final EObject ruleXFunctionCallType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_params_3_0=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalXScript.g:3232:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' ) )
            // InternalXScript.g:3233:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' )
            {
            // InternalXScript.g:3233:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';' )
            // InternalXScript.g:3234:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_params_3_0= RULE_STRING ) )? otherlv_4= ')' otherlv_5= ';'
            {
            // InternalXScript.g:3234:3: ()
            // InternalXScript.g:3235:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionCallTypeAccess().getXFunctionCallTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3241:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3242:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3242:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3243:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionCallTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(otherlv_1, grammarAccess.getXFunctionCallTypeAccess().getFunctionXFunctionTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_38); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionCallTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3258:3: ( (lv_params_3_0= RULE_STRING ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==RULE_STRING) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalXScript.g:3259:4: (lv_params_3_0= RULE_STRING )
                    {
                    // InternalXScript.g:3259:4: (lv_params_3_0= RULE_STRING )
                    // InternalXScript.g:3260:5: lv_params_3_0= RULE_STRING
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
    // InternalXScript.g:3288:1: entryRuleXSignalListenerType returns [EObject current=null] : iv_ruleXSignalListenerType= ruleXSignalListenerType EOF ;
    public final EObject entryRuleXSignalListenerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalListenerType = null;


        try {
            // InternalXScript.g:3288:60: (iv_ruleXSignalListenerType= ruleXSignalListenerType EOF )
            // InternalXScript.g:3289:2: iv_ruleXSignalListenerType= ruleXSignalListenerType EOF
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
    // InternalXScript.g:3295:1: ruleXSignalListenerType returns [EObject current=null] : ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) ;
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
            // InternalXScript.g:3301:2: ( ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:3302:2: ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:3302:2: ( () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:3303:3: () otherlv_1= 'signalListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:3303:3: ()
            // InternalXScript.g:3304:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalListenerTypeAccess().getXSignalListenerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,56,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalListenerTypeAccess().getSignalListenerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalListenerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3318:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:3319:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:3319:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:3320:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:3335:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:3336:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:3336:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:3337:5: otherlv_5= RULE_ID
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
    // InternalXScript.g:3360:1: entryRuleXStreamVarListenerType returns [EObject current=null] : iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF ;
    public final EObject entryRuleXStreamVarListenerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamVarListenerType = null;


        try {
            // InternalXScript.g:3360:63: (iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF )
            // InternalXScript.g:3361:2: iv_ruleXStreamVarListenerType= ruleXStreamVarListenerType EOF
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
    // InternalXScript.g:3367:1: ruleXStreamVarListenerType returns [EObject current=null] : ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) ;
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
            // InternalXScript.g:3373:2: ( ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:3374:2: ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:3374:2: ( () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:3375:3: () otherlv_1= 'streamVarListener' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:3375:3: ()
            // InternalXScript.g:3376:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamVarListenerTypeAccess().getXStreamVarListenerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,57,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXStreamVarListenerTypeAccess().getStreamVarListenerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXStreamVarListenerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3390:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:3391:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:3391:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:3392:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:3407:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:3408:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:3408:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:3409:5: otherlv_5= RULE_ID
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
    // InternalXScript.g:3432:1: entryRuleXSignalTriggerType returns [EObject current=null] : iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF ;
    public final EObject entryRuleXSignalTriggerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalTriggerType = null;


        try {
            // InternalXScript.g:3432:59: (iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF )
            // InternalXScript.g:3433:2: iv_ruleXSignalTriggerType= ruleXSignalTriggerType EOF
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
    // InternalXScript.g:3439:1: ruleXSignalTriggerType returns [EObject current=null] : ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' ) ;
    public final EObject ruleXSignalTriggerType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalXScript.g:3445:2: ( ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' ) )
            // InternalXScript.g:3446:2: ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' )
            {
            // InternalXScript.g:3446:2: ( () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';' )
            // InternalXScript.g:3447:3: () otherlv_1= 'signal' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' otherlv_5= ';'
            {
            // InternalXScript.g:3447:3: ()
            // InternalXScript.g:3448:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalTriggerTypeAccess().getXSignalTriggerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,51,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalTriggerTypeAccess().getSignalKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalTriggerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3462:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:3463:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:3463:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:3464:5: otherlv_3= RULE_ID
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
    // InternalXScript.g:3487:1: entryRuleXFunctionStartType returns [EObject current=null] : iv_ruleXFunctionStartType= ruleXFunctionStartType EOF ;
    public final EObject entryRuleXFunctionStartType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionStartType = null;


        try {
            // InternalXScript.g:3487:59: (iv_ruleXFunctionStartType= ruleXFunctionStartType EOF )
            // InternalXScript.g:3488:2: iv_ruleXFunctionStartType= ruleXFunctionStartType EOF
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
    // InternalXScript.g:3494:1: ruleXFunctionStartType returns [EObject current=null] : ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' ) ;
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
            // InternalXScript.g:3500:2: ( ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' ) )
            // InternalXScript.g:3501:2: ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' )
            {
            // InternalXScript.g:3501:2: ( () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';' )
            // InternalXScript.g:3502:3: () otherlv_1= 'functionRunner' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_interval_5_0= RULE_INT ) ) ( (lv_time_6_0= ruleStreamTimeUnit ) ) otherlv_7= ')' otherlv_8= ';'
            {
            // InternalXScript.g:3502:3: ()
            // InternalXScript.g:3503:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionStartTypeAccess().getXFunctionStartTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,58,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXFunctionStartTypeAccess().getFunctionRunnerKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionStartTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3517:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:3518:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:3518:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:3519:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:3534:3: ( (lv_interval_5_0= RULE_INT ) )
            // InternalXScript.g:3535:4: (lv_interval_5_0= RULE_INT )
            {
            // InternalXScript.g:3535:4: (lv_interval_5_0= RULE_INT )
            // InternalXScript.g:3536:5: lv_interval_5_0= RULE_INT
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

            // InternalXScript.g:3552:3: ( (lv_time_6_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:3553:4: (lv_time_6_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:3553:4: (lv_time_6_0= ruleStreamTimeUnit )
            // InternalXScript.g:3554:5: lv_time_6_0= ruleStreamTimeUnit
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
    // InternalXScript.g:3583:1: entryRuleXVarSetterType returns [EObject current=null] : iv_ruleXVarSetterType= ruleXVarSetterType EOF ;
    public final EObject entryRuleXVarSetterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarSetterType = null;


        try {
            // InternalXScript.g:3583:55: (iv_ruleXVarSetterType= ruleXVarSetterType EOF )
            // InternalXScript.g:3584:2: iv_ruleXVarSetterType= ruleXVarSetterType EOF
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
    // InternalXScript.g:3590:1: ruleXVarSetterType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' ) ;
    public final EObject ruleXVarSetterType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_exp_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:3596:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' ) )
            // InternalXScript.g:3597:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' )
            {
            // InternalXScript.g:3597:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';' )
            // InternalXScript.g:3598:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_exp_3_0= ruleXExpressionType ) ) otherlv_4= ';'
            {
            // InternalXScript.g:3598:3: ()
            // InternalXScript.g:3599:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarSetterTypeAccess().getXVarSetterTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3605:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3606:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3606:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3607:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarSetterTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_10); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarSetterTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_34); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarSetterTypeAccess().getEqualsSignKeyword_2());
            		
            // InternalXScript.g:3622:3: ( (lv_exp_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3623:4: (lv_exp_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3623:4: (lv_exp_3_0= ruleXExpressionType )
            // InternalXScript.g:3624:5: lv_exp_3_0= ruleXExpressionType
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
    // InternalXScript.g:3649:1: entryRuleXVarIncrementType returns [EObject current=null] : iv_ruleXVarIncrementType= ruleXVarIncrementType EOF ;
    public final EObject entryRuleXVarIncrementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarIncrementType = null;


        try {
            // InternalXScript.g:3649:58: (iv_ruleXVarIncrementType= ruleXVarIncrementType EOF )
            // InternalXScript.g:3650:2: iv_ruleXVarIncrementType= ruleXVarIncrementType EOF
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
    // InternalXScript.g:3656:1: ruleXVarIncrementType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' ) ;
    public final EObject ruleXVarIncrementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalXScript.g:3662:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' ) )
            // InternalXScript.g:3663:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' )
            {
            // InternalXScript.g:3663:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';' )
            // InternalXScript.g:3664:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '++' otherlv_3= ';'
            {
            // InternalXScript.g:3664:3: ()
            // InternalXScript.g:3665:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarIncrementTypeAccess().getXVarIncrementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3671:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3672:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3672:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3673:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarIncrementTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_39); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarIncrementTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,59,FOLLOW_12); 

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
    // InternalXScript.g:3696:1: entryRuleXVarDecrementType returns [EObject current=null] : iv_ruleXVarDecrementType= ruleXVarDecrementType EOF ;
    public final EObject entryRuleXVarDecrementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarDecrementType = null;


        try {
            // InternalXScript.g:3696:58: (iv_ruleXVarDecrementType= ruleXVarDecrementType EOF )
            // InternalXScript.g:3697:2: iv_ruleXVarDecrementType= ruleXVarDecrementType EOF
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
    // InternalXScript.g:3703:1: ruleXVarDecrementType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' ) ;
    public final EObject ruleXVarDecrementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalXScript.g:3709:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' ) )
            // InternalXScript.g:3710:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' )
            {
            // InternalXScript.g:3710:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';' )
            // InternalXScript.g:3711:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '--' otherlv_3= ';'
            {
            // InternalXScript.g:3711:3: ()
            // InternalXScript.g:3712:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarDecrementTypeAccess().getXVarDecrementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:3718:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:3719:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:3719:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:3720:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarDecrementTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_40); 

            					newLeafNode(otherlv_1, grammarAccess.getXVarDecrementTypeAccess().getVarXVarTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,60,FOLLOW_12); 

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
    // InternalXScript.g:3743:1: entryRuleXSetVarType returns [EObject current=null] : iv_ruleXSetVarType= ruleXSetVarType EOF ;
    public final EObject entryRuleXSetVarType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSetVarType = null;


        try {
            // InternalXScript.g:3743:52: (iv_ruleXSetVarType= ruleXSetVarType EOF )
            // InternalXScript.g:3744:2: iv_ruleXSetVarType= ruleXSetVarType EOF
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
    // InternalXScript.g:3750:1: ruleXSetVarType returns [EObject current=null] : ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' ) ;
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
            // InternalXScript.g:3756:2: ( ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:3757:2: ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:3757:2: ( () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:3758:3: () otherlv_1= 'setStreamVar' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_value_5_0= ruleXExpressionType ) ) otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:3758:3: ()
            // InternalXScript.g:3759:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSetVarTypeAccess().getXSetVarTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,61,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSetVarTypeAccess().getSetStreamVarKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSetVarTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3773:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:3774:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:3774:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:3775:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSetVarTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXSetVarTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_4, grammarAccess.getXSetVarTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:3790:3: ( (lv_value_5_0= ruleXExpressionType ) )
            // InternalXScript.g:3791:4: (lv_value_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:3791:4: (lv_value_5_0= ruleXExpressionType )
            // InternalXScript.g:3792:5: lv_value_5_0= ruleXExpressionType
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
    // InternalXScript.g:3821:1: entryRuleXDebugType returns [EObject current=null] : iv_ruleXDebugType= ruleXDebugType EOF ;
    public final EObject entryRuleXDebugType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXDebugType = null;


        try {
            // InternalXScript.g:3821:51: (iv_ruleXDebugType= ruleXDebugType EOF )
            // InternalXScript.g:3822:2: iv_ruleXDebugType= ruleXDebugType EOF
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
    // InternalXScript.g:3828:1: ruleXDebugType returns [EObject current=null] : ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' ) ;
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
            // InternalXScript.g:3834:2: ( ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' ) )
            // InternalXScript.g:3835:2: ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' )
            {
            // InternalXScript.g:3835:2: ( () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';' )
            // InternalXScript.g:3836:3: () otherlv_1= 'debug' otherlv_2= '(' ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )? otherlv_6= ')' otherlv_7= ';'
            {
            // InternalXScript.g:3836:3: ()
            // InternalXScript.g:3837:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXDebugTypeAccess().getXDebugTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,62,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXDebugTypeAccess().getDebugKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_41); 

            			newLeafNode(otherlv_2, grammarAccess.getXDebugTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3851:3: ( ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )* )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_ID && LA31_0<=RULE_STRING)||LA31_0==13||(LA31_0>=31 && LA31_0<=33)||LA31_0==37||LA31_0==43||(LA31_0>=69 && LA31_0<=70)||LA31_0==76||(LA31_0>=78 && LA31_0<=83)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalXScript.g:3852:4: ( (lv_args_3_0= ruleXExpressionType ) ) (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )*
                    {
                    // InternalXScript.g:3852:4: ( (lv_args_3_0= ruleXExpressionType ) )
                    // InternalXScript.g:3853:5: (lv_args_3_0= ruleXExpressionType )
                    {
                    // InternalXScript.g:3853:5: (lv_args_3_0= ruleXExpressionType )
                    // InternalXScript.g:3854:6: lv_args_3_0= ruleXExpressionType
                    {

                    						newCompositeNode(grammarAccess.getXDebugTypeAccess().getArgsXExpressionTypeParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_42);
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

                    // InternalXScript.g:3871:4: (otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) ) )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==14) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // InternalXScript.g:3872:5: otherlv_4= ',' ( (lv_args_5_0= ruleXExpressionType ) )
                    	    {
                    	    otherlv_4=(Token)match(input,14,FOLLOW_34); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getXDebugTypeAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalXScript.g:3876:5: ( (lv_args_5_0= ruleXExpressionType ) )
                    	    // InternalXScript.g:3877:6: (lv_args_5_0= ruleXExpressionType )
                    	    {
                    	    // InternalXScript.g:3877:6: (lv_args_5_0= ruleXExpressionType )
                    	    // InternalXScript.g:3878:7: lv_args_5_0= ruleXExpressionType
                    	    {

                    	    							newCompositeNode(grammarAccess.getXDebugTypeAccess().getArgsXExpressionTypeParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_42);
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
    // InternalXScript.g:3909:1: entryRuleXIfStatementType returns [EObject current=null] : iv_ruleXIfStatementType= ruleXIfStatementType EOF ;
    public final EObject entryRuleXIfStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXIfStatementType = null;


        try {
            // InternalXScript.g:3909:57: (iv_ruleXIfStatementType= ruleXIfStatementType EOF )
            // InternalXScript.g:3910:2: iv_ruleXIfStatementType= ruleXIfStatementType EOF
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
    // InternalXScript.g:3916:1: ruleXIfStatementType returns [EObject current=null] : ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? ) ;
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
            // InternalXScript.g:3922:2: ( ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? ) )
            // InternalXScript.g:3923:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? )
            {
            // InternalXScript.g:3923:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )? )
            // InternalXScript.g:3924:3: () otherlv_1= 'if' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )* ( (lv_elseElement_9_0= ruleXElseStatementType ) )?
            {
            // InternalXScript.g:3924:3: ()
            // InternalXScript.g:3925:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXIfStatementTypeAccess().getXIfStatementTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,63,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXIfStatementTypeAccess().getIfKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_34); 

            			newLeafNode(otherlv_2, grammarAccess.getXIfStatementTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:3939:3: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:3940:4: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:3940:4: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:3941:5: lv_expression_3_0= ruleXExpressionType
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
            		
            otherlv_5=(Token)match(input,38,FOLLOW_36); 

            			newLeafNode(otherlv_5, grammarAccess.getXIfStatementTypeAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalXScript.g:3966:3: ( (lv_elements_6_0= ruleXClassFunctionElementType ) )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==RULE_ID||LA32_0==51||LA32_0==53||(LA32_0>=55 && LA32_0<=58)||(LA32_0>=61 && LA32_0<=63)||LA32_0==66||LA32_0==68) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalXScript.g:3967:4: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:3967:4: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:3968:5: lv_elements_6_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXIfStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_36);
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

            otherlv_7=(Token)match(input,39,FOLLOW_43); 

            			newLeafNode(otherlv_7, grammarAccess.getXIfStatementTypeAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalXScript.g:3989:3: ( (lv_elseIfElements_8_0= ruleXElseIfStatementType ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==64) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalXScript.g:3990:4: (lv_elseIfElements_8_0= ruleXElseIfStatementType )
            	    {
            	    // InternalXScript.g:3990:4: (lv_elseIfElements_8_0= ruleXElseIfStatementType )
            	    // InternalXScript.g:3991:5: lv_elseIfElements_8_0= ruleXElseIfStatementType
            	    {

            	    					newCompositeNode(grammarAccess.getXIfStatementTypeAccess().getElseIfElementsXElseIfStatementTypeParserRuleCall_8_0());
            	    				
            	    pushFollow(FOLLOW_43);
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

            // InternalXScript.g:4008:3: ( (lv_elseElement_9_0= ruleXElseStatementType ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==65) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalXScript.g:4009:4: (lv_elseElement_9_0= ruleXElseStatementType )
                    {
                    // InternalXScript.g:4009:4: (lv_elseElement_9_0= ruleXElseStatementType )
                    // InternalXScript.g:4010:5: lv_elseElement_9_0= ruleXElseStatementType
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
    // InternalXScript.g:4031:1: entryRuleXElseIfStatementType returns [EObject current=null] : iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF ;
    public final EObject entryRuleXElseIfStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXElseIfStatementType = null;


        try {
            // InternalXScript.g:4031:61: (iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF )
            // InternalXScript.g:4032:2: iv_ruleXElseIfStatementType= ruleXElseIfStatementType EOF
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
    // InternalXScript.g:4038:1: ruleXElseIfStatementType returns [EObject current=null] : ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) ) ;
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
            // InternalXScript.g:4044:2: ( ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) ) )
            // InternalXScript.g:4045:2: ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) )
            {
            // InternalXScript.g:4045:2: ( () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' ) )
            // InternalXScript.g:4046:3: () (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' )
            {
            // InternalXScript.g:4046:3: ()
            // InternalXScript.g:4047:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXElseIfStatementTypeAccess().getXElseIfStatementTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:4053:3: (otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}' )
            // InternalXScript.g:4054:4: otherlv_1= 'elseif' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXClassFunctionElementType ) )* otherlv_7= '}'
            {
            otherlv_1=(Token)match(input,64,FOLLOW_5); 

            				newLeafNode(otherlv_1, grammarAccess.getXElseIfStatementTypeAccess().getElseifKeyword_1_0());
            			
            otherlv_2=(Token)match(input,13,FOLLOW_34); 

            				newLeafNode(otherlv_2, grammarAccess.getXElseIfStatementTypeAccess().getLeftParenthesisKeyword_1_1());
            			
            // InternalXScript.g:4062:4: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:4063:5: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:4063:5: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:4064:6: lv_expression_3_0= ruleXExpressionType
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
            			
            otherlv_5=(Token)match(input,38,FOLLOW_36); 

            				newLeafNode(otherlv_5, grammarAccess.getXElseIfStatementTypeAccess().getLeftCurlyBracketKeyword_1_4());
            			
            // InternalXScript.g:4089:4: ( (lv_elements_6_0= ruleXClassFunctionElementType ) )*
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_ID||LA35_0==51||LA35_0==53||(LA35_0>=55 && LA35_0<=58)||(LA35_0>=61 && LA35_0<=63)||LA35_0==66||LA35_0==68) ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // InternalXScript.g:4090:5: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:4090:5: (lv_elements_6_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:4091:6: lv_elements_6_0= ruleXClassFunctionElementType
            	    {

            	    						newCompositeNode(grammarAccess.getXElseIfStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_1_5_0());
            	    					
            	    pushFollow(FOLLOW_36);
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

            otherlv_7=(Token)match(input,39,FOLLOW_2); 

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
    // InternalXScript.g:4117:1: entryRuleXElseStatementType returns [EObject current=null] : iv_ruleXElseStatementType= ruleXElseStatementType EOF ;
    public final EObject entryRuleXElseStatementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXElseStatementType = null;


        try {
            // InternalXScript.g:4117:59: (iv_ruleXElseStatementType= ruleXElseStatementType EOF )
            // InternalXScript.g:4118:2: iv_ruleXElseStatementType= ruleXElseStatementType EOF
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
    // InternalXScript.g:4124:1: ruleXElseStatementType returns [EObject current=null] : ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' ) ;
    public final EObject ruleXElseStatementType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_elements_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4130:2: ( ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' ) )
            // InternalXScript.g:4131:2: ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' )
            {
            // InternalXScript.g:4131:2: ( () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}' )
            // InternalXScript.g:4132:3: () otherlv_1= 'else' otherlv_2= '{' ( (lv_elements_3_0= ruleXClassFunctionElementType ) )* otherlv_4= '}'
            {
            // InternalXScript.g:4132:3: ()
            // InternalXScript.g:4133:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXElseStatementTypeAccess().getXElseStatementTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,65,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getXElseStatementTypeAccess().getElseKeyword_1());
            		
            otherlv_2=(Token)match(input,38,FOLLOW_36); 

            			newLeafNode(otherlv_2, grammarAccess.getXElseStatementTypeAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalXScript.g:4147:3: ( (lv_elements_3_0= ruleXClassFunctionElementType ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_ID||LA36_0==51||LA36_0==53||(LA36_0>=55 && LA36_0<=58)||(LA36_0>=61 && LA36_0<=63)||LA36_0==66||LA36_0==68) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalXScript.g:4148:4: (lv_elements_3_0= ruleXClassFunctionElementType )
            	    {
            	    // InternalXScript.g:4148:4: (lv_elements_3_0= ruleXClassFunctionElementType )
            	    // InternalXScript.g:4149:5: lv_elements_3_0= ruleXClassFunctionElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXElseStatementTypeAccess().getElementsXClassFunctionElementTypeParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_36);
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

            otherlv_4=(Token)match(input,39,FOLLOW_2); 

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
    // InternalXScript.g:4174:1: entryRuleXWhileType returns [EObject current=null] : iv_ruleXWhileType= ruleXWhileType EOF ;
    public final EObject entryRuleXWhileType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileType = null;


        try {
            // InternalXScript.g:4174:51: (iv_ruleXWhileType= ruleXWhileType EOF )
            // InternalXScript.g:4175:2: iv_ruleXWhileType= ruleXWhileType EOF
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
    // InternalXScript.g:4181:1: ruleXWhileType returns [EObject current=null] : ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' ) ;
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
            // InternalXScript.g:4187:2: ( ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' ) )
            // InternalXScript.g:4188:2: ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' )
            {
            // InternalXScript.g:4188:2: ( () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}' )
            // InternalXScript.g:4189:3: () otherlv_1= 'whilst' otherlv_2= '(' ( (lv_expression_3_0= ruleXExpressionType ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_elements_6_0= ruleXWhileElementType ) )* otherlv_7= '}'
            {
            // InternalXScript.g:4189:3: ()
            // InternalXScript.g:4190:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXWhileTypeAccess().getXWhileTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,66,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXWhileTypeAccess().getWhilstKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_34); 

            			newLeafNode(otherlv_2, grammarAccess.getXWhileTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:4204:3: ( (lv_expression_3_0= ruleXExpressionType ) )
            // InternalXScript.g:4205:4: (lv_expression_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:4205:4: (lv_expression_3_0= ruleXExpressionType )
            // InternalXScript.g:4206:5: lv_expression_3_0= ruleXExpressionType
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
            		
            otherlv_5=(Token)match(input,38,FOLLOW_44); 

            			newLeafNode(otherlv_5, grammarAccess.getXWhileTypeAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalXScript.g:4231:3: ( (lv_elements_6_0= ruleXWhileElementType ) )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==RULE_ID||LA37_0==51||LA37_0==53||(LA37_0>=55 && LA37_0<=58)||(LA37_0>=61 && LA37_0<=63)||(LA37_0>=66 && LA37_0<=68)) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalXScript.g:4232:4: (lv_elements_6_0= ruleXWhileElementType )
            	    {
            	    // InternalXScript.g:4232:4: (lv_elements_6_0= ruleXWhileElementType )
            	    // InternalXScript.g:4233:5: lv_elements_6_0= ruleXWhileElementType
            	    {

            	    					newCompositeNode(grammarAccess.getXWhileTypeAccess().getElementsXWhileElementTypeParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_44);
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

            otherlv_7=(Token)match(input,39,FOLLOW_2); 

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
    // InternalXScript.g:4258:1: entryRuleXWhileElementType returns [EObject current=null] : iv_ruleXWhileElementType= ruleXWhileElementType EOF ;
    public final EObject entryRuleXWhileElementType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileElementType = null;


        try {
            // InternalXScript.g:4258:58: (iv_ruleXWhileElementType= ruleXWhileElementType EOF )
            // InternalXScript.g:4259:2: iv_ruleXWhileElementType= ruleXWhileElementType EOF
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
    // InternalXScript.g:4265:1: ruleXWhileElementType returns [EObject current=null] : (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType ) ;
    public final EObject ruleXWhileElementType() throws RecognitionException {
        EObject current = null;

        EObject this_XWhileBreakType_0 = null;

        EObject this_XClassFunctionElementType_1 = null;



        	enterRule();

        try {
            // InternalXScript.g:4271:2: ( (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType ) )
            // InternalXScript.g:4272:2: (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType )
            {
            // InternalXScript.g:4272:2: (this_XWhileBreakType_0= ruleXWhileBreakType | this_XClassFunctionElementType_1= ruleXClassFunctionElementType )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==67) ) {
                alt38=1;
            }
            else if ( (LA38_0==RULE_ID||LA38_0==51||LA38_0==53||(LA38_0>=55 && LA38_0<=58)||(LA38_0>=61 && LA38_0<=63)||LA38_0==66||LA38_0==68) ) {
                alt38=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;
            }
            switch (alt38) {
                case 1 :
                    // InternalXScript.g:4273:3: this_XWhileBreakType_0= ruleXWhileBreakType
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
                    // InternalXScript.g:4282:3: this_XClassFunctionElementType_1= ruleXClassFunctionElementType
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
    // InternalXScript.g:4294:1: entryRuleXWhileBreakType returns [EObject current=null] : iv_ruleXWhileBreakType= ruleXWhileBreakType EOF ;
    public final EObject entryRuleXWhileBreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXWhileBreakType = null;


        try {
            // InternalXScript.g:4294:56: (iv_ruleXWhileBreakType= ruleXWhileBreakType EOF )
            // InternalXScript.g:4295:2: iv_ruleXWhileBreakType= ruleXWhileBreakType EOF
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
    // InternalXScript.g:4301:1: ruleXWhileBreakType returns [EObject current=null] : ( () otherlv_1= 'break' otherlv_2= ';' ) ;
    public final EObject ruleXWhileBreakType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalXScript.g:4307:2: ( ( () otherlv_1= 'break' otherlv_2= ';' ) )
            // InternalXScript.g:4308:2: ( () otherlv_1= 'break' otherlv_2= ';' )
            {
            // InternalXScript.g:4308:2: ( () otherlv_1= 'break' otherlv_2= ';' )
            // InternalXScript.g:4309:3: () otherlv_1= 'break' otherlv_2= ';'
            {
            // InternalXScript.g:4309:3: ()
            // InternalXScript.g:4310:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXWhileBreakTypeAccess().getXWhileBreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,67,FOLLOW_12); 

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
    // InternalXScript.g:4328:1: entryRuleXSleepType returns [EObject current=null] : iv_ruleXSleepType= ruleXSleepType EOF ;
    public final EObject entryRuleXSleepType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSleepType = null;


        try {
            // InternalXScript.g:4328:51: (iv_ruleXSleepType= ruleXSleepType EOF )
            // InternalXScript.g:4329:2: iv_ruleXSleepType= ruleXSleepType EOF
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
    // InternalXScript.g:4335:1: ruleXSleepType returns [EObject current=null] : ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' ) ;
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
            // InternalXScript.g:4341:2: ( ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' ) )
            // InternalXScript.g:4342:2: ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' )
            {
            // InternalXScript.g:4342:2: ( () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';' )
            // InternalXScript.g:4343:3: () otherlv_1= 'sleep' otherlv_2= '(' ( (lv_interval_3_0= RULE_INT ) ) ( (lv_unit_4_0= ruleStreamTimeUnit ) ) otherlv_5= ')' otherlv_6= ';'
            {
            // InternalXScript.g:4343:3: ()
            // InternalXScript.g:4344:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSleepTypeAccess().getXSleepTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,68,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSleepTypeAccess().getSleepKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_6); 

            			newLeafNode(otherlv_2, grammarAccess.getXSleepTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:4358:3: ( (lv_interval_3_0= RULE_INT ) )
            // InternalXScript.g:4359:4: (lv_interval_3_0= RULE_INT )
            {
            // InternalXScript.g:4359:4: (lv_interval_3_0= RULE_INT )
            // InternalXScript.g:4360:5: lv_interval_3_0= RULE_INT
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

            // InternalXScript.g:4376:3: ( (lv_unit_4_0= ruleStreamTimeUnit ) )
            // InternalXScript.g:4377:4: (lv_unit_4_0= ruleStreamTimeUnit )
            {
            // InternalXScript.g:4377:4: (lv_unit_4_0= ruleStreamTimeUnit )
            // InternalXScript.g:4378:5: lv_unit_4_0= ruleStreamTimeUnit
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
    // InternalXScript.g:4407:1: entryRuleXExpressionType returns [EObject current=null] : iv_ruleXExpressionType= ruleXExpressionType EOF ;
    public final EObject entryRuleXExpressionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXExpressionType = null;


        try {
            // InternalXScript.g:4407:56: (iv_ruleXExpressionType= ruleXExpressionType EOF )
            // InternalXScript.g:4408:2: iv_ruleXExpressionType= ruleXExpressionType EOF
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
    // InternalXScript.g:4414:1: ruleXExpressionType returns [EObject current=null] : this_XOrType_0= ruleXOrType ;
    public final EObject ruleXExpressionType() throws RecognitionException {
        EObject current = null;

        EObject this_XOrType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4420:2: (this_XOrType_0= ruleXOrType )
            // InternalXScript.g:4421:2: this_XOrType_0= ruleXOrType
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
    // InternalXScript.g:4432:1: entryRuleXOrType returns [EObject current=null] : iv_ruleXOrType= ruleXOrType EOF ;
    public final EObject entryRuleXOrType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXOrType = null;


        try {
            // InternalXScript.g:4432:48: (iv_ruleXOrType= ruleXOrType EOF )
            // InternalXScript.g:4433:2: iv_ruleXOrType= ruleXOrType EOF
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
    // InternalXScript.g:4439:1: ruleXOrType returns [EObject current=null] : (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* ) ;
    public final EObject ruleXOrType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XAndType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4445:2: ( (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* ) )
            // InternalXScript.g:4446:2: (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* )
            {
            // InternalXScript.g:4446:2: (this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )* )
            // InternalXScript.g:4447:3: this_XAndType_0= ruleXAndType ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXOrTypeAccess().getXAndTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_13);
            this_XAndType_0=ruleXAndType();

            state._fsp--;


            			current = this_XAndType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4455:3: ( () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) ) )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==19) ) {
                    alt39=1;
                }


                switch (alt39) {
            	case 1 :
            	    // InternalXScript.g:4456:4: () otherlv_2= '||' ( (lv_right_3_0= ruleXAndType ) )
            	    {
            	    // InternalXScript.g:4456:4: ()
            	    // InternalXScript.g:4457:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXOrTypeAccess().getXOrTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,19,FOLLOW_34); 

            	    				newLeafNode(otherlv_2, grammarAccess.getXOrTypeAccess().getVerticalLineVerticalLineKeyword_1_1());
            	    			
            	    // InternalXScript.g:4467:4: ( (lv_right_3_0= ruleXAndType ) )
            	    // InternalXScript.g:4468:5: (lv_right_3_0= ruleXAndType )
            	    {
            	    // InternalXScript.g:4468:5: (lv_right_3_0= ruleXAndType )
            	    // InternalXScript.g:4469:6: lv_right_3_0= ruleXAndType
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
    // InternalXScript.g:4491:1: entryRuleXAndType returns [EObject current=null] : iv_ruleXAndType= ruleXAndType EOF ;
    public final EObject entryRuleXAndType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAndType = null;


        try {
            // InternalXScript.g:4491:49: (iv_ruleXAndType= ruleXAndType EOF )
            // InternalXScript.g:4492:2: iv_ruleXAndType= ruleXAndType EOF
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
    // InternalXScript.g:4498:1: ruleXAndType returns [EObject current=null] : (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* ) ;
    public final EObject ruleXAndType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_XEqualityType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4504:2: ( (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* ) )
            // InternalXScript.g:4505:2: (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* )
            {
            // InternalXScript.g:4505:2: (this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )* )
            // InternalXScript.g:4506:3: this_XEqualityType_0= ruleXEqualityType ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXAndTypeAccess().getXEqualityTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_14);
            this_XEqualityType_0=ruleXEqualityType();

            state._fsp--;


            			current = this_XEqualityType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4514:3: ( () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==20) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalXScript.g:4515:4: () otherlv_2= '&&' ( (lv_right_3_0= ruleXEqualityType ) )
            	    {
            	    // InternalXScript.g:4515:4: ()
            	    // InternalXScript.g:4516:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXAndTypeAccess().getXAndTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    otherlv_2=(Token)match(input,20,FOLLOW_34); 

            	    				newLeafNode(otherlv_2, grammarAccess.getXAndTypeAccess().getAmpersandAmpersandKeyword_1_1());
            	    			
            	    // InternalXScript.g:4526:4: ( (lv_right_3_0= ruleXEqualityType ) )
            	    // InternalXScript.g:4527:5: (lv_right_3_0= ruleXEqualityType )
            	    {
            	    // InternalXScript.g:4527:5: (lv_right_3_0= ruleXEqualityType )
            	    // InternalXScript.g:4528:6: lv_right_3_0= ruleXEqualityType
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
    // InternalXScript.g:4550:1: entryRuleXEqualityType returns [EObject current=null] : iv_ruleXEqualityType= ruleXEqualityType EOF ;
    public final EObject entryRuleXEqualityType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXEqualityType = null;


        try {
            // InternalXScript.g:4550:54: (iv_ruleXEqualityType= ruleXEqualityType EOF )
            // InternalXScript.g:4551:2: iv_ruleXEqualityType= ruleXEqualityType EOF
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
    // InternalXScript.g:4557:1: ruleXEqualityType returns [EObject current=null] : (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* ) ;
    public final EObject ruleXEqualityType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_XComparisonType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4563:2: ( (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* ) )
            // InternalXScript.g:4564:2: (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* )
            {
            // InternalXScript.g:4564:2: (this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )* )
            // InternalXScript.g:4565:3: this_XComparisonType_0= ruleXComparisonType ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXEqualityTypeAccess().getXComparisonTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_15);
            this_XComparisonType_0=ruleXComparisonType();

            state._fsp--;


            			current = this_XComparisonType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4573:3: ( () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( ((LA42_0>=21 && LA42_0<=22)) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalXScript.g:4574:4: () ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) ) ( (lv_right_3_0= ruleXComparisonType ) )
            	    {
            	    // InternalXScript.g:4574:4: ()
            	    // InternalXScript.g:4575:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXEqualityTypeAccess().getXEqualityTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4581:4: ( ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) ) )
            	    // InternalXScript.g:4582:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    {
            	    // InternalXScript.g:4582:5: ( (lv_op_2_1= '==' | lv_op_2_2= '!=' ) )
            	    // InternalXScript.g:4583:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    {
            	    // InternalXScript.g:4583:6: (lv_op_2_1= '==' | lv_op_2_2= '!=' )
            	    int alt41=2;
            	    int LA41_0 = input.LA(1);

            	    if ( (LA41_0==21) ) {
            	        alt41=1;
            	    }
            	    else if ( (LA41_0==22) ) {
            	        alt41=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 41, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt41) {
            	        case 1 :
            	            // InternalXScript.g:4584:7: lv_op_2_1= '=='
            	            {
            	            lv_op_2_1=(Token)match(input,21,FOLLOW_34); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXEqualityTypeAccess().getOpEqualsSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXEqualityTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4595:7: lv_op_2_2= '!='
            	            {
            	            lv_op_2_2=(Token)match(input,22,FOLLOW_34); 

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

            	    // InternalXScript.g:4608:4: ( (lv_right_3_0= ruleXComparisonType ) )
            	    // InternalXScript.g:4609:5: (lv_right_3_0= ruleXComparisonType )
            	    {
            	    // InternalXScript.g:4609:5: (lv_right_3_0= ruleXComparisonType )
            	    // InternalXScript.g:4610:6: lv_right_3_0= ruleXComparisonType
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
    // InternalXScript.g:4632:1: entryRuleXComparisonType returns [EObject current=null] : iv_ruleXComparisonType= ruleXComparisonType EOF ;
    public final EObject entryRuleXComparisonType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXComparisonType = null;


        try {
            // InternalXScript.g:4632:56: (iv_ruleXComparisonType= ruleXComparisonType EOF )
            // InternalXScript.g:4633:2: iv_ruleXComparisonType= ruleXComparisonType EOF
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
    // InternalXScript.g:4639:1: ruleXComparisonType returns [EObject current=null] : (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* ) ;
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
            // InternalXScript.g:4645:2: ( (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* ) )
            // InternalXScript.g:4646:2: (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* )
            {
            // InternalXScript.g:4646:2: (this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )* )
            // InternalXScript.g:4647:3: this_XPlusOrMinusType_0= ruleXPlusOrMinusType ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXComparisonTypeAccess().getXPlusOrMinusTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_16);
            this_XPlusOrMinusType_0=ruleXPlusOrMinusType();

            state._fsp--;


            			current = this_XPlusOrMinusType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4655:3: ( () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) ) )*
            loop44:
            do {
                int alt44=2;
                int LA44_0 = input.LA(1);

                if ( ((LA44_0>=23 && LA44_0<=26)) ) {
                    alt44=1;
                }


                switch (alt44) {
            	case 1 :
            	    // InternalXScript.g:4656:4: () ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) ) ( (lv_right_3_0= ruleXPlusOrMinusType ) )
            	    {
            	    // InternalXScript.g:4656:4: ()
            	    // InternalXScript.g:4657:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXComparisonTypeAccess().getXComparisonTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4663:4: ( ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) ) )
            	    // InternalXScript.g:4664:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    {
            	    // InternalXScript.g:4664:5: ( (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' ) )
            	    // InternalXScript.g:4665:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    {
            	    // InternalXScript.g:4665:6: (lv_op_2_1= '>=' | lv_op_2_2= '<=' | lv_op_2_3= '>' | lv_op_2_4= '<' )
            	    int alt43=4;
            	    switch ( input.LA(1) ) {
            	    case 23:
            	        {
            	        alt43=1;
            	        }
            	        break;
            	    case 24:
            	        {
            	        alt43=2;
            	        }
            	        break;
            	    case 25:
            	        {
            	        alt43=3;
            	        }
            	        break;
            	    case 26:
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
            	            // InternalXScript.g:4666:7: lv_op_2_1= '>='
            	            {
            	            lv_op_2_1=(Token)match(input,23,FOLLOW_34); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXComparisonTypeAccess().getOpGreaterThanSignEqualsSignKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4677:7: lv_op_2_2= '<='
            	            {
            	            lv_op_2_2=(Token)match(input,24,FOLLOW_34); 

            	            							newLeafNode(lv_op_2_2, grammarAccess.getXComparisonTypeAccess().getOpLessThanSignEqualsSignKeyword_1_1_0_1());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_2, null);
            	            						

            	            }
            	            break;
            	        case 3 :
            	            // InternalXScript.g:4688:7: lv_op_2_3= '>'
            	            {
            	            lv_op_2_3=(Token)match(input,25,FOLLOW_34); 

            	            							newLeafNode(lv_op_2_3, grammarAccess.getXComparisonTypeAccess().getOpGreaterThanSignKeyword_1_1_0_2());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXComparisonTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_3, null);
            	            						

            	            }
            	            break;
            	        case 4 :
            	            // InternalXScript.g:4699:7: lv_op_2_4= '<'
            	            {
            	            lv_op_2_4=(Token)match(input,26,FOLLOW_34); 

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

            	    // InternalXScript.g:4712:4: ( (lv_right_3_0= ruleXPlusOrMinusType ) )
            	    // InternalXScript.g:4713:5: (lv_right_3_0= ruleXPlusOrMinusType )
            	    {
            	    // InternalXScript.g:4713:5: (lv_right_3_0= ruleXPlusOrMinusType )
            	    // InternalXScript.g:4714:6: lv_right_3_0= ruleXPlusOrMinusType
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
    // InternalXScript.g:4736:1: entryRuleXPlusOrMinusType returns [EObject current=null] : iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF ;
    public final EObject entryRuleXPlusOrMinusType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPlusOrMinusType = null;


        try {
            // InternalXScript.g:4736:57: (iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF )
            // InternalXScript.g:4737:2: iv_ruleXPlusOrMinusType= ruleXPlusOrMinusType EOF
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
    // InternalXScript.g:4743:1: ruleXPlusOrMinusType returns [EObject current=null] : (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* ) ;
    public final EObject ruleXPlusOrMinusType() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_XMulOrDivType_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4749:2: ( (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* ) )
            // InternalXScript.g:4750:2: (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* )
            {
            // InternalXScript.g:4750:2: (this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )* )
            // InternalXScript.g:4751:3: this_XMulOrDivType_0= ruleXMulOrDivType ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXPlusOrMinusTypeAccess().getXMulOrDivTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_17);
            this_XMulOrDivType_0=ruleXMulOrDivType();

            state._fsp--;


            			current = this_XMulOrDivType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4759:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) ) )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( ((LA46_0>=27 && LA46_0<=28)) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // InternalXScript.g:4760:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleXMulOrDivType ) )
            	    {
            	    // InternalXScript.g:4760:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
            	    int alt45=2;
            	    int LA45_0 = input.LA(1);

            	    if ( (LA45_0==27) ) {
            	        alt45=1;
            	    }
            	    else if ( (LA45_0==28) ) {
            	        alt45=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 45, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt45) {
            	        case 1 :
            	            // InternalXScript.g:4761:5: ( () otherlv_2= '+' )
            	            {
            	            // InternalXScript.g:4761:5: ( () otherlv_2= '+' )
            	            // InternalXScript.g:4762:6: () otherlv_2= '+'
            	            {
            	            // InternalXScript.g:4762:6: ()
            	            // InternalXScript.g:4763:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getXPlusOrMinusTypeAccess().getXPlusTypeLeftAction_1_0_0_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_2=(Token)match(input,27,FOLLOW_34); 

            	            						newLeafNode(otherlv_2, grammarAccess.getXPlusOrMinusTypeAccess().getPlusSignKeyword_1_0_0_1());
            	            					

            	            }


            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4775:5: ( () otherlv_4= '-' )
            	            {
            	            // InternalXScript.g:4775:5: ( () otherlv_4= '-' )
            	            // InternalXScript.g:4776:6: () otherlv_4= '-'
            	            {
            	            // InternalXScript.g:4776:6: ()
            	            // InternalXScript.g:4777:7: 
            	            {

            	            							current = forceCreateModelElementAndSet(
            	            								grammarAccess.getXPlusOrMinusTypeAccess().getXMinusTypeLeftAction_1_0_1_0(),
            	            								current);
            	            						

            	            }

            	            otherlv_4=(Token)match(input,28,FOLLOW_34); 

            	            						newLeafNode(otherlv_4, grammarAccess.getXPlusOrMinusTypeAccess().getHyphenMinusKeyword_1_0_1_1());
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    // InternalXScript.g:4789:4: ( (lv_right_5_0= ruleXMulOrDivType ) )
            	    // InternalXScript.g:4790:5: (lv_right_5_0= ruleXMulOrDivType )
            	    {
            	    // InternalXScript.g:4790:5: (lv_right_5_0= ruleXMulOrDivType )
            	    // InternalXScript.g:4791:6: lv_right_5_0= ruleXMulOrDivType
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
    // InternalXScript.g:4813:1: entryRuleXMulOrDivType returns [EObject current=null] : iv_ruleXMulOrDivType= ruleXMulOrDivType EOF ;
    public final EObject entryRuleXMulOrDivType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXMulOrDivType = null;


        try {
            // InternalXScript.g:4813:54: (iv_ruleXMulOrDivType= ruleXMulOrDivType EOF )
            // InternalXScript.g:4814:2: iv_ruleXMulOrDivType= ruleXMulOrDivType EOF
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
    // InternalXScript.g:4820:1: ruleXMulOrDivType returns [EObject current=null] : (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* ) ;
    public final EObject ruleXMulOrDivType() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        EObject this_XPrimaryType_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4826:2: ( (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* ) )
            // InternalXScript.g:4827:2: (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* )
            {
            // InternalXScript.g:4827:2: (this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )* )
            // InternalXScript.g:4828:3: this_XPrimaryType_0= ruleXPrimaryType ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )*
            {

            			newCompositeNode(grammarAccess.getXMulOrDivTypeAccess().getXPrimaryTypeParserRuleCall_0());
            		
            pushFollow(FOLLOW_18);
            this_XPrimaryType_0=ruleXPrimaryType();

            state._fsp--;


            			current = this_XPrimaryType_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalXScript.g:4836:3: ( () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) ) )*
            loop48:
            do {
                int alt48=2;
                int LA48_0 = input.LA(1);

                if ( ((LA48_0>=29 && LA48_0<=30)) ) {
                    alt48=1;
                }


                switch (alt48) {
            	case 1 :
            	    // InternalXScript.g:4837:4: () ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) ) ( (lv_right_3_0= ruleXPrimaryType ) )
            	    {
            	    // InternalXScript.g:4837:4: ()
            	    // InternalXScript.g:4838:5: 
            	    {

            	    					current = forceCreateModelElementAndSet(
            	    						grammarAccess.getXMulOrDivTypeAccess().getXMulOrDivTypeLeftAction_1_0(),
            	    						current);
            	    				

            	    }

            	    // InternalXScript.g:4844:4: ( ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) ) )
            	    // InternalXScript.g:4845:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    {
            	    // InternalXScript.g:4845:5: ( (lv_op_2_1= '*' | lv_op_2_2= '/' ) )
            	    // InternalXScript.g:4846:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    {
            	    // InternalXScript.g:4846:6: (lv_op_2_1= '*' | lv_op_2_2= '/' )
            	    int alt47=2;
            	    int LA47_0 = input.LA(1);

            	    if ( (LA47_0==29) ) {
            	        alt47=1;
            	    }
            	    else if ( (LA47_0==30) ) {
            	        alt47=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 47, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt47) {
            	        case 1 :
            	            // InternalXScript.g:4847:7: lv_op_2_1= '*'
            	            {
            	            lv_op_2_1=(Token)match(input,29,FOLLOW_34); 

            	            							newLeafNode(lv_op_2_1, grammarAccess.getXMulOrDivTypeAccess().getOpAsteriskKeyword_1_1_0_0());
            	            						

            	            							if (current==null) {
            	            								current = createModelElement(grammarAccess.getXMulOrDivTypeRule());
            	            							}
            	            							setWithLastConsumed(current, "op", lv_op_2_1, null);
            	            						

            	            }
            	            break;
            	        case 2 :
            	            // InternalXScript.g:4858:7: lv_op_2_2= '/'
            	            {
            	            lv_op_2_2=(Token)match(input,30,FOLLOW_34); 

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

            	    // InternalXScript.g:4871:4: ( (lv_right_3_0= ruleXPrimaryType ) )
            	    // InternalXScript.g:4872:5: (lv_right_3_0= ruleXPrimaryType )
            	    {
            	    // InternalXScript.g:4872:5: (lv_right_3_0= ruleXPrimaryType )
            	    // InternalXScript.g:4873:6: lv_right_3_0= ruleXPrimaryType
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
    // InternalXScript.g:4895:1: entryRuleXPrimaryType returns [EObject current=null] : iv_ruleXPrimaryType= ruleXPrimaryType EOF ;
    public final EObject entryRuleXPrimaryType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPrimaryType = null;


        try {
            // InternalXScript.g:4895:53: (iv_ruleXPrimaryType= ruleXPrimaryType EOF )
            // InternalXScript.g:4896:2: iv_ruleXPrimaryType= ruleXPrimaryType EOF
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
    // InternalXScript.g:4902:1: ruleXPrimaryType returns [EObject current=null] : ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType ) ;
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
            // InternalXScript.g:4908:2: ( ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType ) )
            // InternalXScript.g:4909:2: ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType )
            {
            // InternalXScript.g:4909:2: ( (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' ) | ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) ) | this_XAtomicType_6= ruleXAtomicType )
            int alt49=3;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt49=1;
                }
                break;
            case 31:
                {
                alt49=2;
                }
                break;
            case RULE_ID:
            case RULE_INT:
            case RULE_DOUBLE:
            case RULE_STRING:
            case 32:
            case 33:
            case 37:
            case 43:
            case 69:
            case 70:
            case 76:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
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
                    // InternalXScript.g:4910:3: (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' )
                    {
                    // InternalXScript.g:4910:3: (otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')' )
                    // InternalXScript.g:4911:4: otherlv_0= '(' this_XExpressionType_1= ruleXExpressionType otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,13,FOLLOW_34); 

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
                    // InternalXScript.g:4929:3: ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) )
                    {
                    // InternalXScript.g:4929:3: ( () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) ) )
                    // InternalXScript.g:4930:4: () otherlv_4= '!' ( (lv_expression_5_0= ruleXPrimaryType ) )
                    {
                    // InternalXScript.g:4930:4: ()
                    // InternalXScript.g:4931:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXPrimaryTypeAccess().getXNotTypeAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_4=(Token)match(input,31,FOLLOW_34); 

                    				newLeafNode(otherlv_4, grammarAccess.getXPrimaryTypeAccess().getExclamationMarkKeyword_1_1());
                    			
                    // InternalXScript.g:4941:4: ( (lv_expression_5_0= ruleXPrimaryType ) )
                    // InternalXScript.g:4942:5: (lv_expression_5_0= ruleXPrimaryType )
                    {
                    // InternalXScript.g:4942:5: (lv_expression_5_0= ruleXPrimaryType )
                    // InternalXScript.g:4943:6: lv_expression_5_0= ruleXPrimaryType
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
                    // InternalXScript.g:4962:3: this_XAtomicType_6= ruleXAtomicType
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
    // InternalXScript.g:4974:1: entryRuleXAtomicType returns [EObject current=null] : iv_ruleXAtomicType= ruleXAtomicType EOF ;
    public final EObject entryRuleXAtomicType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAtomicType = null;


        try {
            // InternalXScript.g:4974:52: (iv_ruleXAtomicType= ruleXAtomicType EOF )
            // InternalXScript.g:4975:2: iv_ruleXAtomicType= ruleXAtomicType EOF
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
    // InternalXScript.g:4981:1: ruleXAtomicType returns [EObject current=null] : this_XAtomicBaseType_0= ruleXAtomicBaseType ;
    public final EObject ruleXAtomicType() throws RecognitionException {
        EObject current = null;

        EObject this_XAtomicBaseType_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:4987:2: (this_XAtomicBaseType_0= ruleXAtomicBaseType )
            // InternalXScript.g:4988:2: this_XAtomicBaseType_0= ruleXAtomicBaseType
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
    // InternalXScript.g:4999:1: entryRuleXAtomicBaseType returns [EObject current=null] : iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF ;
    public final EObject entryRuleXAtomicBaseType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXAtomicBaseType = null;


        try {
            // InternalXScript.g:4999:56: (iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF )
            // InternalXScript.g:5000:2: iv_ruleXAtomicBaseType= ruleXAtomicBaseType EOF
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
    // InternalXScript.g:5006:1: ruleXAtomicBaseType returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType ) ;
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
            // InternalXScript.g:5012:2: ( ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType ) )
            // InternalXScript.g:5013:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )
            {
            // InternalXScript.g:5013:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )
            int alt51=18;
            alt51 = dfa51.predict(input);
            switch (alt51) {
                case 1 :
                    // InternalXScript.g:5014:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    {
                    // InternalXScript.g:5014:3: ( () ( (lv_value_1_0= RULE_DOUBLE ) ) )
                    // InternalXScript.g:5015:4: () ( (lv_value_1_0= RULE_DOUBLE ) )
                    {
                    // InternalXScript.g:5015:4: ()
                    // InternalXScript.g:5016:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXDoubleConstantTypeAction_0_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:5022:4: ( (lv_value_1_0= RULE_DOUBLE ) )
                    // InternalXScript.g:5023:5: (lv_value_1_0= RULE_DOUBLE )
                    {
                    // InternalXScript.g:5023:5: (lv_value_1_0= RULE_DOUBLE )
                    // InternalXScript.g:5024:6: lv_value_1_0= RULE_DOUBLE
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
                    // InternalXScript.g:5042:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    {
                    // InternalXScript.g:5042:3: ( () ( (lv_value_3_0= RULE_INT ) ) )
                    // InternalXScript.g:5043:4: () ( (lv_value_3_0= RULE_INT ) )
                    {
                    // InternalXScript.g:5043:4: ()
                    // InternalXScript.g:5044:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXIntConstantTypeAction_1_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:5050:4: ( (lv_value_3_0= RULE_INT ) )
                    // InternalXScript.g:5051:5: (lv_value_3_0= RULE_INT )
                    {
                    // InternalXScript.g:5051:5: (lv_value_3_0= RULE_INT )
                    // InternalXScript.g:5052:6: lv_value_3_0= RULE_INT
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
                    // InternalXScript.g:5070:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    {
                    // InternalXScript.g:5070:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    // InternalXScript.g:5071:4: () ( (lv_value_5_0= RULE_STRING ) )
                    {
                    // InternalXScript.g:5071:4: ()
                    // InternalXScript.g:5072:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXStringConstantTypeAction_2_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:5078:4: ( (lv_value_5_0= RULE_STRING ) )
                    // InternalXScript.g:5079:5: (lv_value_5_0= RULE_STRING )
                    {
                    // InternalXScript.g:5079:5: (lv_value_5_0= RULE_STRING )
                    // InternalXScript.g:5080:6: lv_value_5_0= RULE_STRING
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
                    // InternalXScript.g:5098:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    {
                    // InternalXScript.g:5098:3: ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) )
                    // InternalXScript.g:5099:4: () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    {
                    // InternalXScript.g:5099:4: ()
                    // InternalXScript.g:5100:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getXAtomicBaseTypeAccess().getXBoolConstantTypeAction_3_0(),
                    						current);
                    				

                    }

                    // InternalXScript.g:5106:4: ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) )
                    // InternalXScript.g:5107:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    {
                    // InternalXScript.g:5107:5: ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) )
                    // InternalXScript.g:5108:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    {
                    // InternalXScript.g:5108:6: (lv_value_7_1= 'true' | lv_value_7_2= 'false' )
                    int alt50=2;
                    int LA50_0 = input.LA(1);

                    if ( (LA50_0==32) ) {
                        alt50=1;
                    }
                    else if ( (LA50_0==33) ) {
                        alt50=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 50, 0, input);

                        throw nvae;
                    }
                    switch (alt50) {
                        case 1 :
                            // InternalXScript.g:5109:7: lv_value_7_1= 'true'
                            {
                            lv_value_7_1=(Token)match(input,32,FOLLOW_2); 

                            							newLeafNode(lv_value_7_1, grammarAccess.getXAtomicBaseTypeAccess().getValueTrueKeyword_3_1_0_0());
                            						

                            							if (current==null) {
                            								current = createModelElement(grammarAccess.getXAtomicBaseTypeRule());
                            							}
                            							setWithLastConsumed(current, "value", lv_value_7_1, null);
                            						

                            }
                            break;
                        case 2 :
                            // InternalXScript.g:5120:7: lv_value_7_2= 'false'
                            {
                            lv_value_7_2=(Token)match(input,33,FOLLOW_2); 

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
                    // InternalXScript.g:5135:3: this_XPercentChangeExpType_8= ruleXPercentChangeExpType
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
                    // InternalXScript.g:5144:3: this_XSubExpType_9= ruleXSubExpType
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
                    // InternalXScript.g:5153:3: this_XVarExpType_10= ruleXVarExpType
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
                    // InternalXScript.g:5162:3: this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType
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
                    // InternalXScript.g:5171:3: this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType
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
                    // InternalXScript.g:5180:3: this_XFunctionCallExpType_13= ruleXFunctionCallExpType
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
                    // InternalXScript.g:5189:3: this_XVarStreakType_14= ruleXVarStreakType
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
                    // InternalXScript.g:5198:3: this_XVarCompareStreakType_15= ruleXVarCompareStreakType
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
                    // InternalXScript.g:5207:3: this_XSlrAvgExpType_16= ruleXSlrAvgExpType
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
                    // InternalXScript.g:5216:3: this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType
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
                    // InternalXScript.g:5225:3: this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType
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
                    // InternalXScript.g:5234:3: this_XVarianceAverageType_19= ruleXVarianceAverageType
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
                    // InternalXScript.g:5243:3: this_XRocExpType_20= ruleXRocExpType
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
                    // InternalXScript.g:5252:3: this_XVarianceMaxType_21= ruleXVarianceMaxType
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
    // InternalXScript.g:5264:1: entryRuleXPercentChangeExpType returns [EObject current=null] : iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF ;
    public final EObject entryRuleXPercentChangeExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXPercentChangeExpType = null;


        try {
            // InternalXScript.g:5264:62: (iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF )
            // InternalXScript.g:5265:2: iv_ruleXPercentChangeExpType= ruleXPercentChangeExpType EOF
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
    // InternalXScript.g:5271:1: ruleXPercentChangeExpType returns [EObject current=null] : ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:5277:2: ( ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:5278:2: ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:5278:2: ( () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:5279:3: () otherlv_1= 'percentChange' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:5279:3: ()
            // InternalXScript.g:5280:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXPercentChangeExpTypeAccess().getXPercentChangeExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,69,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXPercentChangeExpTypeAccess().getPercentChangeKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_34); 

            			newLeafNode(otherlv_2, grammarAccess.getXPercentChangeExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5294:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:5295:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:5295:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:5296:5: lv_value1_3_0= ruleXExpressionType
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

            otherlv_4=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_4, grammarAccess.getXPercentChangeExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5317:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:5318:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:5318:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:5319:5: lv_value2_5_0= ruleXExpressionType
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
    // InternalXScript.g:5344:1: entryRuleXSubExpType returns [EObject current=null] : iv_ruleXSubExpType= ruleXSubExpType EOF ;
    public final EObject entryRuleXSubExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSubExpType = null;


        try {
            // InternalXScript.g:5344:52: (iv_ruleXSubExpType= ruleXSubExpType EOF )
            // InternalXScript.g:5345:2: iv_ruleXSubExpType= ruleXSubExpType EOF
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
    // InternalXScript.g:5351:1: ruleXSubExpType returns [EObject current=null] : ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:5357:2: ( ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:5358:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:5358:2: ( () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:5359:3: () otherlv_1= 'sub' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:5359:3: ()
            // InternalXScript.g:5360:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSubExpTypeAccess().getXSubExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,43,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSubExpTypeAccess().getSubKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_34); 

            			newLeafNode(otherlv_2, grammarAccess.getXSubExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5374:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:5375:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:5375:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:5376:5: lv_value1_3_0= ruleXExpressionType
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

            otherlv_4=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_4, grammarAccess.getXSubExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5397:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:5398:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:5398:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:5399:5: lv_value2_5_0= ruleXExpressionType
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
    // InternalXScript.g:5424:1: entryRuleXVarExpType returns [EObject current=null] : iv_ruleXVarExpType= ruleXVarExpType EOF ;
    public final EObject entryRuleXVarExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarExpType = null;


        try {
            // InternalXScript.g:5424:52: (iv_ruleXVarExpType= ruleXVarExpType EOF )
            // InternalXScript.g:5425:2: iv_ruleXVarExpType= ruleXVarExpType EOF
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
    // InternalXScript.g:5431:1: ruleXVarExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleXVarExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;


        	enterRule();

        try {
            // InternalXScript.g:5437:2: ( ( () ( (otherlv_1= RULE_ID ) ) ) )
            // InternalXScript.g:5438:2: ( () ( (otherlv_1= RULE_ID ) ) )
            {
            // InternalXScript.g:5438:2: ( () ( (otherlv_1= RULE_ID ) ) )
            // InternalXScript.g:5439:3: () ( (otherlv_1= RULE_ID ) )
            {
            // InternalXScript.g:5439:3: ()
            // InternalXScript.g:5440:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarExpTypeAccess().getXVarExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5446:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:5447:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:5447:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:5448:5: otherlv_1= RULE_ID
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
    // InternalXScript.g:5463:1: entryRuleXStreamWrapperExpType returns [EObject current=null] : iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF ;
    public final EObject entryRuleXStreamWrapperExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamWrapperExpType = null;


        try {
            // InternalXScript.g:5463:62: (iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF )
            // InternalXScript.g:5464:2: iv_ruleXStreamWrapperExpType= ruleXStreamWrapperExpType EOF
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
    // InternalXScript.g:5470:1: ruleXStreamWrapperExpType returns [EObject current=null] : ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' ) ;
    public final EObject ruleXStreamWrapperExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_wrapperExp_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:5476:2: ( ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' ) )
            // InternalXScript.g:5477:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:5477:2: ( () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')' )
            // InternalXScript.g:5478:3: () otherlv_1= 'exp' otherlv_2= '(' ( (lv_wrapperExp_3_0= ruleExpressionType ) ) otherlv_4= ')'
            {
            // InternalXScript.g:5478:3: ()
            // InternalXScript.g:5479:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamWrapperExpTypeAccess().getXStreamWrapperExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,37,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXStreamWrapperExpTypeAccess().getExpKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getXStreamWrapperExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5493:3: ( (lv_wrapperExp_3_0= ruleExpressionType ) )
            // InternalXScript.g:5494:4: (lv_wrapperExp_3_0= ruleExpressionType )
            {
            // InternalXScript.g:5494:4: (lv_wrapperExp_3_0= ruleExpressionType )
            // InternalXScript.g:5495:5: lv_wrapperExp_3_0= ruleExpressionType
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
    // InternalXScript.g:5520:1: entryRuleXStreamVarValueExpType returns [EObject current=null] : iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF ;
    public final EObject entryRuleXStreamVarValueExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXStreamVarValueExpType = null;


        try {
            // InternalXScript.g:5520:63: (iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF )
            // InternalXScript.g:5521:2: iv_ruleXStreamVarValueExpType= ruleXStreamVarValueExpType EOF
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
    // InternalXScript.g:5527:1: ruleXStreamVarValueExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' ) ;
    public final EObject ruleXStreamVarValueExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_expressionValue_3_0 = null;



        	enterRule();

        try {
            // InternalXScript.g:5533:2: ( ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' ) )
            // InternalXScript.g:5534:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' )
            {
            // InternalXScript.g:5534:2: ( () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']' )
            // InternalXScript.g:5535:3: () ( (otherlv_1= RULE_ID ) ) (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) ) otherlv_4= ']'
            {
            // InternalXScript.g:5535:3: ()
            // InternalXScript.g:5536:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXStreamVarValueExpTypeAccess().getXStreamVarValueExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5542:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:5543:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:5543:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:5544:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXStreamVarValueExpTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_19); 

            					newLeafNode(otherlv_1, grammarAccess.getXStreamVarValueExpTypeAccess().getVarVarTypeCrossReference_1_0());
            				

            }


            }

            // InternalXScript.g:5555:3: (otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) ) )
            // InternalXScript.g:5556:4: otherlv_2= '[' ( (lv_expressionValue_3_0= ruleXExpressionType ) )
            {
            otherlv_2=(Token)match(input,35,FOLLOW_34); 

            				newLeafNode(otherlv_2, grammarAccess.getXStreamVarValueExpTypeAccess().getLeftSquareBracketKeyword_2_0());
            			
            // InternalXScript.g:5560:4: ( (lv_expressionValue_3_0= ruleXExpressionType ) )
            // InternalXScript.g:5561:5: (lv_expressionValue_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:5561:5: (lv_expressionValue_3_0= ruleXExpressionType )
            // InternalXScript.g:5562:6: lv_expressionValue_3_0= ruleXExpressionType
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

            otherlv_4=(Token)match(input,36,FOLLOW_2); 

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
    // InternalXScript.g:5588:1: entryRuleXFunctionCallExpType returns [EObject current=null] : iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF ;
    public final EObject entryRuleXFunctionCallExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXFunctionCallExpType = null;


        try {
            // InternalXScript.g:5588:61: (iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF )
            // InternalXScript.g:5589:2: iv_ruleXFunctionCallExpType= ruleXFunctionCallExpType EOF
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
    // InternalXScript.g:5595:1: ruleXFunctionCallExpType returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' ) ;
    public final EObject ruleXFunctionCallExpType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_parms_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalXScript.g:5601:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' ) )
            // InternalXScript.g:5602:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' )
            {
            // InternalXScript.g:5602:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')' )
            // InternalXScript.g:5603:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( (lv_parms_3_0= RULE_STRING ) )? otherlv_4= ')'
            {
            // InternalXScript.g:5603:3: ()
            // InternalXScript.g:5604:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXFunctionCallExpTypeAccess().getXFunctionCallExpTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5610:3: ( (otherlv_1= RULE_ID ) )
            // InternalXScript.g:5611:4: (otherlv_1= RULE_ID )
            {
            // InternalXScript.g:5611:4: (otherlv_1= RULE_ID )
            // InternalXScript.g:5612:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXFunctionCallExpTypeRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_5); 

            					newLeafNode(otherlv_1, grammarAccess.getXFunctionCallExpTypeAccess().getFunctionXFunctionTypeCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_38); 

            			newLeafNode(otherlv_2, grammarAccess.getXFunctionCallExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5627:3: ( (lv_parms_3_0= RULE_STRING ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==RULE_STRING) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // InternalXScript.g:5628:4: (lv_parms_3_0= RULE_STRING )
                    {
                    // InternalXScript.g:5628:4: (lv_parms_3_0= RULE_STRING )
                    // InternalXScript.g:5629:5: lv_parms_3_0= RULE_STRING
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
    // InternalXScript.g:5653:1: entryRuleXVarStreakType returns [EObject current=null] : iv_ruleXVarStreakType= ruleXVarStreakType EOF ;
    public final EObject entryRuleXVarStreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakType = null;


        try {
            // InternalXScript.g:5653:55: (iv_ruleXVarStreakType= ruleXVarStreakType EOF )
            // InternalXScript.g:5654:2: iv_ruleXVarStreakType= ruleXVarStreakType EOF
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
    // InternalXScript.g:5660:1: ruleXVarStreakType returns [EObject current=null] : ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' ) ;
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
            // InternalXScript.g:5666:2: ( ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' ) )
            // InternalXScript.g:5667:2: ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' )
            {
            // InternalXScript.g:5667:2: ( () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')' )
            // InternalXScript.g:5668:3: () otherlv_1= 'columnStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) ) otherlv_6= ',' ( (lv_startIndexExp_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) ) otherlv_11= ')'
            {
            // InternalXScript.g:5668:3: ()
            // InternalXScript.g:5669:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakTypeAccess().getXVarStreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,70,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarStreakTypeAccess().getColumnStrkKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarStreakTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:5683:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:5684:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:5684:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:5685:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarStreakTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXVarStreakTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_45); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:5700:3: ( ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) ) )
            // InternalXScript.g:5701:4: ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) )
            {
            // InternalXScript.g:5701:4: ( (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' ) )
            // InternalXScript.g:5702:5: (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' )
            {
            // InternalXScript.g:5702:5: (lv_direction_5_1= 'bwd' | lv_direction_5_2= 'fwd' )
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==71) ) {
                alt53=1;
            }
            else if ( (LA53_0==72) ) {
                alt53=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 53, 0, input);

                throw nvae;
            }
            switch (alt53) {
                case 1 :
                    // InternalXScript.g:5703:6: lv_direction_5_1= 'bwd'
                    {
                    lv_direction_5_1=(Token)match(input,71,FOLLOW_7); 

                    						newLeafNode(lv_direction_5_1, grammarAccess.getXVarStreakTypeAccess().getDirectionBwdKeyword_5_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "direction", lv_direction_5_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5714:6: lv_direction_5_2= 'fwd'
                    {
                    lv_direction_5_2=(Token)match(input,72,FOLLOW_7); 

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

            otherlv_6=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:5731:3: ( (lv_startIndexExp_7_0= ruleXExpressionType ) )
            // InternalXScript.g:5732:4: (lv_startIndexExp_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:5732:4: (lv_startIndexExp_7_0= ruleXExpressionType )
            // InternalXScript.g:5733:5: lv_startIndexExp_7_0= ruleXExpressionType
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

            otherlv_8=(Token)match(input,14,FOLLOW_46); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarStreakTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:5754:3: ( ( (lv_compare_9_0= ruleXVarStreakCompareType ) ) | ( (lv_value_10_0= ruleXVarStreakValueType ) ) )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( ((LA54_0>=73 && LA54_0<=74)) ) {
                alt54=1;
            }
            else if ( (LA54_0==75) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // InternalXScript.g:5755:4: ( (lv_compare_9_0= ruleXVarStreakCompareType ) )
                    {
                    // InternalXScript.g:5755:4: ( (lv_compare_9_0= ruleXVarStreakCompareType ) )
                    // InternalXScript.g:5756:5: (lv_compare_9_0= ruleXVarStreakCompareType )
                    {
                    // InternalXScript.g:5756:5: (lv_compare_9_0= ruleXVarStreakCompareType )
                    // InternalXScript.g:5757:6: lv_compare_9_0= ruleXVarStreakCompareType
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
                    // InternalXScript.g:5775:4: ( (lv_value_10_0= ruleXVarStreakValueType ) )
                    {
                    // InternalXScript.g:5775:4: ( (lv_value_10_0= ruleXVarStreakValueType ) )
                    // InternalXScript.g:5776:5: (lv_value_10_0= ruleXVarStreakValueType )
                    {
                    // InternalXScript.g:5776:5: (lv_value_10_0= ruleXVarStreakValueType )
                    // InternalXScript.g:5777:6: lv_value_10_0= ruleXVarStreakValueType
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
    // InternalXScript.g:5803:1: entryRuleXVarStreakCompareType returns [EObject current=null] : iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF ;
    public final EObject entryRuleXVarStreakCompareType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakCompareType = null;


        try {
            // InternalXScript.g:5803:62: (iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF )
            // InternalXScript.g:5804:2: iv_ruleXVarStreakCompareType= ruleXVarStreakCompareType EOF
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
    // InternalXScript.g:5810:1: ruleXVarStreakCompareType returns [EObject current=null] : ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' ) ;
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
            // InternalXScript.g:5816:2: ( ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' ) )
            // InternalXScript.g:5817:2: ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' )
            {
            // InternalXScript.g:5817:2: ( () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')' )
            // InternalXScript.g:5818:3: () ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) ) otherlv_2= '(' ( (lv_offset_3_0= RULE_INT ) ) otherlv_4= ')' ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) ) ( (lv_test_6_0= RULE_DOUBLE ) ) otherlv_7= ')'
            {
            // InternalXScript.g:5818:3: ()
            // InternalXScript.g:5819:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakCompareTypeAccess().getXVarStreakCompareTypeAction_0(),
            					current);
            			

            }

            // InternalXScript.g:5825:3: ( ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) ) )
            // InternalXScript.g:5826:4: ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) )
            {
            // InternalXScript.g:5826:4: ( (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' ) )
            // InternalXScript.g:5827:5: (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' )
            {
            // InternalXScript.g:5827:5: (lv_function_1_1= 'sum' | lv_function_1_2= 'diff' )
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==73) ) {
                alt55=1;
            }
            else if ( (LA55_0==74) ) {
                alt55=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 55, 0, input);

                throw nvae;
            }
            switch (alt55) {
                case 1 :
                    // InternalXScript.g:5828:6: lv_function_1_1= 'sum'
                    {
                    lv_function_1_1=(Token)match(input,73,FOLLOW_5); 

                    						newLeafNode(lv_function_1_1, grammarAccess.getXVarStreakCompareTypeAccess().getFunctionSumKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5839:6: lv_function_1_2= 'diff'
                    {
                    lv_function_1_2=(Token)match(input,74,FOLLOW_5); 

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
            		
            // InternalXScript.g:5856:3: ( (lv_offset_3_0= RULE_INT ) )
            // InternalXScript.g:5857:4: (lv_offset_3_0= RULE_INT )
            {
            // InternalXScript.g:5857:4: (lv_offset_3_0= RULE_INT )
            // InternalXScript.g:5858:5: lv_offset_3_0= RULE_INT
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

            otherlv_4=(Token)match(input,15,FOLLOW_47); 

            			newLeafNode(otherlv_4, grammarAccess.getXVarStreakCompareTypeAccess().getRightParenthesisKeyword_4());
            		
            // InternalXScript.g:5878:3: ( ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) ) )
            // InternalXScript.g:5879:4: ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) )
            {
            // InternalXScript.g:5879:4: ( (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' ) )
            // InternalXScript.g:5880:5: (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' )
            {
            // InternalXScript.g:5880:5: (lv_op_5_1= '<' | lv_op_5_2= '>' | lv_op_5_3= '=' )
            int alt56=3;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt56=1;
                }
                break;
            case 25:
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
                    // InternalXScript.g:5881:6: lv_op_5_1= '<'
                    {
                    lv_op_5_1=(Token)match(input,26,FOLLOW_48); 

                    						newLeafNode(lv_op_5_1, grammarAccess.getXVarStreakCompareTypeAccess().getOpLessThanSignKeyword_5_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_5_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5892:6: lv_op_5_2= '>'
                    {
                    lv_op_5_2=(Token)match(input,25,FOLLOW_48); 

                    						newLeafNode(lv_op_5_2, grammarAccess.getXVarStreakCompareTypeAccess().getOpGreaterThanSignKeyword_5_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakCompareTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_5_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5903:6: lv_op_5_3= '='
                    {
                    lv_op_5_3=(Token)match(input,16,FOLLOW_48); 

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

            // InternalXScript.g:5916:3: ( (lv_test_6_0= RULE_DOUBLE ) )
            // InternalXScript.g:5917:4: (lv_test_6_0= RULE_DOUBLE )
            {
            // InternalXScript.g:5917:4: (lv_test_6_0= RULE_DOUBLE )
            // InternalXScript.g:5918:5: lv_test_6_0= RULE_DOUBLE
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
    // InternalXScript.g:5942:1: entryRuleXVarStreakValueType returns [EObject current=null] : iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF ;
    public final EObject entryRuleXVarStreakValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarStreakValueType = null;


        try {
            // InternalXScript.g:5942:60: (iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF )
            // InternalXScript.g:5943:2: iv_ruleXVarStreakValueType= ruleXVarStreakValueType EOF
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
    // InternalXScript.g:5949:1: ruleXVarStreakValueType returns [EObject current=null] : ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) ) ;
    public final EObject ruleXVarStreakValueType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_op_2_1=null;
        Token lv_op_2_2=null;
        Token lv_op_2_3=null;
        Token lv_test_3_0=null;


        	enterRule();

        try {
            // InternalXScript.g:5955:2: ( ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) ) )
            // InternalXScript.g:5956:2: ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) )
            {
            // InternalXScript.g:5956:2: ( () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) ) )
            // InternalXScript.g:5957:3: () otherlv_1= 'value' ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) ) ( (lv_test_3_0= RULE_DOUBLE ) )
            {
            // InternalXScript.g:5957:3: ()
            // InternalXScript.g:5958:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarStreakValueTypeAccess().getXVarStreakValueTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,75,FOLLOW_47); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarStreakValueTypeAccess().getValueKeyword_1());
            		
            // InternalXScript.g:5968:3: ( ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) ) )
            // InternalXScript.g:5969:4: ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) )
            {
            // InternalXScript.g:5969:4: ( (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' ) )
            // InternalXScript.g:5970:5: (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' )
            {
            // InternalXScript.g:5970:5: (lv_op_2_1= '<' | lv_op_2_2= '>' | lv_op_2_3= '=' )
            int alt57=3;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt57=1;
                }
                break;
            case 25:
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
                    // InternalXScript.g:5971:6: lv_op_2_1= '<'
                    {
                    lv_op_2_1=(Token)match(input,26,FOLLOW_48); 

                    						newLeafNode(lv_op_2_1, grammarAccess.getXVarStreakValueTypeAccess().getOpLessThanSignKeyword_2_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakValueTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_2_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:5982:6: lv_op_2_2= '>'
                    {
                    lv_op_2_2=(Token)match(input,25,FOLLOW_48); 

                    						newLeafNode(lv_op_2_2, grammarAccess.getXVarStreakValueTypeAccess().getOpGreaterThanSignKeyword_2_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarStreakValueTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_2_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:5993:6: lv_op_2_3= '='
                    {
                    lv_op_2_3=(Token)match(input,16,FOLLOW_48); 

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

            // InternalXScript.g:6006:3: ( (lv_test_3_0= RULE_DOUBLE ) )
            // InternalXScript.g:6007:4: (lv_test_3_0= RULE_DOUBLE )
            {
            // InternalXScript.g:6007:4: (lv_test_3_0= RULE_DOUBLE )
            // InternalXScript.g:6008:5: lv_test_3_0= RULE_DOUBLE
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
    // InternalXScript.g:6028:1: entryRuleXVarCompareStreakType returns [EObject current=null] : iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF ;
    public final EObject entryRuleXVarCompareStreakType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarCompareStreakType = null;


        try {
            // InternalXScript.g:6028:62: (iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF )
            // InternalXScript.g:6029:2: iv_ruleXVarCompareStreakType= ruleXVarCompareStreakType EOF
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
    // InternalXScript.g:6035:1: ruleXVarCompareStreakType returns [EObject current=null] : ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' ) ;
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
            // InternalXScript.g:6041:2: ( ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' ) )
            // InternalXScript.g:6042:2: ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' )
            {
            // InternalXScript.g:6042:2: ( () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')' )
            // InternalXScript.g:6043:3: () otherlv_1= 'columnPairStrk' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) ) otherlv_8= ',' ( (lv_startIndexExp_9_0= ruleXExpressionType ) ) otherlv_10= ',' ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) ) ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) ) ( (lv_test_13_0= RULE_DOUBLE ) ) otherlv_14= ')'
            {
            // InternalXScript.g:6043:3: ()
            // InternalXScript.g:6044:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarCompareStreakTypeAccess().getXVarCompareStreakTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,76,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarCompareStreakTypeAccess().getColumnPairStrkKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarCompareStreakTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6058:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6059:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6059:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6060:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:6075:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:6076:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:6076:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:6077:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarCompareStreakTypeAccess().getCompareVarVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_45); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6092:3: ( ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) ) )
            // InternalXScript.g:6093:4: ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) )
            {
            // InternalXScript.g:6093:4: ( (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' ) )
            // InternalXScript.g:6094:5: (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' )
            {
            // InternalXScript.g:6094:5: (lv_direction_7_1= 'bwd' | lv_direction_7_2= 'fwd' )
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==71) ) {
                alt58=1;
            }
            else if ( (LA58_0==72) ) {
                alt58=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 58, 0, input);

                throw nvae;
            }
            switch (alt58) {
                case 1 :
                    // InternalXScript.g:6095:6: lv_direction_7_1= 'bwd'
                    {
                    lv_direction_7_1=(Token)match(input,71,FOLLOW_7); 

                    						newLeafNode(lv_direction_7_1, grammarAccess.getXVarCompareStreakTypeAccess().getDirectionBwdKeyword_7_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "direction", lv_direction_7_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:6106:6: lv_direction_7_2= 'fwd'
                    {
                    lv_direction_7_2=(Token)match(input,72,FOLLOW_7); 

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

            otherlv_8=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:6123:3: ( (lv_startIndexExp_9_0= ruleXExpressionType ) )
            // InternalXScript.g:6124:4: (lv_startIndexExp_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:6124:4: (lv_startIndexExp_9_0= ruleXExpressionType )
            // InternalXScript.g:6125:5: lv_startIndexExp_9_0= ruleXExpressionType
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

            otherlv_10=(Token)match(input,14,FOLLOW_49); 

            			newLeafNode(otherlv_10, grammarAccess.getXVarCompareStreakTypeAccess().getCommaKeyword_10());
            		
            // InternalXScript.g:6146:3: ( ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) ) )
            // InternalXScript.g:6147:4: ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) )
            {
            // InternalXScript.g:6147:4: ( (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' ) )
            // InternalXScript.g:6148:5: (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' )
            {
            // InternalXScript.g:6148:5: (lv_function_11_1= 'sum' | lv_function_11_2= 'diff' | lv_function_11_3= 'value' | lv_function_11_4= 'variance' )
            int alt59=4;
            switch ( input.LA(1) ) {
            case 73:
                {
                alt59=1;
                }
                break;
            case 74:
                {
                alt59=2;
                }
                break;
            case 75:
                {
                alt59=3;
                }
                break;
            case 77:
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
                    // InternalXScript.g:6149:6: lv_function_11_1= 'sum'
                    {
                    lv_function_11_1=(Token)match(input,73,FOLLOW_47); 

                    						newLeafNode(lv_function_11_1, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionSumKeyword_11_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:6160:6: lv_function_11_2= 'diff'
                    {
                    lv_function_11_2=(Token)match(input,74,FOLLOW_47); 

                    						newLeafNode(lv_function_11_2, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionDiffKeyword_11_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:6171:6: lv_function_11_3= 'value'
                    {
                    lv_function_11_3=(Token)match(input,75,FOLLOW_47); 

                    						newLeafNode(lv_function_11_3, grammarAccess.getXVarCompareStreakTypeAccess().getFunctionValueKeyword_11_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "function", lv_function_11_3, null);
                    					

                    }
                    break;
                case 4 :
                    // InternalXScript.g:6182:6: lv_function_11_4= 'variance'
                    {
                    lv_function_11_4=(Token)match(input,77,FOLLOW_47); 

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

            // InternalXScript.g:6195:3: ( ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) ) )
            // InternalXScript.g:6196:4: ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) )
            {
            // InternalXScript.g:6196:4: ( (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' ) )
            // InternalXScript.g:6197:5: (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' )
            {
            // InternalXScript.g:6197:5: (lv_op_12_1= '<' | lv_op_12_2= '>' | lv_op_12_3= '=' )
            int alt60=3;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt60=1;
                }
                break;
            case 25:
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
                    // InternalXScript.g:6198:6: lv_op_12_1= '<'
                    {
                    lv_op_12_1=(Token)match(input,26,FOLLOW_48); 

                    						newLeafNode(lv_op_12_1, grammarAccess.getXVarCompareStreakTypeAccess().getOpLessThanSignKeyword_12_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_12_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalXScript.g:6209:6: lv_op_12_2= '>'
                    {
                    lv_op_12_2=(Token)match(input,25,FOLLOW_48); 

                    						newLeafNode(lv_op_12_2, grammarAccess.getXVarCompareStreakTypeAccess().getOpGreaterThanSignKeyword_12_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getXVarCompareStreakTypeRule());
                    						}
                    						setWithLastConsumed(current, "op", lv_op_12_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalXScript.g:6220:6: lv_op_12_3= '='
                    {
                    lv_op_12_3=(Token)match(input,16,FOLLOW_48); 

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

            // InternalXScript.g:6233:3: ( (lv_test_13_0= RULE_DOUBLE ) )
            // InternalXScript.g:6234:4: (lv_test_13_0= RULE_DOUBLE )
            {
            // InternalXScript.g:6234:4: (lv_test_13_0= RULE_DOUBLE )
            // InternalXScript.g:6235:5: lv_test_13_0= RULE_DOUBLE
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
    // InternalXScript.g:6259:1: entryRuleXSlrAvgExpType returns [EObject current=null] : iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF ;
    public final EObject entryRuleXSlrAvgExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSlrAvgExpType = null;


        try {
            // InternalXScript.g:6259:55: (iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF )
            // InternalXScript.g:6260:2: iv_ruleXSlrAvgExpType= ruleXSlrAvgExpType EOF
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
    // InternalXScript.g:6266:1: ruleXSlrAvgExpType returns [EObject current=null] : ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' ) ;
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
            // InternalXScript.g:6272:2: ( ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' ) )
            // InternalXScript.g:6273:2: ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' )
            {
            // InternalXScript.g:6273:2: ( () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')' )
            // InternalXScript.g:6274:3: () otherlv_1= 'slrAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_startValue_5_0= ruleXExpressionType ) ) otherlv_6= ',' ( (lv_endValue_7_0= ruleXExpressionType ) ) otherlv_8= ')'
            {
            // InternalXScript.g:6274:3: ()
            // InternalXScript.g:6275:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSlrAvgExpTypeAccess().getXSlrAvgExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,78,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSlrAvgExpTypeAccess().getSlrAvgKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSlrAvgExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6289:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6290:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6290:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6291:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXSlrAvgExpTypeRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_3, grammarAccess.getXSlrAvgExpTypeAccess().getVarVarTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_4, grammarAccess.getXSlrAvgExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6306:3: ( (lv_startValue_5_0= ruleXExpressionType ) )
            // InternalXScript.g:6307:4: (lv_startValue_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:6307:4: (lv_startValue_5_0= ruleXExpressionType )
            // InternalXScript.g:6308:5: lv_startValue_5_0= ruleXExpressionType
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

            otherlv_6=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_6, grammarAccess.getXSlrAvgExpTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6329:3: ( (lv_endValue_7_0= ruleXExpressionType ) )
            // InternalXScript.g:6330:4: (lv_endValue_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:6330:4: (lv_endValue_7_0= ruleXExpressionType )
            // InternalXScript.g:6331:5: lv_endValue_7_0= ruleXExpressionType
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
    // InternalXScript.g:6356:1: entryRuleXLastSignalTriggerType returns [EObject current=null] : iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF ;
    public final EObject entryRuleXLastSignalTriggerType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXLastSignalTriggerType = null;


        try {
            // InternalXScript.g:6356:63: (iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF )
            // InternalXScript.g:6357:2: iv_ruleXLastSignalTriggerType= ruleXLastSignalTriggerType EOF
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
    // InternalXScript.g:6363:1: ruleXLastSignalTriggerType returns [EObject current=null] : ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' ) ;
    public final EObject ruleXLastSignalTriggerType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalXScript.g:6369:2: ( ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' ) )
            // InternalXScript.g:6370:2: ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' )
            {
            // InternalXScript.g:6370:2: ( () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' )
            // InternalXScript.g:6371:3: () otherlv_1= 'lst' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')'
            {
            // InternalXScript.g:6371:3: ()
            // InternalXScript.g:6372:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXLastSignalTriggerTypeAccess().getXLastSignalTriggerTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,79,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXLastSignalTriggerTypeAccess().getLstKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXLastSignalTriggerTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6386:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6387:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6387:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6388:5: otherlv_3= RULE_ID
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
    // InternalXScript.g:6407:1: entryRuleXSignalTriggerCountType returns [EObject current=null] : iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF ;
    public final EObject entryRuleXSignalTriggerCountType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXSignalTriggerCountType = null;


        try {
            // InternalXScript.g:6407:64: (iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF )
            // InternalXScript.g:6408:2: iv_ruleXSignalTriggerCountType= ruleXSignalTriggerCountType EOF
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
    // InternalXScript.g:6414:1: ruleXSignalTriggerCountType returns [EObject current=null] : ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:6420:2: ( ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' ) )
            // InternalXScript.g:6421:2: ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:6421:2: ( () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')' )
            // InternalXScript.g:6422:3: () otherlv_1= 'stc' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (lv_lookback_5_0= RULE_INT ) ) otherlv_6= ')'
            {
            // InternalXScript.g:6422:3: ()
            // InternalXScript.g:6423:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXSignalTriggerCountTypeAccess().getXSignalTriggerCountTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,80,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXSignalTriggerCountTypeAccess().getStcKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXSignalTriggerCountTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6437:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6438:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6438:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6439:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:6454:3: ( (lv_lookback_5_0= RULE_INT ) )
            // InternalXScript.g:6455:4: (lv_lookback_5_0= RULE_INT )
            {
            // InternalXScript.g:6455:4: (lv_lookback_5_0= RULE_INT )
            // InternalXScript.g:6456:5: lv_lookback_5_0= RULE_INT
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
    // InternalXScript.g:6480:1: entryRuleXVarianceAverageType returns [EObject current=null] : iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF ;
    public final EObject entryRuleXVarianceAverageType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarianceAverageType = null;


        try {
            // InternalXScript.g:6480:61: (iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF )
            // InternalXScript.g:6481:2: iv_ruleXVarianceAverageType= ruleXVarianceAverageType EOF
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
    // InternalXScript.g:6487:1: ruleXVarianceAverageType returns [EObject current=null] : ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) ;
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
            // InternalXScript.g:6493:2: ( ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) )
            // InternalXScript.g:6494:2: ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            {
            // InternalXScript.g:6494:2: ( () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            // InternalXScript.g:6495:3: () otherlv_1= 'varAvg' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')'
            {
            // InternalXScript.g:6495:3: ()
            // InternalXScript.g:6496:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarianceAverageTypeAccess().getXVarianceAverageTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,81,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarianceAverageTypeAccess().getVarAvgKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarianceAverageTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6510:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6511:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6511:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6512:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:6527:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:6528:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:6528:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:6529:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarianceAverageTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarianceAverageTypeAccess().getCompareVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarianceAverageTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6544:3: ( (lv_start_7_0= ruleXExpressionType ) )
            // InternalXScript.g:6545:4: (lv_start_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:6545:4: (lv_start_7_0= ruleXExpressionType )
            // InternalXScript.g:6546:5: lv_start_7_0= ruleXExpressionType
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

            otherlv_8=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarianceAverageTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:6567:3: ( (lv_end_9_0= ruleXExpressionType ) )
            // InternalXScript.g:6568:4: (lv_end_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:6568:4: (lv_end_9_0= ruleXExpressionType )
            // InternalXScript.g:6569:5: lv_end_9_0= ruleXExpressionType
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
    // InternalXScript.g:6594:1: entryRuleXVarianceMaxType returns [EObject current=null] : iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF ;
    public final EObject entryRuleXVarianceMaxType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXVarianceMaxType = null;


        try {
            // InternalXScript.g:6594:57: (iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF )
            // InternalXScript.g:6595:2: iv_ruleXVarianceMaxType= ruleXVarianceMaxType EOF
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
    // InternalXScript.g:6601:1: ruleXVarianceMaxType returns [EObject current=null] : ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) ;
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
            // InternalXScript.g:6607:2: ( ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' ) )
            // InternalXScript.g:6608:2: ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            {
            // InternalXScript.g:6608:2: ( () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')' )
            // InternalXScript.g:6609:3: () otherlv_1= 'varMax' otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ',' ( (otherlv_5= RULE_ID ) ) otherlv_6= ',' ( (lv_start_7_0= ruleXExpressionType ) ) otherlv_8= ',' ( (lv_end_9_0= ruleXExpressionType ) ) otherlv_10= ')'
            {
            // InternalXScript.g:6609:3: ()
            // InternalXScript.g:6610:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXVarianceMaxTypeAccess().getXVarianceMaxTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,82,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXVarianceMaxTypeAccess().getVarMaxKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_4); 

            			newLeafNode(otherlv_2, grammarAccess.getXVarianceMaxTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6624:3: ( (otherlv_3= RULE_ID ) )
            // InternalXScript.g:6625:4: (otherlv_3= RULE_ID )
            {
            // InternalXScript.g:6625:4: (otherlv_3= RULE_ID )
            // InternalXScript.g:6626:5: otherlv_3= RULE_ID
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
            		
            // InternalXScript.g:6641:3: ( (otherlv_5= RULE_ID ) )
            // InternalXScript.g:6642:4: (otherlv_5= RULE_ID )
            {
            // InternalXScript.g:6642:4: (otherlv_5= RULE_ID )
            // InternalXScript.g:6643:5: otherlv_5= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getXVarianceMaxTypeRule());
            					}
            				
            otherlv_5=(Token)match(input,RULE_ID,FOLLOW_7); 

            					newLeafNode(otherlv_5, grammarAccess.getXVarianceMaxTypeAccess().getCompareVarTypeCrossReference_5_0());
            				

            }


            }

            otherlv_6=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_6, grammarAccess.getXVarianceMaxTypeAccess().getCommaKeyword_6());
            		
            // InternalXScript.g:6658:3: ( (lv_start_7_0= ruleXExpressionType ) )
            // InternalXScript.g:6659:4: (lv_start_7_0= ruleXExpressionType )
            {
            // InternalXScript.g:6659:4: (lv_start_7_0= ruleXExpressionType )
            // InternalXScript.g:6660:5: lv_start_7_0= ruleXExpressionType
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

            otherlv_8=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_8, grammarAccess.getXVarianceMaxTypeAccess().getCommaKeyword_8());
            		
            // InternalXScript.g:6681:3: ( (lv_end_9_0= ruleXExpressionType ) )
            // InternalXScript.g:6682:4: (lv_end_9_0= ruleXExpressionType )
            {
            // InternalXScript.g:6682:4: (lv_end_9_0= ruleXExpressionType )
            // InternalXScript.g:6683:5: lv_end_9_0= ruleXExpressionType
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
    // InternalXScript.g:6708:1: entryRuleXRocExpType returns [EObject current=null] : iv_ruleXRocExpType= ruleXRocExpType EOF ;
    public final EObject entryRuleXRocExpType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleXRocExpType = null;


        try {
            // InternalXScript.g:6708:52: (iv_ruleXRocExpType= ruleXRocExpType EOF )
            // InternalXScript.g:6709:2: iv_ruleXRocExpType= ruleXRocExpType EOF
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
    // InternalXScript.g:6715:1: ruleXRocExpType returns [EObject current=null] : ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) ;
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
            // InternalXScript.g:6721:2: ( ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' ) )
            // InternalXScript.g:6722:2: ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            {
            // InternalXScript.g:6722:2: ( () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')' )
            // InternalXScript.g:6723:3: () otherlv_1= 'rox' otherlv_2= '(' ( (lv_value1_3_0= ruleXExpressionType ) ) otherlv_4= ',' ( (lv_value2_5_0= ruleXExpressionType ) ) otherlv_6= ')'
            {
            // InternalXScript.g:6723:3: ()
            // InternalXScript.g:6724:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getXRocExpTypeAccess().getXRocExpTypeAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,83,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getXRocExpTypeAccess().getRoxKeyword_1());
            		
            otherlv_2=(Token)match(input,13,FOLLOW_34); 

            			newLeafNode(otherlv_2, grammarAccess.getXRocExpTypeAccess().getLeftParenthesisKeyword_2());
            		
            // InternalXScript.g:6738:3: ( (lv_value1_3_0= ruleXExpressionType ) )
            // InternalXScript.g:6739:4: (lv_value1_3_0= ruleXExpressionType )
            {
            // InternalXScript.g:6739:4: (lv_value1_3_0= ruleXExpressionType )
            // InternalXScript.g:6740:5: lv_value1_3_0= ruleXExpressionType
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

            otherlv_4=(Token)match(input,14,FOLLOW_34); 

            			newLeafNode(otherlv_4, grammarAccess.getXRocExpTypeAccess().getCommaKeyword_4());
            		
            // InternalXScript.g:6761:3: ( (lv_value2_5_0= ruleXExpressionType ) )
            // InternalXScript.g:6762:4: (lv_value2_5_0= ruleXExpressionType )
            {
            // InternalXScript.g:6762:4: (lv_value2_5_0= ruleXExpressionType )
            // InternalXScript.g:6763:5: lv_value2_5_0= ruleXExpressionType
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


    // $ANTLR start "ruleHistoricalAggFunc"
    // InternalXScript.g:6788:1: ruleHistoricalAggFunc returns [Enumerator current=null] : ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) ) ;
    public final Enumerator ruleHistoricalAggFunc() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalXScript.g:6794:2: ( ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) ) )
            // InternalXScript.g:6795:2: ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) )
            {
            // InternalXScript.g:6795:2: ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) )
            int alt61=2;
            int LA61_0 = input.LA(1);

            if ( (LA61_0==84) ) {
                alt61=1;
            }
            else if ( (LA61_0==85) ) {
                alt61=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 61, 0, input);

                throw nvae;
            }
            switch (alt61) {
                case 1 :
                    // InternalXScript.g:6796:3: (enumLiteral_0= 'HIGH' )
                    {
                    // InternalXScript.g:6796:3: (enumLiteral_0= 'HIGH' )
                    // InternalXScript.g:6797:4: enumLiteral_0= 'HIGH'
                    {
                    enumLiteral_0=(Token)match(input,84,FOLLOW_2); 

                    				current = grammarAccess.getHistoricalAggFuncAccess().getHighEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getHistoricalAggFuncAccess().getHighEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:6804:3: (enumLiteral_1= 'LOW' )
                    {
                    // InternalXScript.g:6804:3: (enumLiteral_1= 'LOW' )
                    // InternalXScript.g:6805:4: enumLiteral_1= 'LOW'
                    {
                    enumLiteral_1=(Token)match(input,85,FOLLOW_2); 

                    				current = grammarAccess.getHistoricalAggFuncAccess().getLowEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getHistoricalAggFuncAccess().getLowEnumLiteralDeclaration_1());
                    			

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
    // $ANTLR end "ruleHistoricalAggFunc"


    // $ANTLR start "ruleSessionAggFunc"
    // InternalXScript.g:6815:1: ruleSessionAggFunc returns [Enumerator current=null] : ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) ) ;
    public final Enumerator ruleSessionAggFunc() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalXScript.g:6821:2: ( ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) ) )
            // InternalXScript.g:6822:2: ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) )
            {
            // InternalXScript.g:6822:2: ( (enumLiteral_0= 'HIGH' ) | (enumLiteral_1= 'LOW' ) )
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==84) ) {
                alt62=1;
            }
            else if ( (LA62_0==85) ) {
                alt62=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 62, 0, input);

                throw nvae;
            }
            switch (alt62) {
                case 1 :
                    // InternalXScript.g:6823:3: (enumLiteral_0= 'HIGH' )
                    {
                    // InternalXScript.g:6823:3: (enumLiteral_0= 'HIGH' )
                    // InternalXScript.g:6824:4: enumLiteral_0= 'HIGH'
                    {
                    enumLiteral_0=(Token)match(input,84,FOLLOW_2); 

                    				current = grammarAccess.getSessionAggFuncAccess().getHighEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSessionAggFuncAccess().getHighEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:6831:3: (enumLiteral_1= 'LOW' )
                    {
                    // InternalXScript.g:6831:3: (enumLiteral_1= 'LOW' )
                    // InternalXScript.g:6832:4: enumLiteral_1= 'LOW'
                    {
                    enumLiteral_1=(Token)match(input,85,FOLLOW_2); 

                    				current = grammarAccess.getSessionAggFuncAccess().getLowEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSessionAggFuncAccess().getLowEnumLiteralDeclaration_1());
                    			

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
    // $ANTLR end "ruleSessionAggFunc"


    // $ANTLR start "ruleSessionTimeUnit"
    // InternalXScript.g:6842:1: ruleSessionTimeUnit returns [Enumerator current=null] : ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) ) ;
    public final Enumerator ruleSessionTimeUnit() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalXScript.g:6848:2: ( ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) ) )
            // InternalXScript.g:6849:2: ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) )
            {
            // InternalXScript.g:6849:2: ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) )
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==86) ) {
                alt63=1;
            }
            else if ( (LA63_0==87) ) {
                alt63=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 63, 0, input);

                throw nvae;
            }
            switch (alt63) {
                case 1 :
                    // InternalXScript.g:6850:3: (enumLiteral_0= 'SEC' )
                    {
                    // InternalXScript.g:6850:3: (enumLiteral_0= 'SEC' )
                    // InternalXScript.g:6851:4: enumLiteral_0= 'SEC'
                    {
                    enumLiteral_0=(Token)match(input,86,FOLLOW_2); 

                    				current = grammarAccess.getSessionTimeUnitAccess().getSecEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getSessionTimeUnitAccess().getSecEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:6858:3: (enumLiteral_1= 'MIN' )
                    {
                    // InternalXScript.g:6858:3: (enumLiteral_1= 'MIN' )
                    // InternalXScript.g:6859:4: enumLiteral_1= 'MIN'
                    {
                    enumLiteral_1=(Token)match(input,87,FOLLOW_2); 

                    				current = grammarAccess.getSessionTimeUnitAccess().getMinEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getSessionTimeUnitAccess().getMinEnumLiteralDeclaration_1());
                    			

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
    // $ANTLR end "ruleSessionTimeUnit"


    // $ANTLR start "ruleStreamTimeUnit"
    // InternalXScript.g:6869:1: ruleStreamTimeUnit returns [Enumerator current=null] : ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) ) ;
    public final Enumerator ruleStreamTimeUnit() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;


        	enterRule();

        try {
            // InternalXScript.g:6875:2: ( ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) ) )
            // InternalXScript.g:6876:2: ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) )
            {
            // InternalXScript.g:6876:2: ( (enumLiteral_0= 'SEC' ) | (enumLiteral_1= 'MIN' ) | (enumLiteral_2= 'HOUR' ) )
            int alt64=3;
            switch ( input.LA(1) ) {
            case 86:
                {
                alt64=1;
                }
                break;
            case 87:
                {
                alt64=2;
                }
                break;
            case 88:
                {
                alt64=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 64, 0, input);

                throw nvae;
            }

            switch (alt64) {
                case 1 :
                    // InternalXScript.g:6877:3: (enumLiteral_0= 'SEC' )
                    {
                    // InternalXScript.g:6877:3: (enumLiteral_0= 'SEC' )
                    // InternalXScript.g:6878:4: enumLiteral_0= 'SEC'
                    {
                    enumLiteral_0=(Token)match(input,86,FOLLOW_2); 

                    				current = grammarAccess.getStreamTimeUnitAccess().getSecondEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getStreamTimeUnitAccess().getSecondEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:6885:3: (enumLiteral_1= 'MIN' )
                    {
                    // InternalXScript.g:6885:3: (enumLiteral_1= 'MIN' )
                    // InternalXScript.g:6886:4: enumLiteral_1= 'MIN'
                    {
                    enumLiteral_1=(Token)match(input,87,FOLLOW_2); 

                    				current = grammarAccess.getStreamTimeUnitAccess().getMinuteEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getStreamTimeUnitAccess().getMinuteEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:6893:3: (enumLiteral_2= 'HOUR' )
                    {
                    // InternalXScript.g:6893:3: (enumLiteral_2= 'HOUR' )
                    // InternalXScript.g:6894:4: enumLiteral_2= 'HOUR'
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
    // InternalXScript.g:6904:1: ruleDataType returns [Enumerator current=null] : ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) ) ;
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
            // InternalXScript.g:6910:2: ( ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) ) )
            // InternalXScript.g:6911:2: ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) )
            {
            // InternalXScript.g:6911:2: ( (enumLiteral_0= 'STR' ) | (enumLiteral_1= 'INT' ) | (enumLiteral_2= 'BOOl' ) | (enumLiteral_3= 'T' ) | (enumLiteral_4= 'DT' ) | (enumLiteral_5= 'DATE' ) | (enumLiteral_6= 'DUB' ) | (enumLiteral_7= 'LONG' ) )
            int alt65=8;
            switch ( input.LA(1) ) {
            case 89:
                {
                alt65=1;
                }
                break;
            case 90:
                {
                alt65=2;
                }
                break;
            case 91:
                {
                alt65=3;
                }
                break;
            case 92:
                {
                alt65=4;
                }
                break;
            case 93:
                {
                alt65=5;
                }
                break;
            case 94:
                {
                alt65=6;
                }
                break;
            case 95:
                {
                alt65=7;
                }
                break;
            case 96:
                {
                alt65=8;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 65, 0, input);

                throw nvae;
            }

            switch (alt65) {
                case 1 :
                    // InternalXScript.g:6912:3: (enumLiteral_0= 'STR' )
                    {
                    // InternalXScript.g:6912:3: (enumLiteral_0= 'STR' )
                    // InternalXScript.g:6913:4: enumLiteral_0= 'STR'
                    {
                    enumLiteral_0=(Token)match(input,89,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getSTREnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getDataTypeAccess().getSTREnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalXScript.g:6920:3: (enumLiteral_1= 'INT' )
                    {
                    // InternalXScript.g:6920:3: (enumLiteral_1= 'INT' )
                    // InternalXScript.g:6921:4: enumLiteral_1= 'INT'
                    {
                    enumLiteral_1=(Token)match(input,90,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getINTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getDataTypeAccess().getINTEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalXScript.g:6928:3: (enumLiteral_2= 'BOOl' )
                    {
                    // InternalXScript.g:6928:3: (enumLiteral_2= 'BOOl' )
                    // InternalXScript.g:6929:4: enumLiteral_2= 'BOOl'
                    {
                    enumLiteral_2=(Token)match(input,91,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getBOOlEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getDataTypeAccess().getBOOlEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalXScript.g:6936:3: (enumLiteral_3= 'T' )
                    {
                    // InternalXScript.g:6936:3: (enumLiteral_3= 'T' )
                    // InternalXScript.g:6937:4: enumLiteral_3= 'T'
                    {
                    enumLiteral_3=(Token)match(input,92,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getTEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getDataTypeAccess().getTEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalXScript.g:6944:3: (enumLiteral_4= 'DT' )
                    {
                    // InternalXScript.g:6944:3: (enumLiteral_4= 'DT' )
                    // InternalXScript.g:6945:4: enumLiteral_4= 'DT'
                    {
                    enumLiteral_4=(Token)match(input,93,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDTEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getDataTypeAccess().getDTEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalXScript.g:6952:3: (enumLiteral_5= 'DATE' )
                    {
                    // InternalXScript.g:6952:3: (enumLiteral_5= 'DATE' )
                    // InternalXScript.g:6953:4: enumLiteral_5= 'DATE'
                    {
                    enumLiteral_5=(Token)match(input,94,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDATEEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getDataTypeAccess().getDATEEnumLiteralDeclaration_5());
                    			

                    }


                    }
                    break;
                case 7 :
                    // InternalXScript.g:6960:3: (enumLiteral_6= 'DUB' )
                    {
                    // InternalXScript.g:6960:3: (enumLiteral_6= 'DUB' )
                    // InternalXScript.g:6961:4: enumLiteral_6= 'DUB'
                    {
                    enumLiteral_6=(Token)match(input,95,FOLLOW_2); 

                    				current = grammarAccess.getDataTypeAccess().getDUBEnumLiteralDeclaration_6().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_6, grammarAccess.getDataTypeAccess().getDUBEnumLiteralDeclaration_6());
                    			

                    }


                    }
                    break;
                case 8 :
                    // InternalXScript.g:6968:3: (enumLiteral_7= 'LONG' )
                    {
                    // InternalXScript.g:6968:3: (enumLiteral_7= 'LONG' )
                    // InternalXScript.g:6969:4: enumLiteral_7= 'LONG'
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

    // Delegated rules


    protected DFA16 dfa16 = new DFA16(this);
    protected DFA27 dfa27 = new DFA27(this);
    protected DFA51 dfa51 = new DFA51(this);
    static final String dfa_1s = "\25\uffff";
    static final String dfa_2s = "\1\4\12\uffff\1\43\5\uffff\1\5\1\16\2\uffff";
    static final String dfa_3s = "\1\62\12\uffff\1\43\5\uffff\1\45\1\44\2\uffff";
    static final String dfa_4s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\uffff\1\15\1\16\1\17\1\20\1\21\2\uffff\1\14\1\13";
    static final String dfa_5s = "\25\uffff}>";
    static final String[] dfa_6s = {
            "\1\13\1\2\1\1\1\3\30\uffff\2\4\1\5\2\uffff\1\12\1\7\1\uffff\1\6\1\10\1\11\1\14\3\uffff\1\16\1\15\1\17\1\20",
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
            "\1\21",
            "",
            "",
            "",
            "",
            "",
            "\1\22\37\uffff\1\23",
            "\1\24\25\uffff\1\23",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "1047:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_TickExpressionType_8= ruleTickExpressionType | this_SnapshotExpressionType_9= ruleSnapshotExpressionType | this_SetExpressionType_10= ruleSetExpressionType | this_RocExpressionType_11= ruleRocExpressionType | this_AvgExpressionType_12= ruleAvgExpressionType | this_VariableValueExpType_13= ruleVariableValueExpType | this_VariableValueRangeType_14= ruleVariableValueRangeType | this_VariableValueType_15= ruleVariableValueType | this_SubExpressionType_16= ruleSubExpressionType | this_VarAggHistoryType_17= ruleVarAggHistoryType | this_VarAggSessionType_18= ruleVarAggSessionType | this_SignalCountSession_19= ruleSignalCountSession | this_SignalCountHistory_20= ruleSignalCountHistory )";
        }
    }
    static final String dfa_7s = "\21\uffff";
    static final String dfa_8s = "\1\4\6\uffff\1\15\11\uffff";
    static final String dfa_9s = "\1\104\6\uffff\1\74\11\uffff";
    static final String dfa_10s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\1\13\1\15\1\16\1\17\1\10\1\7\1\14\1\12";
    static final String dfa_11s = "\21\uffff}>";
    static final String[] dfa_12s = {
            "\1\7\56\uffff\1\10\1\uffff\1\2\1\uffff\1\1\1\4\1\5\1\6\2\uffff\1\11\1\12\1\3\2\uffff\1\14\1\uffff\1\13",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\16\2\uffff\1\15\52\uffff\1\17\1\20",
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
            return "3027:2: (this_XFunctionReturnType_0= ruleXFunctionReturnType | this_XVarType_1= ruleXVarType | this_XIfStatementType_2= ruleXIfStatementType | this_XSignalListenerType_3= ruleXSignalListenerType | this_XStreamVarListenerType_4= ruleXStreamVarListenerType | this_XFunctionStartType_5= ruleXFunctionStartType | this_XFunctionCallType_6= ruleXFunctionCallType | this_XVarSetterType_7= ruleXVarSetterType | this_XSignalTriggerType_8= ruleXSignalTriggerType | this_XVarDecrementType_9= ruleXVarDecrementType | this_XSetVarType_10= ruleXSetVarType | this_XVarIncrementType_11= ruleXVarIncrementType | this_XDebugType_12= ruleXDebugType | this_XSleepType_13= ruleXSleepType | this_XWhileType_14= ruleXWhileType )";
        }
    }
    static final String dfa_13s = "\24\uffff";
    static final String dfa_14s = "\7\uffff\1\23\14\uffff";
    static final String dfa_15s = "\1\4\6\uffff\1\15\14\uffff";
    static final String dfa_16s = "\1\123\6\uffff\1\44\14\uffff";
    static final String dfa_17s = "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\10\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\11\1\12\1\7";
    static final String dfa_18s = "\24\uffff}>";
    static final String[] dfa_19s = {
            "\1\7\1\2\1\1\1\3\30\uffff\2\4\3\uffff\1\10\5\uffff\1\6\31\uffff\1\5\1\11\5\uffff\1\12\1\uffff\1\13\1\14\1\15\1\16\1\20\1\17",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\22\2\23\1\uffff\1\23\1\uffff\14\23\4\uffff\1\21\1\23",
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
            return "5013:2: ( ( () ( (lv_value_1_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_3_0= RULE_INT ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( ( (lv_value_7_1= 'true' | lv_value_7_2= 'false' ) ) ) ) | this_XPercentChangeExpType_8= ruleXPercentChangeExpType | this_XSubExpType_9= ruleXSubExpType | this_XVarExpType_10= ruleXVarExpType | this_XStreamWrapperExpType_11= ruleXStreamWrapperExpType | this_XStreamVarValueExpType_12= ruleXStreamVarValueExpType | this_XFunctionCallExpType_13= ruleXFunctionCallExpType | this_XVarStreakType_14= ruleXVarStreakType | this_XVarCompareStreakType_15= ruleXVarCompareStreakType | this_XSlrAvgExpType_16= ruleXSlrAvgExpType | this_XLastSignalTriggerType_17= ruleXLastSignalTriggerType | this_XSignalTriggerCountType_18= ruleXSignalTriggerCountType | this_XVarianceAverageType_19= ruleXVarianceAverageType | this_XRocExpType_20= ruleXRocExpType | this_XVarianceMaxType_21= ruleXVarianceMaxType )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0018000000041002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000000L,0x00000001FE000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00078F67800020F0L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000600002L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000007800002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000018000002L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000060000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000002000000020L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x00078FE7800020F0L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000008000004000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000000L,0x0000000001C00000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000000L,0x0000000000C00000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000000L,0x0000000000300000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000600000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000004000002000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0060008000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x00000823800020F0L,0x00000000000FD060L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0xE7E8008000000010L,0x0000000000000014L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x00000823800220F0L,0x00000000000FD060L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000000008080L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x000008238000A0F0L,0x00000000000FD060L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x000000000000C000L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000003L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0xE7E8008000000010L,0x000000000000001CL});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000180L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000E00L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000000006010000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002E00L});

}