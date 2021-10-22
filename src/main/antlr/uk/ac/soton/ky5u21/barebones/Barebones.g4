grammar Barebones;

program: statement* EOF;
statement: clear
  | increment
  | decrement
  | whileLoop;
clear: 'clear' ID ';';
increment: 'incr' ID ';';
decrement: 'decr' ID ';';
whileLoop: 'while' ID 'not 0 do;' body=statement* 'end;';


ID: 'a' .. 'z' ('a' .. 'z' | '0' .. '9' | '_')*;
WS: [ \n\t\r]+ -> skip;
