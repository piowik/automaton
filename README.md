Celem projektu jest przygotowanie narzędzia <abbr title="Graphical User Interface">GUI</abbr> do wizualizacji różnych wariantów automatów komórkowych. Podstawowa implementacja powinna zawierać:
<ol>
<li class="level1">  grę w życie <a href="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life" class="urlextern" title="https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life"  rel="nofollow">Connwaya</a> w różnych wariantach: 
<ol>
<li class="level2"> zarówno podstawowym 23/3,
</li>
<li class="level2"> jak i dowolnym innym założonym przez użytkownika, 
</li>
<li class="level2"> quad life.
</li>
</ol>
</li>
<li class="level1"><a href="https://en.wikipedia.org/wiki/Langton%27s_ant" class="urlextern" title="https://en.wikipedia.org/wiki/Langton%27s_ant"  rel="nofollow">mrówkę langtona</a> (możliowść obsłużenia kilku mrówek w jednej symulacji),
</li>
<li class="level1"> <a href="https://en.wikipedia.org/wiki/Wireworld" class="urlextern" title="https://en.wikipedia.org/wiki/Wireworld"  rel="nofollow">wireworld</a>,
</li>
<li class="level1">automat jednowymiarowy:
<ol>
<li class="level2">w wersji podstawowej <a href="http://mathworld.wolfram.com/Rule30.html" class="urlextern" title="http://mathworld.wolfram.com/Rule30.html"  rel="nofollow">rule 30</a> i <a href="http://mathworld.wolfram.com/Rule110.html" class="urlextern" title="http://mathworld.wolfram.com/Rule110.html"  rel="nofollow">rule 110</a>.
</li>
<li class="level2"> wersja rozszerzona wszystkie 256 automatów.
</li>
</ol>
</li>
</ol>

<p>
Symulator powinien uwzględniać różne warianty sąsiedztwa komórek i efekty zawijania lub nie planszy. 
Warianty sąsiedztwa:
</p>
<ol>
<li class="level1"> sąsiedztwo dwuwymiarowe:
<ol>
<li class="level2"> <a href="http://mathworld.wolfram.com/vonNeumannNeighborhood.html" class="urlextern" title="http://mathworld.wolfram.com/vonNeumannNeighborhood.html"  rel="nofollow">von Neumana</a>
<ol>
<li class="level3"> wersja podstawowa r=1,
</li>
<li class="level3"> rozszerzona r dowolne.
</li>
</ol>
</li>
<li class="level2"> <a href="http://mathworld.wolfram.com/MooreNeighborhood.html" class="urlextern" title="http://mathworld.wolfram.com/MooreNeighborhood.html"  rel="nofollow">Moora</a>
<ol>
<li class="level3"> wersja podstawowa r=1,
</li>
<li class="level3"> rozszerzona r dowolne.
</li>
</ol>
</li>
</ol>
</li>
<li class="level1"> sąsiedztwo jednowymiarowe
</li>
<li class="level1"> dodatkowo niezależnie powinna być możliwość uzyskania zawinięcia planszy lub nie.
</li>
</ol>

<p>
Automaty powinny być wyposażone w możliwość dodawania podstawowych
struktur, np. dla gry w życie, możliwość dodania za jednym kliknięciem:
</p>
<ol>
<li class="level1"> <a href="https://en.wikipedia.org/wiki/File:Game_of_life_animated_glider.gif" class="urlextern" title="https://en.wikipedia.org/wiki/File:Game_of_life_animated_glider.gif"  rel="nofollow">latawca</a>
</li>
<li class="level1"> <a href="https://en.wikipedia.org/wiki/File:Game_of_life_glider_gun.svg" class="urlextern" title="https://en.wikipedia.org/wiki/File:Game_of_life_glider_gun.svg"  rel="nofollow">działo</a>
</li>
<li class="level1"> itp.
</li>
</ol>

<p>
Aplikacja powinna być wyposażona w interfejs <abbr title="Graphical User Interface">GUI</abbr>.
</p>

<p>
Diagram klas reprezentujący główną logikę aplikacji (klasy nie mają wyspecyfikowanych konstruktorów i setterów/getterów, dodatkowo klasy VonNeumanNeighborhood i MooreNeighborhood zostały zaznaczone schematycznie, bez specyfikacji pól. W celu poprawnego działania
należy podać im również wielkość planszy):</p>
<p>
<a href="http://home.agh.edu.pl/~mwypych/lib/exe/fetch.php?media=zimowy:java2015:labs:automaton.png" class="media" title="zimowy:java2015:labs:automaton.png"><img src="http://home.agh.edu.pl/~mwypych/lib/exe/fetch.php?w=400&amp;tok=d50258&amp;media=zimowy:java2015:labs:automaton.png" class="mediacenter" alt="" width="400" /></a>
</p>
<p>Diagram sekwencji opisujący działanie metody nextState klasy Automaton (nie uwzlędnia pełnej inicjalizacji CellIterator i nie zagłębia się w metodę <em>next</em> klasy CellIterator):</p>
<p>
<a href="http://home.agh.edu.pl/~mwypych/lib/exe/fetch.php?media=zimowy:java2015:labs:automaton-seq.png" class="media" title="zimowy:java2015:labs:automaton-seq.png"><img src="http://home.agh.edu.pl/~mwypych/lib/exe/fetch.php?w=400&amp;tok=16cb6d&amp;media=zimowy:java2015:labs:automaton-seq.png" class="mediacenter" alt="" width="400" /></a>
</p>
