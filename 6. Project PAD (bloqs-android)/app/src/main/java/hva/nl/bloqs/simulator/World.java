package hva.nl.bloqs.simulator;

import android.support.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.voodoodyne.jackson.jsog.JSOGGenerator;

@JsonIdentityInfo(generator=JSOGGenerator.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class World implements Runnable {
    private long currentTick;
    private boolean running;

    private int gridWidth;
    private int gridHeight;
    private Block[][] grid;

    private WorldEventListener worldEventListener;

    private Integer scenario;

    public World(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        this.grid = new Block[gridWidth][gridHeight];

        currentTick = 0;
        running = false;
    }

    public void run(){
        running = true;

        // Notify the listener about the current grid size.
        this.setGridSize(null, null);

        while(isRunning()) {
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            currentTick++;

            Block[][] newGrid = new Block[gridWidth][gridHeight];

            for(int x = 0; x < grid.length; x++)
                for (int y = 0; y < grid[x].length; y++)
                    if (grid[x][y] != null)
                        newGrid[x][y] = grid[x][y].update(currentTick);

            grid = newGrid;

            if(worldEventListener != null)
                worldEventListener.onTickEvent(currentTick);

            if (currentTick % 25 == 0 && getScenario() != null)
            {
                if (GameScenario.Scenarios[getScenario()].hasMetConditions(this))
                {
                    if (worldEventListener != null)
                        worldEventListener.onScenarioMetConditions();
                }
            }
        }
    }


    public <T extends Block> T getBlockAt(int x, int y, Class<T> type) {
        return type.cast(grid[x][y]);
    }

    public void setBlockAt(int x, int y, int orientation, Block block) {
        block.setWorld(this);
        block.setPosition(x, y, orientation);
        grid[x][y] = block;
    }

    public void setBlockOrientation(int x, int y, int orientation) {
        if (grid[x][y] != null)
            grid[x][y].setOrientation(orientation);
    }

    public void setBlockAt(int x, int y, Block block)
    {
        setBlockAt(x, y, Block.SIDE_RIGHT, block);
    }

    public void removeBlockAt(int x, int y) {
        grid[x][y] = null;
    }

    public void stop() {
        running = false;
    }


    public boolean isRunning(){
        return running;
    }

    public void setWorldEventListener(WorldEventListener worldEventListener) {
        this.worldEventListener = worldEventListener;
    }

    public void setGridWidth(int gridWidth) {
        this.setGridSize(null, this.gridHeight);
    }

    public void setGridHeight(int gridHeight) {
        this.setGridSize(this.gridWidth, null);
    }

    public void setGridSize(Integer gridWidth, Integer gridHeight) {
        if (gridWidth != null)
            this.gridWidth = gridWidth;
        if (gridHeight != null)
            this.gridHeight = gridHeight;

        if(worldEventListener != null)
            worldEventListener.onGridResize(this.gridWidth, this.gridHeight);
    }

    public void setScenario(Integer scenarioIndex) {
        this.scenario = scenarioIndex;
        if(scenarioIndex != null)
            GameScenario.Scenarios[scenarioIndex].initializeWorld(this);
    }
    public Integer getScenario() { return this.scenario; }

    public long getCurrentTick(){
        return currentTick;
    }

    public Block[][] getGrid() {
        return grid;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public static final int BLOCK_SIZE = 100;

}
