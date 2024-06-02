/**********************************************************
 *  TICK DATA INPUTS 100 
 * *********************************************************/  
 svar TickSymbol(1,STR) = tick(202,0,STR); // Security Trading Symbol  
 svar TickLast(2,DUB) = tick(202,1,DUB); // Current Price  
 svar TickVolume(3,LONG) = tick(202,27,LONG); // Current Volume 
 svar TickTradeCount(4,INT) = tick(202,30,INT); // Current Trade Count 

 /**********************************************************
 *  TICK DATA 1 SECOND SNAPSHOTS 200 
 * *********************************************************/
 svar TickLast1sec(5,DUB) = snapshot(TickLast[0]) 1 SEC; 
 svar TickVolume1sec(6,LONG) = snapshot(TickVolume[0]) 1 SEC; 
 svar TickTradeCount1sec(7,INT) = snapshot(TickTradeCount[0]) 1 SEC; 

/**********************************************************
 *  SIMPLE MOVING AVERAGE PRICE (SECONDS) 1,000 
 * *********************************************************/
 svar Sma10sec(8,DUB) = avg({TickLast1sec[0,9]}); // 10 sec 
 svar Sma20sec(9,DUB) = avg({TickLast1sec[0,19]}); // 20 sec 
 svar Sma30sec(10,DUB) = avg({TickLast1sec[0,29]}); // 30 sec 
 svar Sma40sec(11,DUB) = avg({TickLast1sec[0,39]}); // 40 sec 
 svar Sma50sec(12,DUB) = avg({TickLast1sec[0,49]}); // 50 sec
 
 
/**********************************************************
 *  SIMPLE MOVING AVERAGE PRICE (MINUTES) 2,000 
 * *********************************************************/
 svar Sma60sec(13,DUB) = avg({TickLast1sec[0,59]}); // 1 minute
 svar Sma90sec(14,DUB) = avg({TickLast1sec[0,89]}); // 1 minute
 svar Sma120sec(15,DUB) = avg({TickLast1sec[0,119]}); // 2 minute 
 svar Sma150sec(16,DUB) = avg({TickLast1sec[0,149]}); // 2 minute 
 svar Sma180sec(17,DUB) = avg({TickLast1sec[0,179]}); // 3 minute 
 svar Sma240sec(18,DUB) = avg({TickLast1sec[0,239]}); // 4 minute 
 svar Sma300sec(19,DUB) = avg({TickLast1sec[0,299]}); // 5 minute 

 /*******************************************************
 * MOVING VOLUME COUNTS (SECONDS) 5,000
 * *****************************************************/
 svar Mvc5sec(20,LONG) = sub(TickVolume1sec[0],TickVolume1sec[4]);
 svar Mvc10sec(21,LONG) = sub(TickVolume1sec[0],TickVolume1sec[9]);
 svar Mvc30sec(22,LONG) = sub(TickVolume1sec[0],TickVolume1sec[29]);
 svar Mvc60sec(23,LONG) = sub(TickVolume1sec[0],TickVolume1sec[59]);

/*******************************************************
 * VOLUME COUNT BARS (SECONDS) 6,000
 * *****************************************************/

 svar Vcb5sec(24,LONG) = snapshot(sub(TickVolume1sec[0],TickVolume1sec[4])) 5 SEC;
 svar Vcb10sec(25,LONG) = snapshot(sub(TickVolume1sec[0],TickVolume1sec[9])) 10 SEC;
 svar Vcb15sec(26,LONG) = snapshot(sub(TickVolume1sec[0],TickVolume1sec[14])) 15 SEC;
 svar Vcb30sec(27,LONG) = snapshot(sub(TickVolume1sec[0],TickVolume1sec[29])) 30 SEC;
 svar Vcb60sec(28,LONG) = snapshot(sub(TickVolume1sec[0],TickVolume1sec[59])) 60 SEC;

 /*******************************************************
 * MOVING TRADE COUNTS (SECONDS) 7,000
 * *****************************************************/

 svar Mtc5sec(29,LONG) = sub(TickTradeCount1sec[0],TickTradeCount1sec[4]);
 svar Mtc10sec(30,LONG) = sub(TickTradeCount1sec[0],TickTradeCount1sec[9]);
 svar Mtc15sec(31,LONG) = sub(TickTradeCount1sec[0],TickTradeCount1sec[14]);
 svar Mtc30sec(32,LONG) = sub(TickTradeCount1sec[0],TickTradeCount1sec[29]);

/*******************************************************
 * TRADE COUNT BARS (SECONDS) 8,000
 * *****************************************************/
 svar Tcb5sec(33,LONG) = snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[4])) 5 SEC;
 svar Tcb10sec(34,LONG) = snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[9])) 10 SEC;
 svar Tcb15sec(35,LONG) = snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[14])) 15 SEC;
 svar Tcb30sec(36,LONG) = snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[29])) 30 SEC;
 svar Tcb60sec(37,LONG) = snapshot(sub(TickTradeCount1sec[0],TickTradeCount1sec[59])) 60 SEC;

/*******************************************************
 * SMA RATE OF CHANGES (SECONDS) 30,000
 * *****************************************************/
 
 svar SmaRoc10x20sec(38,DUB) = roc(Sma10sec[0],Sma20sec[0]);
 svar SmaRoc20x30sec(39,DUB) = roc(Sma20sec[0],Sma30sec[0]);
 svar SmaRoc30x40sec(40,DUB) = roc(Sma30sec[0],Sma40sec[0]);
 svar SmaRoc40x50sec(41,DUB) = roc(Sma40sec[0],Sma50sec[0]);
 svar SmaRoc50x60sec(42,DUB) = roc(Sma50sec[0],Sma60sec[0]);

 // 1 minute increment
 svar SmaRoc60x120sec(43,DUB) = roc(Sma60sec[0],Sma120sec[0]);
 svar SmaRoc120x150sec(44,DUB) = roc(Sma120sec[0],Sma150sec[0]);
 svar SmaRoc120x180sec(45,DUB) = roc(Sma120sec[0],Sma180sec[0]);
 svar SmaRoc180x240sec(46,DUB) = roc(Sma180sec[0],Sma240sec[0]);
 svar SmaRoc240x300sec(47,DUB) = roc(Sma240sec[0],Sma300sec[0]);
