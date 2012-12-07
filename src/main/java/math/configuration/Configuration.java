package math.configuration;

public class Configuration {
	private Integer expressionLength;
	
	private Integer historyLength;
	
	private Integer historyAutofreshMil;
	
	private Boolean saveHistory;
	
	private Boolean clearInputAfterCalculation;
	
	private Boolean cachingUsed;
	
	private Boolean saveDuplicatedExpToHistory;

	public Boolean isCachingUsed() {
		return cachingUsed;
	}

	public void setCachingUsed(Boolean cachingUsed) {
		this.cachingUsed = cachingUsed;
	}

	public Boolean isSaveDuplicatedExpToHistory() {
		return saveDuplicatedExpToHistory;
	}

	public void setSaveDuplicatedExpToHistory(Boolean saveDuplicatedExpToHistory) {
		this.saveDuplicatedExpToHistory = saveDuplicatedExpToHistory;
	}

	public Integer getExpressionLength() {
		return expressionLength;
	}

	public void setExpressionLength(Integer expressionLength) {
		this.expressionLength = expressionLength;
	}

	public Integer getHistoryLength() {
		return historyLength;
	}

	public void setHistoryLength(Integer historyLength) {
		this.historyLength = historyLength;
	}

	public Integer getHistoryAutofreshMil() {
		return historyAutofreshMil;
	}

	public void setHistoryAutofreshMil(Integer historyAutofreshMil) {
		this.historyAutofreshMil = historyAutofreshMil;
	}

	public Boolean isSaveHistory() {
		return saveHistory;
	}

	public void setSaveHistory(Boolean saveHistory) {
		this.saveHistory = saveHistory;
	}

	public Boolean isClearInputAfterCalculation() {
		return clearInputAfterCalculation;
	}

	public void setClearInputAfterCalculation(Boolean clearInputAfterCalculation) {
		this.clearInputAfterCalculation = clearInputAfterCalculation;
	}
}
