package dicePool;

import java.util.HashSet;
import java.util.Set;

import dice.AbilityDie;
import dice.BoostDie;
import dice.ChallengeDie;
import dice.DifficultyDie;
import dice.ForceDie;
import dice.ProficiencyDie;
import dice.SetbackDie;
import dice.abstractDice.AbstractEotEDie;
import dice.enums.Symbol;

public class DicePool {

	private final Set<AbstractEotEDie> allEotEDice;

	private final Set<AbilityDie> abilityDice;

	private final Set<BoostDie> boostDice;

	private final Set<ChallengeDie> challengeDice;

	private final Set<DifficultyDie> difficultyDice;

	private final Set<ForceDie> forceDice;

	private final Set<ProficiencyDie> proficiencyDice;

	private final Set<SetbackDie> setbackDice;

	private Integer successes;

	private Integer failures;

	private Integer advantages;

	private Integer threats;

	private Integer triumphs;

	private Integer despairs;

	private Integer lightForcePoints;

	private Integer darkForcePoints;

	public DicePool(Integer numberOfBoostDice, Integer numberOfSetbackDice, Integer numberOfAbilityDice, Integer numberOfDifficultyDice,
			Integer numberOfProficiencyDice, Integer numberOfChallengeDice, Integer numberOfForceDice) {

		boostDice = new HashSet<>();
		setbackDice = new HashSet<>();
		abilityDice = new HashSet<>();
		difficultyDice = new HashSet<>();
		proficiencyDice = new HashSet<>();
		challengeDice = new HashSet<>();
		forceDice = new HashSet<>();

		for (int index = 0; index < numberOfBoostDice; index++) {
			boostDice.add(new BoostDie());
		}
		for (int index = 0; index < numberOfSetbackDice; index++) {
			setbackDice.add(new SetbackDie());
		}
		for (int index = 0; index < numberOfAbilityDice; index++) {
			abilityDice.add(new AbilityDie());
		}
		for (int index = 0; index < numberOfDifficultyDice; index++) {
			difficultyDice.add(new DifficultyDie());
		}
		for (int index = 0; index < numberOfProficiencyDice; index++) {
			proficiencyDice.add(new ProficiencyDie());
		}
		for (int index = 0; index < numberOfChallengeDice; index++) {
			challengeDice.add(new ChallengeDie());
		}
		for (int index = 0; index < numberOfForceDice; index++) {
			forceDice.add(new ForceDie());
		}

		allEotEDice = new HashSet<>();
		allEotEDice.addAll(boostDice);
		allEotEDice.addAll(setbackDice);
		allEotEDice.addAll(abilityDice);
		allEotEDice.addAll(difficultyDice);
		allEotEDice.addAll(challengeDice);
		allEotEDice.addAll(proficiencyDice);
		allEotEDice.addAll(forceDice);

		successes = 0;
		failures = 0;
		advantages = 0;
		threats = 0;
		triumphs = 0;
		despairs = 0;
		lightForcePoints = 0;
		darkForcePoints = 0;

		rollAll();
	}

	public void rollAll() {
		for (AbstractEotEDie die : allEotEDice) {
			die.roll();
			for (Symbol symbol : die.getResult().getSymbols()) {
				switch (symbol) {
				case SUCCESS:
					successes++;
					break;
				case FAILURE:
					failures++;
					break;
				case ADVANTAGE:
					advantages++;
					break;
				case THREAT:
					threats++;
					break;
				case TRIUMPH:
					successes++;
					triumphs++;
					break;
				case DESPAIR:
					failures++;
					despairs++;
					break;
				case LIGHT_FORCE:
					lightForcePoints++;
					break;
				case DARK_FORCE:
					darkForcePoints++;
					break;
				default:
					break;
				}
			}
		}
	}

	public Boolean isSuccessful() {
		Integer successDifference = successes - failures;
		if (successDifference > 0) {
			return true;
		} else {
			return false;
		}
	}

	public Integer getSuccesses() {
		return successes;
	}

	public Integer getFailures() {
		return failures;
	}

	public Integer getDegreeOfSuccess() {
		return successes - failures;
	}

	public Integer getAdvantages() {
		return advantages;
	}

	public Integer getThreats() {
		return threats;
	}

	public Integer getDegreeOfAdvantage() {
		return advantages - threats;
	}

	public Integer getTriumphs() {
		return triumphs;
	}

	public Integer getDespairs() {
		return despairs;
	}

	public Integer getLightForcePoints() {
		return lightForcePoints;
	}

	public Integer getDarkForcePoints() {
		return darkForcePoints;
	}

	public Set<AbilityDie> getAbilityDice() {
		return abilityDice;
	}

	public Set<BoostDie> getBoostDice() {
		return boostDice;
	}

	public Set<ChallengeDie> getChallengeDice() {
		return challengeDice;
	}

	public Set<DifficultyDie> getDifficultyDice() {
		return difficultyDice;
	}

	public Set<ForceDie> getForceDice() {
		return forceDice;
	}

	public Set<ProficiencyDie> getProficiencyDice() {
		return proficiencyDice;
	}

	public Set<SetbackDie> getSetbackDice() {
		return setbackDice;
	}

	public Integer getMaxDiceOfAnyType() {
		Integer maximum = 0;
		maximum = Math.max(getAbilityDice().size(), maximum);
		maximum = Math.max(getBoostDice().size(), maximum);
		maximum = Math.max(getChallengeDice().size(), maximum);
		maximum = Math.max(getDifficultyDice().size(), maximum);
		maximum = Math.max(getForceDice().size(), maximum);
		maximum = Math.max(getProficiencyDice().size(), maximum);
		maximum = Math.max(getSetbackDice().size(), maximum);
		return maximum;
	}
}
