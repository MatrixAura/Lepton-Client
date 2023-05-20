package wtf.agent.client.util.trait;

public interface Nameable {
    String getName();

    default String[] getAliases() {
        return new String[0];
    }
}
