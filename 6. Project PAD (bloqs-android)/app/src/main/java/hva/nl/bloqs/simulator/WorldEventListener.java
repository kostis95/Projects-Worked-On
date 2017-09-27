package hva.nl.bloqs.simulator;


public interface WorldEventListener {
    void onTickEvent(long currentTick);
    void onGridResize(int width, int height);
    void onScenarioMetConditions();
}
