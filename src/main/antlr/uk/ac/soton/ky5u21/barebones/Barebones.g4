grammar Barebones;

program: statement* EOF;
statement: clear
  | increment
  | decrement
  | input
  | whileLoop;
clear: 'clear' name=ID ';';
increment: 'incr' name=ID ';';
decrement: 'decr' name=ID ';';
input: 'input' name=ID ';';
whileLoop: 'while' conditionVariable=ID 'not' '0' 'do' ';' (body+=statement)* 'end;';


ID: 'a' .. 'z' ('a' .. 'z' | '0' .. '9' | '_')*;
WS: [ \n\t\r]+ -> skip;
COMMENT: '#' ~[\r\n]* -> skip;
