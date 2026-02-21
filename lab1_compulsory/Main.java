//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
  //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
  // to see how IntelliJ IDEA suggests fixing it.
  System.out.println("Hello world!");

  String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
  int n = (int) (Math.random() * 1_000_000);

  n *=3;
  n += 0b10101;
  n += 0xFF;
  n *= 6;

  int result;
  if(n%9 == 0){
    result=9;

  }
  else{
    result=n%9;
  }

  System.out.println( "Willy-nilly, this semester I will learn " + languages[result]);
}
