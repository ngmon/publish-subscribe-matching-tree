package cz.muni.fi.publishsubscribe.matchingtree;

public class Subscription {

	private String application;
	private Long processId;
	private Integer severity;

	public Subscription() {
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public Integer getSeverity() {
		return severity;
	}

	public void setSeverity(Integer severity) {
		this.severity = severity;
	}

	public Comparable<?> getAttributeValue(int index) {
		switch (index) {
		case 0:
			return application;
		case 1:
			return processId;
		case 2:
			return severity;
		default:
			throw new IllegalArgumentException(
					"Subscription.getAttribute() - index invalid");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((application == null) ? 0 : application.hashCode());
		result = prime * result
				+ ((processId == null) ? 0 : processId.hashCode());
		result = prime * result
				+ ((severity == null) ? 0 : severity.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (processId == null) {
			if (other.processId != null)
				return false;
		} else if (!processId.equals(other.processId))
			return false;
		if (severity == null) {
			if (other.severity != null)
				return false;
		} else if (!severity.equals(other.severity))
			return false;
		return true;
	}

}
