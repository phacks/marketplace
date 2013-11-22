package logic;

public class Item {
	private String name;
	private String owner;
	private String description;

	public Item(String name,String owner, String description){
		this.setName(name);
		this.setOwner(owner);
		this.setDescription(description);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
