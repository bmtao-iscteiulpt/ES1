package antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

@SuppressWarnings("serial")
public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	AutomaticCalculator calc = new AutomaticCalculator(); // ler os ficheiros SPAM HAM
    // para cada mensage estabelece a ligaçao ao vector X, i.e o indice
	
	  public AntiSpamFilterProblem() {
	    // 10 variables (anti-spam filter rules) by default 
	    this(335);
	    
	    //calc.setupComputingFPFN();
	  }

	  public AntiSpamFilterProblem(Integer numberOfVariables) {
	    setNumberOfVariables(numberOfVariables);
	    setNumberOfObjectives(2);
	    setName("AntiSpamFilterProblem");

	    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
	    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

	    for (int i = 0; i < getNumberOfVariables(); i++) {
	      lowerLimit.add(-5.0);
	      upperLimit.add(5.0);
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);
	  }

	  public void evaluate(DoubleSolution solution) {
	    double[] fx = new double[getNumberOfObjectives()];
	    double[] x = new double[getNumberOfVariables()];
	    for (int i = 0; i < solution.getNumberOfVariables(); i++) {
	      x[i] = solution.getVariableValue(i) ;
	    }

	    fx[0] = calc.evaluateFP(x);  // x varia aqui mas as mensagens foram definidas aquando do construtor
	    fx[1] = calc.evaluateFN(x);
	    
	    solution.setObjective(0, fx[0]);
	    solution.setObjective(1, fx[1]);
	  }
	}
