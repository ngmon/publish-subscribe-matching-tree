package cz.muni.fi.publishsubscribe.matchingtree;

/**
 * A simple POJO representing an event; its attributes are ordered (starting
 * from 0)
 */
public class Event {

	private Long id;
	private String application;
	private Long processId;
	private Integer severity;

	public Event() {
	}

	public Event(Long id, String application, Long processId, Integer severity) {
		this.id = id;
		this.application = application;
		this.processId = processId;
		this.severity = severity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	/**
	 * @param index
	 *            Attribute index (from 0 to Constants.EVENT_ATTRIBUTE_COUNT -
	 *            1)
	 * @return Attribute value
	 * @throws IllegalArgumentException
	 *             If the index is out of range
	 */
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
					"Event.getAttribute() - index invalid");
		}
	}

}
