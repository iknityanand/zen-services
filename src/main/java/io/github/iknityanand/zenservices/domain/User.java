package io.github.iknityanand.zenservices.domain;

public class User {
	
	private final String alias;

	public String getAlias() {
		return alias;
	}

	public User(String alias) {
		this.alias = alias;
	}

	@Override
	public String toString() {
		return "User [alias=" + alias + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
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
		User other = (User) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		return true;
	}
	
}
