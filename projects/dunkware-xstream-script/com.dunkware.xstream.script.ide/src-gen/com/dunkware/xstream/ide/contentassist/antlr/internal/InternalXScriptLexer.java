package com.dunkware.xstream.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalXScriptLexer extends Lexer {
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

    public InternalXScriptLexer() {;} 
    public InternalXScriptLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalXScriptLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalXScript.g"; }

    // $ANTLR start "T__12"
    public final void mT__12() throws RecognitionException {
        try {
            int _type = T__12;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:11:7: ( '==' )
            // InternalXScript.g:11:9: '=='
            {
            match("=="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__12"

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:12:7: ( '!=' )
            // InternalXScript.g:12:9: '!='
            {
            match("!="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:13:7: ( '>=' )
            // InternalXScript.g:13:9: '>='
            {
            match(">="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:14:7: ( '<=' )
            // InternalXScript.g:14:9: '<='
            {
            match("<="); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:15:7: ( '>' )
            // InternalXScript.g:15:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:16:7: ( '<' )
            // InternalXScript.g:16:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17:7: ( '*' )
            // InternalXScript.g:17:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:18:7: ( '/' )
            // InternalXScript.g:18:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:19:7: ( 'true' )
            // InternalXScript.g:19:9: 'true'
            {
            match("true"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:20:7: ( 'false' )
            // InternalXScript.g:20:9: 'false'
            {
            match("false"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:21:7: ( '=' )
            // InternalXScript.g:21:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:22:7: ( 'bwd' )
            // InternalXScript.g:22:9: 'bwd'
            {
            match("bwd"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:23:7: ( 'fwd' )
            // InternalXScript.g:23:9: 'fwd'
            {
            match("fwd"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:24:7: ( 'sum' )
            // InternalXScript.g:24:9: 'sum'
            {
            match("sum"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:25:7: ( 'diff' )
            // InternalXScript.g:25:9: 'diff'
            {
            match("diff"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:26:7: ( 'value' )
            // InternalXScript.g:26:9: 'value'
            {
            match("value"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:27:7: ( 'variance' )
            // InternalXScript.g:27:9: 'variance'
            {
            match("variance"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:28:7: ( 'SEC' )
            // InternalXScript.g:28:9: 'SEC'
            {
            match("SEC"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:29:7: ( 'MIN' )
            // InternalXScript.g:29:9: 'MIN'
            {
            match("MIN"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:30:7: ( 'HOUR' )
            // InternalXScript.g:30:9: 'HOUR'
            {
            match("HOUR"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:31:7: ( 'STR' )
            // InternalXScript.g:31:9: 'STR'
            {
            match("STR"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:32:7: ( 'INT' )
            // InternalXScript.g:32:9: 'INT'
            {
            match("INT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:33:7: ( 'BOOl' )
            // InternalXScript.g:33:9: 'BOOl'
            {
            match("BOOl"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:34:7: ( 'T' )
            // InternalXScript.g:34:9: 'T'
            {
            match('T'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:35:7: ( 'DT' )
            // InternalXScript.g:35:9: 'DT'
            {
            match("DT"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:36:7: ( 'DATE' )
            // InternalXScript.g:36:9: 'DATE'
            {
            match("DATE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:37:7: ( 'DUB' )
            // InternalXScript.g:37:9: 'DUB'
            {
            match("DUB"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:38:7: ( 'LONG' )
            // InternalXScript.g:38:9: 'LONG'
            {
            match("LONG"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:39:7: ( 'svar' )
            // InternalXScript.g:39:9: 'svar'
            {
            match("svar"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:40:7: ( '(' )
            // InternalXScript.g:40:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:41:7: ( ',' )
            // InternalXScript.g:41:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:42:7: ( ')' )
            // InternalXScript.g:42:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:43:7: ( ';' )
            // InternalXScript.g:43:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:44:7: ( '||' )
            // InternalXScript.g:44:9: '||'
            {
            match("||"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:45:7: ( '&&' )
            // InternalXScript.g:45:9: '&&'
            {
            match("&&"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:46:7: ( '+' )
            // InternalXScript.g:46:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:47:7: ( '-' )
            // InternalXScript.g:47:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:48:7: ( '!' )
            // InternalXScript.g:48:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:49:7: ( 'tick' )
            // InternalXScript.g:49:9: 'tick'
            {
            match("tick"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:50:7: ( '[' )
            // InternalXScript.g:50:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:51:7: ( ']' )
            // InternalXScript.g:51:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:52:7: ( 'exp' )
            // InternalXScript.g:52:9: 'exp'
            {
            match("exp"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:53:7: ( '{' )
            // InternalXScript.g:53:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:54:7: ( '}' )
            // InternalXScript.g:54:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:55:7: ( 'snapshot' )
            // InternalXScript.g:55:9: 'snapshot'
            {
            match("snapshot"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:56:7: ( 'roc' )
            // InternalXScript.g:56:9: 'roc'
            {
            match("roc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:57:7: ( 'avg' )
            // InternalXScript.g:57:9: 'avg'
            {
            match("avg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:58:7: ( 'sub' )
            // InternalXScript.g:58:9: 'sub'
            {
            match("sub"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:59:7: ( 'ssc' )
            // InternalXScript.g:59:9: 'ssc'
            {
            match("ssc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:60:7: ( 'in last' )
            // InternalXScript.g:60:9: 'in last'
            {
            match("in last"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:61:7: ( 'EntityQuery' )
            // InternalXScript.g:61:9: 'EntityQuery'
            {
            match("EntityQuery"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:62:7: ( 'LIMIT ' )
            // InternalXScript.g:62:9: 'LIMIT '
            {
            match("LIMIT "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:63:7: ( 'signal' )
            // InternalXScript.g:63:9: 'signal'
            {
            match("signal"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:64:7: ( 'class' )
            // InternalXScript.g:64:9: 'class'
            {
            match("class"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:65:7: ( 'var' )
            // InternalXScript.g:65:9: 'var'
            {
            match("var"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:66:7: ( 'function' )
            // InternalXScript.g:66:9: 'function'
            {
            match("function"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:67:7: ( 'return' )
            // InternalXScript.g:67:9: 'return'
            {
            match("return"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:68:7: ( 'signalListener' )
            // InternalXScript.g:68:9: 'signalListener'
            {
            match("signalListener"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:69:7: ( 'streamVarListener' )
            // InternalXScript.g:69:9: 'streamVarListener'
            {
            match("streamVarListener"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:70:7: ( 'functionRunner' )
            // InternalXScript.g:70:9: 'functionRunner'
            {
            match("functionRunner"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:71:7: ( '++' )
            // InternalXScript.g:71:9: '++'
            {
            match("++"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:72:7: ( '--' )
            // InternalXScript.g:72:9: '--'
            {
            match("--"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:73:7: ( 'setStreamVar' )
            // InternalXScript.g:73:9: 'setStreamVar'
            {
            match("setStreamVar"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:74:7: ( 'debug' )
            // InternalXScript.g:74:9: 'debug'
            {
            match("debug"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:75:7: ( 'if' )
            // InternalXScript.g:75:9: 'if'
            {
            match("if"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:76:7: ( 'elseif' )
            // InternalXScript.g:76:9: 'elseif'
            {
            match("elseif"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:77:7: ( 'else' )
            // InternalXScript.g:77:9: 'else'
            {
            match("else"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:78:7: ( 'whilst' )
            // InternalXScript.g:78:9: 'whilst'
            {
            match("whilst"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:79:7: ( 'break' )
            // InternalXScript.g:79:9: 'break'
            {
            match("break"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:80:7: ( 'sleep' )
            // InternalXScript.g:80:9: 'sleep'
            {
            match("sleep"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:81:7: ( 'percentChange' )
            // InternalXScript.g:81:9: 'percentChange'
            {
            match("percentChange"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:82:7: ( 'columnStrk' )
            // InternalXScript.g:82:9: 'columnStrk'
            {
            match("columnStrk"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:83:7: ( 'columnPairStrk' )
            // InternalXScript.g:83:9: 'columnPairStrk'
            {
            match("columnPairStrk"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:84:7: ( 'slrAvg' )
            // InternalXScript.g:84:9: 'slrAvg'
            {
            match("slrAvg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:85:7: ( 'lst' )
            // InternalXScript.g:85:9: 'lst'
            {
            match("lst"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:86:7: ( 'stc' )
            // InternalXScript.g:86:9: 'stc'
            {
            match("stc"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:87:7: ( 'varAvg' )
            // InternalXScript.g:87:9: 'varAvg'
            {
            match("varAvg"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:88:7: ( 'varMax' )
            // InternalXScript.g:88:9: 'varMax'
            {
            match("varMax"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:89:7: ( 'rox' )
            // InternalXScript.g:89:9: 'rox'
            {
            match("rox"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "RULE_DOUBLE"
    public final void mRULE_DOUBLE() throws RecognitionException {
        try {
            int _type = RULE_DOUBLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17610:13: ( ( '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '-' )? ( '0' .. '9' )+ )? )
            // InternalXScript.g:17610:15: ( '-' )? ( '0' .. '9' )+ '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) ( '-' )? ( '0' .. '9' )+ )?
            {
            // InternalXScript.g:17610:15: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalXScript.g:17610:15: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // InternalXScript.g:17610:20: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalXScript.g:17610:21: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            match('.'); 
            // InternalXScript.g:17610:36: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalXScript.g:17610:37: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            // InternalXScript.g:17610:48: ( ( 'e' | 'E' ) ( '-' )? ( '0' .. '9' )+ )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='E'||LA6_0=='e') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalXScript.g:17610:49: ( 'e' | 'E' ) ( '-' )? ( '0' .. '9' )+
                    {
                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    // InternalXScript.g:17610:59: ( '-' )?
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0=='-') ) {
                        alt4=1;
                    }
                    switch (alt4) {
                        case 1 :
                            // InternalXScript.g:17610:59: '-'
                            {
                            match('-'); 

                            }
                            break;

                    }

                    // InternalXScript.g:17610:64: ( '0' .. '9' )+
                    int cnt5=0;
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalXScript.g:17610:65: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt5 >= 1 ) break loop5;
                                EarlyExitException eee =
                                    new EarlyExitException(5, input);
                                throw eee;
                        }
                        cnt5++;
                    } while (true);


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DOUBLE"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17612:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalXScript.g:17612:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalXScript.g:17612:11: ( '^' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='^') ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalXScript.g:17612:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalXScript.g:17612:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='0' && LA8_0<='9')||(LA8_0>='A' && LA8_0<='Z')||LA8_0=='_'||(LA8_0>='a' && LA8_0<='z')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalXScript.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17614:10: ( ( '0' .. '9' )+ )
            // InternalXScript.g:17614:12: ( '0' .. '9' )+
            {
            // InternalXScript.g:17614:12: ( '0' .. '9' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='0' && LA9_0<='9')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalXScript.g:17614:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17616:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalXScript.g:17616:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalXScript.g:17616:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\"') ) {
                alt12=1;
            }
            else if ( (LA12_0=='\'') ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalXScript.g:17616:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalXScript.g:17616:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop10:
                    do {
                        int alt10=3;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0=='\\') ) {
                            alt10=1;
                        }
                        else if ( ((LA10_0>='\u0000' && LA10_0<='!')||(LA10_0>='#' && LA10_0<='[')||(LA10_0>=']' && LA10_0<='\uFFFF')) ) {
                            alt10=2;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalXScript.g:17616:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalXScript.g:17616:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalXScript.g:17616:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalXScript.g:17616:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop11:
                    do {
                        int alt11=3;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0=='\\') ) {
                            alt11=1;
                        }
                        else if ( ((LA11_0>='\u0000' && LA11_0<='&')||(LA11_0>='(' && LA11_0<='[')||(LA11_0>=']' && LA11_0<='\uFFFF')) ) {
                            alt11=2;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalXScript.g:17616:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalXScript.g:17616:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17618:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalXScript.g:17618:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalXScript.g:17618:24: ( options {greedy=false; } : . )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0=='*') ) {
                    int LA13_1 = input.LA(2);

                    if ( (LA13_1=='/') ) {
                        alt13=2;
                    }
                    else if ( ((LA13_1>='\u0000' && LA13_1<='.')||(LA13_1>='0' && LA13_1<='\uFFFF')) ) {
                        alt13=1;
                    }


                }
                else if ( ((LA13_0>='\u0000' && LA13_0<=')')||(LA13_0>='+' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalXScript.g:17618:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17620:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalXScript.g:17620:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalXScript.g:17620:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>='\u0000' && LA14_0<='\t')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\uFFFF')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalXScript.g:17620:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // InternalXScript.g:17620:40: ( ( '\\r' )? '\\n' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='\n'||LA16_0=='\r') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // InternalXScript.g:17620:41: ( '\\r' )? '\\n'
                    {
                    // InternalXScript.g:17620:41: ( '\\r' )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0=='\r') ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // InternalXScript.g:17620:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17622:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalXScript.g:17622:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalXScript.g:17622:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='\t' && LA17_0<='\n')||LA17_0=='\r'||LA17_0==' ') ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalXScript.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalXScript.g:17624:16: ( . )
            // InternalXScript.g:17624:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalXScript.g:1:8: ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | RULE_DOUBLE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt18=87;
        alt18 = dfa18.predict(input);
        switch (alt18) {
            case 1 :
                // InternalXScript.g:1:10: T__12
                {
                mT__12(); 

                }
                break;
            case 2 :
                // InternalXScript.g:1:16: T__13
                {
                mT__13(); 

                }
                break;
            case 3 :
                // InternalXScript.g:1:22: T__14
                {
                mT__14(); 

                }
                break;
            case 4 :
                // InternalXScript.g:1:28: T__15
                {
                mT__15(); 

                }
                break;
            case 5 :
                // InternalXScript.g:1:34: T__16
                {
                mT__16(); 

                }
                break;
            case 6 :
                // InternalXScript.g:1:40: T__17
                {
                mT__17(); 

                }
                break;
            case 7 :
                // InternalXScript.g:1:46: T__18
                {
                mT__18(); 

                }
                break;
            case 8 :
                // InternalXScript.g:1:52: T__19
                {
                mT__19(); 

                }
                break;
            case 9 :
                // InternalXScript.g:1:58: T__20
                {
                mT__20(); 

                }
                break;
            case 10 :
                // InternalXScript.g:1:64: T__21
                {
                mT__21(); 

                }
                break;
            case 11 :
                // InternalXScript.g:1:70: T__22
                {
                mT__22(); 

                }
                break;
            case 12 :
                // InternalXScript.g:1:76: T__23
                {
                mT__23(); 

                }
                break;
            case 13 :
                // InternalXScript.g:1:82: T__24
                {
                mT__24(); 

                }
                break;
            case 14 :
                // InternalXScript.g:1:88: T__25
                {
                mT__25(); 

                }
                break;
            case 15 :
                // InternalXScript.g:1:94: T__26
                {
                mT__26(); 

                }
                break;
            case 16 :
                // InternalXScript.g:1:100: T__27
                {
                mT__27(); 

                }
                break;
            case 17 :
                // InternalXScript.g:1:106: T__28
                {
                mT__28(); 

                }
                break;
            case 18 :
                // InternalXScript.g:1:112: T__29
                {
                mT__29(); 

                }
                break;
            case 19 :
                // InternalXScript.g:1:118: T__30
                {
                mT__30(); 

                }
                break;
            case 20 :
                // InternalXScript.g:1:124: T__31
                {
                mT__31(); 

                }
                break;
            case 21 :
                // InternalXScript.g:1:130: T__32
                {
                mT__32(); 

                }
                break;
            case 22 :
                // InternalXScript.g:1:136: T__33
                {
                mT__33(); 

                }
                break;
            case 23 :
                // InternalXScript.g:1:142: T__34
                {
                mT__34(); 

                }
                break;
            case 24 :
                // InternalXScript.g:1:148: T__35
                {
                mT__35(); 

                }
                break;
            case 25 :
                // InternalXScript.g:1:154: T__36
                {
                mT__36(); 

                }
                break;
            case 26 :
                // InternalXScript.g:1:160: T__37
                {
                mT__37(); 

                }
                break;
            case 27 :
                // InternalXScript.g:1:166: T__38
                {
                mT__38(); 

                }
                break;
            case 28 :
                // InternalXScript.g:1:172: T__39
                {
                mT__39(); 

                }
                break;
            case 29 :
                // InternalXScript.g:1:178: T__40
                {
                mT__40(); 

                }
                break;
            case 30 :
                // InternalXScript.g:1:184: T__41
                {
                mT__41(); 

                }
                break;
            case 31 :
                // InternalXScript.g:1:190: T__42
                {
                mT__42(); 

                }
                break;
            case 32 :
                // InternalXScript.g:1:196: T__43
                {
                mT__43(); 

                }
                break;
            case 33 :
                // InternalXScript.g:1:202: T__44
                {
                mT__44(); 

                }
                break;
            case 34 :
                // InternalXScript.g:1:208: T__45
                {
                mT__45(); 

                }
                break;
            case 35 :
                // InternalXScript.g:1:214: T__46
                {
                mT__46(); 

                }
                break;
            case 36 :
                // InternalXScript.g:1:220: T__47
                {
                mT__47(); 

                }
                break;
            case 37 :
                // InternalXScript.g:1:226: T__48
                {
                mT__48(); 

                }
                break;
            case 38 :
                // InternalXScript.g:1:232: T__49
                {
                mT__49(); 

                }
                break;
            case 39 :
                // InternalXScript.g:1:238: T__50
                {
                mT__50(); 

                }
                break;
            case 40 :
                // InternalXScript.g:1:244: T__51
                {
                mT__51(); 

                }
                break;
            case 41 :
                // InternalXScript.g:1:250: T__52
                {
                mT__52(); 

                }
                break;
            case 42 :
                // InternalXScript.g:1:256: T__53
                {
                mT__53(); 

                }
                break;
            case 43 :
                // InternalXScript.g:1:262: T__54
                {
                mT__54(); 

                }
                break;
            case 44 :
                // InternalXScript.g:1:268: T__55
                {
                mT__55(); 

                }
                break;
            case 45 :
                // InternalXScript.g:1:274: T__56
                {
                mT__56(); 

                }
                break;
            case 46 :
                // InternalXScript.g:1:280: T__57
                {
                mT__57(); 

                }
                break;
            case 47 :
                // InternalXScript.g:1:286: T__58
                {
                mT__58(); 

                }
                break;
            case 48 :
                // InternalXScript.g:1:292: T__59
                {
                mT__59(); 

                }
                break;
            case 49 :
                // InternalXScript.g:1:298: T__60
                {
                mT__60(); 

                }
                break;
            case 50 :
                // InternalXScript.g:1:304: T__61
                {
                mT__61(); 

                }
                break;
            case 51 :
                // InternalXScript.g:1:310: T__62
                {
                mT__62(); 

                }
                break;
            case 52 :
                // InternalXScript.g:1:316: T__63
                {
                mT__63(); 

                }
                break;
            case 53 :
                // InternalXScript.g:1:322: T__64
                {
                mT__64(); 

                }
                break;
            case 54 :
                // InternalXScript.g:1:328: T__65
                {
                mT__65(); 

                }
                break;
            case 55 :
                // InternalXScript.g:1:334: T__66
                {
                mT__66(); 

                }
                break;
            case 56 :
                // InternalXScript.g:1:340: T__67
                {
                mT__67(); 

                }
                break;
            case 57 :
                // InternalXScript.g:1:346: T__68
                {
                mT__68(); 

                }
                break;
            case 58 :
                // InternalXScript.g:1:352: T__69
                {
                mT__69(); 

                }
                break;
            case 59 :
                // InternalXScript.g:1:358: T__70
                {
                mT__70(); 

                }
                break;
            case 60 :
                // InternalXScript.g:1:364: T__71
                {
                mT__71(); 

                }
                break;
            case 61 :
                // InternalXScript.g:1:370: T__72
                {
                mT__72(); 

                }
                break;
            case 62 :
                // InternalXScript.g:1:376: T__73
                {
                mT__73(); 

                }
                break;
            case 63 :
                // InternalXScript.g:1:382: T__74
                {
                mT__74(); 

                }
                break;
            case 64 :
                // InternalXScript.g:1:388: T__75
                {
                mT__75(); 

                }
                break;
            case 65 :
                // InternalXScript.g:1:394: T__76
                {
                mT__76(); 

                }
                break;
            case 66 :
                // InternalXScript.g:1:400: T__77
                {
                mT__77(); 

                }
                break;
            case 67 :
                // InternalXScript.g:1:406: T__78
                {
                mT__78(); 

                }
                break;
            case 68 :
                // InternalXScript.g:1:412: T__79
                {
                mT__79(); 

                }
                break;
            case 69 :
                // InternalXScript.g:1:418: T__80
                {
                mT__80(); 

                }
                break;
            case 70 :
                // InternalXScript.g:1:424: T__81
                {
                mT__81(); 

                }
                break;
            case 71 :
                // InternalXScript.g:1:430: T__82
                {
                mT__82(); 

                }
                break;
            case 72 :
                // InternalXScript.g:1:436: T__83
                {
                mT__83(); 

                }
                break;
            case 73 :
                // InternalXScript.g:1:442: T__84
                {
                mT__84(); 

                }
                break;
            case 74 :
                // InternalXScript.g:1:448: T__85
                {
                mT__85(); 

                }
                break;
            case 75 :
                // InternalXScript.g:1:454: T__86
                {
                mT__86(); 

                }
                break;
            case 76 :
                // InternalXScript.g:1:460: T__87
                {
                mT__87(); 

                }
                break;
            case 77 :
                // InternalXScript.g:1:466: T__88
                {
                mT__88(); 

                }
                break;
            case 78 :
                // InternalXScript.g:1:472: T__89
                {
                mT__89(); 

                }
                break;
            case 79 :
                // InternalXScript.g:1:478: T__90
                {
                mT__90(); 

                }
                break;
            case 80 :
                // InternalXScript.g:1:484: RULE_DOUBLE
                {
                mRULE_DOUBLE(); 

                }
                break;
            case 81 :
                // InternalXScript.g:1:496: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 82 :
                // InternalXScript.g:1:504: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 83 :
                // InternalXScript.g:1:513: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 84 :
                // InternalXScript.g:1:525: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 85 :
                // InternalXScript.g:1:541: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 86 :
                // InternalXScript.g:1:557: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 87 :
                // InternalXScript.g:1:565: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA18_eotS =
        "\1\uffff\1\62\1\64\1\66\1\70\1\uffff\1\74\13\77\1\126\2\77\4\uffff\2\60\1\143\1\145\2\uffff\1\77\2\uffff\10\77\1\171\1\60\1\uffff\2\60\16\uffff\2\77\1\uffff\26\77\1\uffff\1\u0098\4\77\15\uffff\2\77\2\uffff\4\77\1\u00a4\6\77\1\171\3\uffff\3\77\1\u00ae\1\77\1\u00b0\1\77\1\u00b2\1\u00b3\2\77\1\u00b6\2\77\1\u00b9\6\77\1\u00c3\1\u00c4\1\u00c5\1\u00c6\1\77\1\u00c8\1\77\1\uffff\1\77\1\u00cb\2\77\1\u00ce\1\77\1\u00d0\1\u00d1\1\77\1\u00d3\2\uffff\5\77\1\u00d9\1\u00da\1\u00db\1\77\1\uffff\1\77\1\uffff\1\77\2\uffff\1\u00df\1\77\1\uffff\2\77\1\uffff\3\77\1\u00e6\5\77\4\uffff\1\u00ec\1\uffff\1\u00ed\1\u00ee\1\uffff\1\u00ef\1\77\1\uffff\1\u00f2\2\uffff\1\77\1\uffff\5\77\3\uffff\1\u00f9\1\77\1\u00fb\1\uffff\4\77\1\u0100\1\77\1\uffff\1\u0102\1\u0103\3\77\4\uffff\2\77\1\uffff\2\77\1\u010b\3\77\1\uffff\1\77\1\uffff\1\77\1\u0112\2\77\1\uffff\1\u0115\2\uffff\1\77\1\u0117\1\u0118\1\uffff\1\u0119\1\u011a\1\77\1\uffff\1\77\1\u011e\4\77\1\uffff\2\77\1\uffff\1\77\4\uffff\3\77\1\uffff\1\77\1\u012b\1\u012c\3\77\1\u0130\5\77\2\uffff\3\77\1\uffff\11\77\1\u0142\6\77\1\u0149\1\uffff\5\77\1\u014f\1\uffff\5\77\1\uffff\1\77\1\u0156\1\u0157\1\u0158\1\77\1\u015a\3\uffff\1\77\1\uffff\1\77\1\u015d\1\uffff";
    static final String DFA18_eofS =
        "\u015e\uffff";
    static final String DFA18_minS =
        "\1\0\4\75\1\uffff\1\52\1\151\1\141\1\162\2\145\1\141\1\105\1\111\1\117\1\116\1\117\1\60\1\101\1\111\4\uffff\1\174\1\46\1\53\1\55\2\uffff\1\154\2\uffff\1\145\1\166\1\146\1\156\1\154\1\150\1\145\1\163\1\56\1\101\1\uffff\2\0\16\uffff\1\165\1\143\1\uffff\1\154\1\144\1\156\1\144\1\145\1\142\2\141\1\143\1\147\1\143\1\164\1\145\1\146\1\142\1\154\1\103\1\122\1\116\1\125\1\124\1\117\1\uffff\1\60\1\124\1\102\1\116\1\115\15\uffff\1\160\1\163\2\uffff\1\143\1\164\1\147\1\40\1\60\1\164\1\141\1\154\1\151\1\162\1\164\1\56\3\uffff\1\145\1\153\1\163\1\60\1\143\1\60\1\141\2\60\1\162\1\160\1\60\1\156\1\145\1\60\1\123\1\145\1\101\1\146\2\165\4\60\1\122\1\60\1\154\1\uffff\1\105\1\60\1\107\1\111\1\60\1\145\2\60\1\165\1\60\2\uffff\1\151\1\163\1\165\1\154\1\143\3\60\1\145\1\uffff\1\164\1\uffff\1\153\2\uffff\1\60\1\163\1\uffff\2\141\1\uffff\1\164\1\160\1\166\1\60\1\147\1\145\1\141\1\166\1\141\4\uffff\1\60\1\uffff\2\60\1\uffff\1\60\1\124\1\uffff\1\60\2\uffff\1\162\1\uffff\1\164\1\163\1\155\1\163\1\145\3\uffff\1\60\1\151\1\60\1\uffff\1\150\1\154\1\155\1\162\1\60\1\147\1\uffff\2\60\1\156\1\147\1\170\4\uffff\1\40\1\146\1\uffff\1\156\1\171\1\60\1\156\1\164\1\156\1\uffff\1\157\1\uffff\1\157\1\60\1\126\1\145\1\uffff\1\60\2\uffff\1\143\2\60\1\uffff\2\60\1\121\1\uffff\1\120\1\60\1\164\1\156\1\164\1\151\1\uffff\2\141\1\uffff\1\145\4\uffff\1\165\1\164\1\141\1\uffff\1\103\2\60\1\163\1\162\1\155\1\60\1\145\1\162\1\151\1\150\1\165\2\uffff\1\164\1\114\1\126\1\uffff\1\162\1\153\1\162\1\141\1\156\1\145\1\151\1\141\1\171\1\60\1\123\3\156\1\163\1\162\1\60\1\uffff\1\164\1\147\2\145\1\164\1\60\1\uffff\1\162\1\145\2\162\1\145\1\uffff\1\153\3\60\1\156\1\60\3\uffff\1\145\1\uffff\1\162\1\60\1\uffff";
    static final String DFA18_maxS =
        "\1\uffff\4\75\1\uffff\1\57\1\162\2\167\1\166\1\151\1\141\1\124\1\111\1\117\1\116\1\117\1\172\1\125\1\117\4\uffff\1\174\1\46\1\53\1\71\2\uffff\1\170\2\uffff\1\157\1\166\2\156\1\157\1\150\1\145\1\163\1\71\1\172\1\uffff\2\uffff\16\uffff\1\165\1\143\1\uffff\1\154\1\144\1\156\1\144\1\145\1\155\2\141\1\143\1\147\1\162\1\164\1\162\1\146\1\142\1\162\1\103\1\122\1\116\1\125\1\124\1\117\1\uffff\1\172\1\124\1\102\1\116\1\115\15\uffff\1\160\1\163\2\uffff\1\170\1\164\1\147\1\40\1\172\1\164\1\141\1\154\1\151\1\162\1\164\1\71\3\uffff\1\145\1\153\1\163\1\172\1\143\1\172\1\141\2\172\1\162\1\160\1\172\1\156\1\145\1\172\1\123\1\145\1\101\1\146\2\165\4\172\1\122\1\172\1\154\1\uffff\1\105\1\172\1\107\1\111\1\172\1\145\2\172\1\165\1\172\2\uffff\1\151\1\163\1\165\1\154\1\143\3\172\1\145\1\uffff\1\164\1\uffff\1\153\2\uffff\1\172\1\163\1\uffff\2\141\1\uffff\1\164\1\160\1\166\1\172\1\147\1\145\1\141\1\166\1\141\4\uffff\1\172\1\uffff\2\172\1\uffff\1\172\1\124\1\uffff\1\172\2\uffff\1\162\1\uffff\1\164\1\163\1\155\1\163\1\145\3\uffff\1\172\1\151\1\172\1\uffff\1\150\1\154\1\155\1\162\1\172\1\147\1\uffff\2\172\1\156\1\147\1\170\4\uffff\1\40\1\146\1\uffff\1\156\1\171\1\172\1\156\1\164\1\156\1\uffff\1\157\1\uffff\1\157\1\172\1\126\1\145\1\uffff\1\172\2\uffff\1\143\2\172\1\uffff\2\172\1\121\1\uffff\1\123\1\172\1\164\1\156\1\164\1\151\1\uffff\2\141\1\uffff\1\145\4\uffff\1\165\1\164\1\141\1\uffff\1\103\2\172\1\163\1\162\1\155\1\172\1\145\1\162\1\151\1\150\1\165\2\uffff\1\164\1\114\1\126\1\uffff\1\162\1\153\1\162\1\141\1\156\1\145\1\151\1\141\1\171\1\172\1\123\3\156\1\163\1\162\1\172\1\uffff\1\164\1\147\2\145\1\164\1\172\1\uffff\1\162\1\145\2\162\1\145\1\uffff\1\153\3\172\1\156\1\172\3\uffff\1\145\1\uffff\1\162\1\172\1\uffff";
    static final String DFA18_acceptS =
        "\5\uffff\1\7\17\uffff\1\36\1\37\1\40\1\41\4\uffff\1\50\1\51\1\uffff\1\53\1\54\12\uffff\1\121\2\uffff\1\126\1\127\1\1\1\13\1\2\1\46\1\3\1\5\1\4\1\6\1\7\1\124\1\125\1\10\2\uffff\1\121\26\uffff\1\30\5\uffff\1\36\1\37\1\40\1\41\1\42\1\43\1\75\1\44\1\76\1\45\1\120\1\50\1\51\2\uffff\1\53\1\54\14\uffff\1\122\1\123\1\126\34\uffff\1\31\12\uffff\1\62\1\101\11\uffff\1\15\1\uffff\1\14\1\uffff\1\16\1\60\2\uffff\1\61\2\uffff\1\114\11\uffff\1\67\1\22\1\25\1\23\1\uffff\1\26\2\uffff\1\33\2\uffff\1\52\1\uffff\1\56\1\117\1\uffff\1\57\5\uffff\1\113\1\11\1\47\3\uffff\1\35\6\uffff\1\17\5\uffff\1\24\1\27\1\32\1\34\2\uffff\1\103\6\uffff\1\12\1\uffff\1\105\4\uffff\1\106\1\uffff\1\100\1\20\3\uffff\1\64\3\uffff\1\66\6\uffff\1\65\2\uffff\1\112\1\uffff\1\115\1\116\1\102\1\71\3\uffff\1\104\14\uffff\1\70\1\55\3\uffff\1\21\21\uffff\1\110\6\uffff\1\63\5\uffff\1\77\6\uffff\1\107\1\74\1\72\1\uffff\1\111\2\uffff\1\73";
    static final String DFA18_specialS =
        "\1\1\54\uffff\1\2\1\0\u012f\uffff}>";
    static final String[] DFA18_transitionS = {
            "\11\60\2\57\2\60\1\57\22\60\1\57\1\2\1\55\3\60\1\32\1\56\1\25\1\27\1\5\1\33\1\26\1\34\1\60\1\6\12\52\1\60\1\30\1\4\1\1\1\3\2\60\1\54\1\21\1\54\1\23\1\45\2\54\1\17\1\20\2\54\1\24\1\16\5\54\1\15\1\22\6\54\1\35\1\60\1\36\1\53\1\54\1\60\1\43\1\11\1\46\1\13\1\37\1\10\2\54\1\44\2\54\1\51\3\54\1\50\1\54\1\42\1\12\1\7\1\54\1\14\1\47\3\54\1\40\1\31\1\41\uff82\60",
            "\1\61",
            "\1\63",
            "\1\65",
            "\1\67",
            "",
            "\1\72\4\uffff\1\73",
            "\1\76\10\uffff\1\75",
            "\1\100\23\uffff\1\102\1\uffff\1\101",
            "\1\104\4\uffff\1\103",
            "\1\113\3\uffff\1\111\2\uffff\1\114\1\uffff\1\107\4\uffff\1\110\1\112\1\105\1\106",
            "\1\116\3\uffff\1\115",
            "\1\117",
            "\1\120\16\uffff\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\130\22\uffff\1\127\1\131",
            "\1\133\5\uffff\1\132",
            "",
            "",
            "",
            "",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\144\2\uffff\12\146",
            "",
            "",
            "\1\152\13\uffff\1\151",
            "",
            "",
            "\1\156\11\uffff\1\155",
            "\1\157",
            "\1\161\7\uffff\1\160",
            "\1\162",
            "\1\163\2\uffff\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\146\1\uffff\12\170",
            "\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "\0\172",
            "\0\172",
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
            "",
            "",
            "",
            "\1\174",
            "\1\175",
            "",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0084\12\uffff\1\u0083",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u008a\16\uffff\1\u0089",
            "\1\u008b",
            "\1\u008c\14\uffff\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090\5\uffff\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
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
            "",
            "",
            "\1\u009d",
            "\1\u009e",
            "",
            "",
            "\1\u009f\24\uffff\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00a5",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\146\1\uffff\12\170",
            "",
            "",
            "",
            "\1\u00ab",
            "\1\u00ac",
            "\1\u00ad",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00af",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00b1",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00b4",
            "\1\u00b5",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00b7",
            "\1\u00b8",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00ba",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\12\77\7\uffff\1\u00c1\13\77\1\u00c2\15\77\4\uffff\1\77\1\uffff\10\77\1\u00c0\21\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00c7",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00c9",
            "",
            "\1\u00ca",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00cc",
            "\1\u00cd",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00cf",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00d2",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "",
            "\1\u00d4",
            "\1\u00d5",
            "\1\u00d6",
            "\1\u00d7",
            "\1\u00d8",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00dc",
            "",
            "\1\u00dd",
            "",
            "\1\u00de",
            "",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00e0",
            "",
            "\1\u00e1",
            "\1\u00e2",
            "",
            "\1\u00e3",
            "\1\u00e4",
            "\1\u00e5",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00e7",
            "\1\u00e8",
            "\1\u00e9",
            "\1\u00ea",
            "\1\u00eb",
            "",
            "",
            "",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00f0",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\10\77\1\u00f1\21\77",
            "",
            "",
            "\1\u00f3",
            "",
            "\1\u00f4",
            "\1\u00f5",
            "\1\u00f6",
            "\1\u00f7",
            "\1\u00f8",
            "",
            "",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u00fa",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "\1\u00fc",
            "\1\u00fd",
            "\1\u00fe",
            "\1\u00ff",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u0101",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u0104",
            "\1\u0105",
            "\1\u0106",
            "",
            "",
            "",
            "",
            "\1\u0107",
            "\1\u0108",
            "",
            "\1\u0109",
            "\1\u010a",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u010c",
            "\1\u010d",
            "\1\u010e",
            "",
            "\1\u010f",
            "",
            "\1\u0110",
            "\12\77\7\uffff\13\77\1\u0111\16\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u0113",
            "\1\u0114",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "",
            "\1\u0116",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u011b",
            "",
            "\1\u011d\2\uffff\1\u011c",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u011f",
            "\1\u0120",
            "\1\u0121",
            "\1\u0122",
            "",
            "\1\u0123",
            "\1\u0124",
            "",
            "\1\u0125",
            "",
            "",
            "",
            "",
            "\1\u0126",
            "\1\u0127",
            "\1\u0128",
            "",
            "\1\u0129",
            "\12\77\7\uffff\21\77\1\u012a\10\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u012d",
            "\1\u012e",
            "\1\u012f",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u0131",
            "\1\u0132",
            "\1\u0133",
            "\1\u0134",
            "\1\u0135",
            "",
            "",
            "\1\u0136",
            "\1\u0137",
            "\1\u0138",
            "",
            "\1\u0139",
            "\1\u013a",
            "\1\u013b",
            "\1\u013c",
            "\1\u013d",
            "\1\u013e",
            "\1\u013f",
            "\1\u0140",
            "\1\u0141",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u0143",
            "\1\u0144",
            "\1\u0145",
            "\1\u0146",
            "\1\u0147",
            "\1\u0148",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "\1\u014a",
            "\1\u014b",
            "\1\u014c",
            "\1\u014d",
            "\1\u014e",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "\1\u0150",
            "\1\u0151",
            "\1\u0152",
            "\1\u0153",
            "\1\u0154",
            "",
            "\1\u0155",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "\1\u0159",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            "",
            "",
            "",
            "\1\u015b",
            "",
            "\1\u015c",
            "\12\77\7\uffff\32\77\4\uffff\1\77\1\uffff\32\77",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | RULE_DOUBLE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA18_46 = input.LA(1);

                        s = -1;
                        if ( ((LA18_46>='\u0000' && LA18_46<='\uFFFF')) ) {s = 122;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA18_0 = input.LA(1);

                        s = -1;
                        if ( (LA18_0=='=') ) {s = 1;}

                        else if ( (LA18_0=='!') ) {s = 2;}

                        else if ( (LA18_0=='>') ) {s = 3;}

                        else if ( (LA18_0=='<') ) {s = 4;}

                        else if ( (LA18_0=='*') ) {s = 5;}

                        else if ( (LA18_0=='/') ) {s = 6;}

                        else if ( (LA18_0=='t') ) {s = 7;}

                        else if ( (LA18_0=='f') ) {s = 8;}

                        else if ( (LA18_0=='b') ) {s = 9;}

                        else if ( (LA18_0=='s') ) {s = 10;}

                        else if ( (LA18_0=='d') ) {s = 11;}

                        else if ( (LA18_0=='v') ) {s = 12;}

                        else if ( (LA18_0=='S') ) {s = 13;}

                        else if ( (LA18_0=='M') ) {s = 14;}

                        else if ( (LA18_0=='H') ) {s = 15;}

                        else if ( (LA18_0=='I') ) {s = 16;}

                        else if ( (LA18_0=='B') ) {s = 17;}

                        else if ( (LA18_0=='T') ) {s = 18;}

                        else if ( (LA18_0=='D') ) {s = 19;}

                        else if ( (LA18_0=='L') ) {s = 20;}

                        else if ( (LA18_0=='(') ) {s = 21;}

                        else if ( (LA18_0==',') ) {s = 22;}

                        else if ( (LA18_0==')') ) {s = 23;}

                        else if ( (LA18_0==';') ) {s = 24;}

                        else if ( (LA18_0=='|') ) {s = 25;}

                        else if ( (LA18_0=='&') ) {s = 26;}

                        else if ( (LA18_0=='+') ) {s = 27;}

                        else if ( (LA18_0=='-') ) {s = 28;}

                        else if ( (LA18_0=='[') ) {s = 29;}

                        else if ( (LA18_0==']') ) {s = 30;}

                        else if ( (LA18_0=='e') ) {s = 31;}

                        else if ( (LA18_0=='{') ) {s = 32;}

                        else if ( (LA18_0=='}') ) {s = 33;}

                        else if ( (LA18_0=='r') ) {s = 34;}

                        else if ( (LA18_0=='a') ) {s = 35;}

                        else if ( (LA18_0=='i') ) {s = 36;}

                        else if ( (LA18_0=='E') ) {s = 37;}

                        else if ( (LA18_0=='c') ) {s = 38;}

                        else if ( (LA18_0=='w') ) {s = 39;}

                        else if ( (LA18_0=='p') ) {s = 40;}

                        else if ( (LA18_0=='l') ) {s = 41;}

                        else if ( ((LA18_0>='0' && LA18_0<='9')) ) {s = 42;}

                        else if ( (LA18_0=='^') ) {s = 43;}

                        else if ( (LA18_0=='A'||LA18_0=='C'||(LA18_0>='F' && LA18_0<='G')||(LA18_0>='J' && LA18_0<='K')||(LA18_0>='N' && LA18_0<='R')||(LA18_0>='U' && LA18_0<='Z')||LA18_0=='_'||(LA18_0>='g' && LA18_0<='h')||(LA18_0>='j' && LA18_0<='k')||(LA18_0>='m' && LA18_0<='o')||LA18_0=='q'||LA18_0=='u'||(LA18_0>='x' && LA18_0<='z')) ) {s = 44;}

                        else if ( (LA18_0=='\"') ) {s = 45;}

                        else if ( (LA18_0=='\'') ) {s = 46;}

                        else if ( ((LA18_0>='\t' && LA18_0<='\n')||LA18_0=='\r'||LA18_0==' ') ) {s = 47;}

                        else if ( ((LA18_0>='\u0000' && LA18_0<='\b')||(LA18_0>='\u000B' && LA18_0<='\f')||(LA18_0>='\u000E' && LA18_0<='\u001F')||(LA18_0>='#' && LA18_0<='%')||LA18_0=='.'||LA18_0==':'||(LA18_0>='?' && LA18_0<='@')||LA18_0=='\\'||LA18_0=='`'||(LA18_0>='~' && LA18_0<='\uFFFF')) ) {s = 48;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA18_45 = input.LA(1);

                        s = -1;
                        if ( ((LA18_45>='\u0000' && LA18_45<='\uFFFF')) ) {s = 122;}

                        else s = 48;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 18, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}