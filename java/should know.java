

// Background cleaner
    private final ScheduledExecutorService cleaner = Executors.newSingleThreadScheduledExecutor();


cleaner.scheduleAtFixedRate(this::evictExpired, ttlMillis / 2, ttlMillis / 2, TimeUnit.MILLISECONDS);



